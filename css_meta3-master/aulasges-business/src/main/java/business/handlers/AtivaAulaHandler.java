package business.handlers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.Aula;
import business.Instalacao;
import business.Sessao;
import business.catalogos.CatalogoAulas;
import business.catalogos.CatalogoInstalacao;
import facade.dto.InstalacaoDTO;
import facade.exceptions.ApplicationException;
@Stateless
public class AtivaAulaHandler {
	
	@EJB
	private CatalogoInstalacao catIns;
	@EJB
	private CatalogoAulas catAulas;

	
	public List<InstalacaoDTO> getInstalacoes() throws ApplicationException {
		List<Instalacao> allI = catIns.getAllInstalacoes();
		List<InstalacaoDTO> res = new ArrayList<>();
		
		for (Instalacao ins : allI) {
			res.add(new InstalacaoDTO(ins.getId(), ins.getNome()));
		}
		
		return res;
	}

	public void ativaAula(String inst, String nomeAula, String ini, String end, int maxAlunos) throws ApplicationException {
		
		
		Aula aula = catAulas.getByName(nomeAula);
		
		if (aula.isActive()) {
			throw new ApplicationException("aula ja ativa!");
		}
		//o par de datas define um período no futuro
		LocalDate newIni;
		LocalDate newEnd;
		try {
			newIni = LocalDate.parse(ini);
			newEnd = LocalDate.parse(end);
		} catch (DateTimeParseException e) {
			throw new ApplicationException("formatos nao estao corretos!");
		}
		
		if ( newIni.isBefore(LocalDate.now()) && newIni.isAfter(newEnd)) {
			throw new ApplicationException("datas nao consistentes!");
		}
		
		Instalacao newInst = catIns.getByName(inst);
		
		//a instalação permite a realização de atividades da modalidade da aula
		if (!newInst.modIsValid(aula.getModalidade())) {
			throw new ApplicationException("instalacao nao tem essa modalidade!");
		}
		
		//a instalação está livre no horário/dias-da-semana da aula, durante todo o período em que a aula estiver ativa
		if (!newInst.isfreeBetween(newIni,newEnd,aula.getHorario())) {
			throw new ApplicationException("instalacao nao tem disponibilidade total no periodo pretendido!");
		}
		
		
		//o número máximo de participantes é inferior à capacidade da instalação indicada
		if (maxAlunos>newInst.getMaxLot()) {
			throw new ApplicationException("o numero maximo de participantes inferior capacidade da instalacao indicada!");
		}
		
		aula.ativarAula(newInst,newIni,newEnd,maxAlunos);
		
		//newInst.fillSessions(aula.getHorario(),newIni,newEnd,aula,maxAlunos,newInst);
		
		ArrayList<DayOfWeek> a = new ArrayList<>();
		ArrayList<Sessao> sessoes = new ArrayList<>();
		
		for (DayOfWeek dayOfWeek : aula.getHorario().getDays()) {
			a.add(dayOfWeek);
		}
		
		
		while (newIni.compareTo(newEnd) != 0) {
			
			if (a.contains(newIni.getDayOfWeek())) {
				Sessao s = new Sessao(newIni.getDayOfWeek(), newIni, aula.getHorario().getHoraInicio(), aula.getHorario().getDuracoes(),aula,maxAlunos,newInst);
				sessoes.add(s);
				//aulaAtiva.addSessao(s);
			}
			
			newIni = newIni.plusDays(1);
		}
		
		newInst.fillSessoes(sessoes,aula);
	
	}
	

}
