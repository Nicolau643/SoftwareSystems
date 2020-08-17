package business.handlers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.Instalacao;
import business.Sessao;
import business.catalogos.CatalogoInstalacao;
import facade.dto.AulaDTO;
import facade.exceptions.ApplicationException;
@Stateless
public class OcupacaoHandler {
	
	@EJB
	private CatalogoInstalacao catInstalacao;
	
	public AulaDTO[] getOcupacao(String inst, String date) throws ApplicationException {
		
		Instalacao instalacao = catInstalacao.getByName(inst);
		
		List<AulaDTO> res = new ArrayList<>();
		
		if (instalacao == null) {
			throw new ApplicationException("instalacao invalida!");
		}
		
		LocalDate newDate = LocalDate.parse(date);
		
		List<Sessao> sessoes = instalacao.getSessoes();
		
		for (Sessao s : sessoes) {
			if (s.getDate().compareTo(newDate) == 0) {
				res.add(new AulaDTO(s.getAula().getId(),
						s.getAula().getNome(),
						s.getAula().getHorario().getHoraInicio().toString(),
						s.getAula().getHorario().getDuracoes()
						));
			}
		}
		
		Collections.sort(res, new Comparator<AulaDTO>() {

			@Override
			public int compare(AulaDTO o1, AulaDTO o2) {
				return LocalTime.parse(o1.getHorario()).compareTo(LocalTime.parse(o2.getHorario()));
			}
		});
		
		
		return res.toArray(new AulaDTO[0]);
	} 
	
	
}
