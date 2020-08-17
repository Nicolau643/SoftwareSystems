package business.handlers;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.Modalidade;
import business.catalogos.CatalogoAulas;
import business.catalogos.CatalogoModadlidade;
import facade.dto.ModalidadeDTO;
import facade.exceptions.ApplicationException;

@Stateless
public class CriaAulaHandler {

	@EJB
	private CatalogoAulas catLectures;
	@EJB
	private CatalogoModadlidade catModalidades;

	public void addAula(String modalidade, String nomeAula, int[] days, String horaInicio, int duracoes) throws ApplicationException {
		System.out.println("bora!");
		Modalidade mod = catModalidades.getByName(modalidade);
		
		//supostamente tem de ser negado para corresponder ao pedido
		if(nomeAula.length() == 6 && isValidName(nomeAula) && catLectures.exists(nomeAula)) {
			throw new ApplicationException("nome de aula invalido! o nome tem 6 caracteres, pelo menos 3 alfanuméricos, "
					+ "não tem espaços, e é único");
		}
		
		if(duracoes <= 0) {
			throw new ApplicationException("duracoes nao sao positivas ou 0!");
		}
		
		if(!durationMoreThanMod(mod,duracoes)) {
			throw new ApplicationException("duracoes nao correspondem aos minimos da modalidade pretendida!");
		}
		
		LocalTime newhoraInicio =  convertBeginHours(horaInicio);
		DayOfWeek[] newDays = convertDays(days);
		
		catLectures.add(mod,nomeAula,newDays,newhoraInicio,duracoes);
		
	}


	private DayOfWeek[] convertDays(int[] days) throws ApplicationException {
		
		DayOfWeek[] res = new DayOfWeek[days.length];
		for (int i = 0; i < days.length; i++) {
			try {
				res[i] = DayOfWeek.of(days[i]);
			}catch(DateTimeParseException e) {
				throw new ApplicationException("Formatos de dias de inicio errados!");
			}
		}
		return res;
	}


	private LocalTime convertBeginHours(String horaInicio) throws ApplicationException {
		LocalTime res;
		
		try {
			res = LocalTime.parse(horaInicio);
		}catch(DateTimeParseException e) {
			throw new ApplicationException("Formatos de datas de inicio errados!");
		}

		return res;
	}


	private boolean durationMoreThanMod(Modalidade mod, int duracoes) {
		
		return duracoes >= mod.getduracaoMin();
	}

	private boolean isValidName(String nomeAula) {
		
		int numericCont = 0;
		
		for (int i = 0; i < nomeAula.length(); i++) {
			if (Character.isDigit(nomeAula.charAt(i))) {
				numericCont++;
			} else if (nomeAula.charAt(i) == ' ') {
				return false;
			}
		}
		
		return numericCont < 3;
	}


	public List<ModalidadeDTO> getModalidades() throws ApplicationException {		
		List<Modalidade> allM = catModalidades.getAllMod();
		List<ModalidadeDTO> res = new ArrayList<>();
		
		for (Modalidade ins : allM) {
			res.add(new ModalidadeDTO(ins.getId(), ins.getName()));
		}
		
		return res;
	}
	
	
}
