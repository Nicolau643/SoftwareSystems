package business;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import facade.exceptions.ApplicationException;

public class AtivaAulaHandler {
	
	private CatalogoInstalacao catIns;
	private CatalogoAulas catAulas;
	

	public AtivaAulaHandler(CatalogoInstalacao catIns, CatalogoAulas catAulas) {
		super();
		this.catIns = catIns;
		this.catAulas = catAulas;
	}

	public String[] getInstalacoes() {
		return catIns.getAllNames();
	}

	public void ativaAula(String inst, String nomeAula, String ini, String end, int maxAlunos) throws ApplicationException {
		
		Aula aula = catAulas.getByName(nomeAula);
		
		//a aula indicada é válida e não está ativa
		if (aula == null) {
			throw new ApplicationException("aula nao existente!");
		}
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
		
		//a instalação indicada é válida
		if (newInst == null) {
			throw new ApplicationException("nao existe instalacao!");
		}
		
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
		
		newInst.fillSessions(aula.getHorario(),newIni,newEnd,aula);
		
	}
	

}
