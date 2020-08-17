package business;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import facade.exceptions.ApplicationException;

public class OcupacaoHandler {

	private CatalogoInstalacao catInstalacao;
	
	
	public OcupacaoHandler(CatalogoInstalacao catInstalacao) {
		super();
		this.catInstalacao = catInstalacao;
	}
	
	public AulaDTO[] getOcupacao(String inst, String date) throws ApplicationException {
		
		Instalacao instalacao = catInstalacao.getByName(inst);
	
		List<AulaDTO> res = new ArrayList<>();
		
		if (instalacao == null) {
			throw new ApplicationException("instalacao invalida!");
		}
		
		LocalDate newDate = LocalDate.parse(date);
		
		for (Sessao s : instalacao.getSessoes()) {
			if (s.getDate().compareTo(newDate) == 0) {
				res.add(new AulaDTO(s.getAula().getNome(),
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
		
		
		return (AulaDTO[]) res.toArray();
		
	} 
	
	
}
