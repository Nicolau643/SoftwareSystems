package business;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import facade.exceptions.ApplicationException;

public class CriaAulaHandler {

	private CatalogoAulas catLectures;
	private CatalogoModadlidade catModalidades;
	
	
	public CriaAulaHandler(CatalogoAulas catLectures, CatalogoModadlidade catModalidades) {
		super();
		this.catLectures = catLectures;
		this.catModalidades = catModalidades;
		
	}


	public void addAula(String modalidade, String nomeAula, int[] days, String horaInicio, int duracoes) throws ApplicationException {
		
		Modalidade mod = catModalidades.getByName(modalidade);
		
		if (mod == null) {
			throw new ApplicationException("modalidade invalida!");
		}
		
		if(nomeAula.length() == 6 && isValidName(nomeAula) && catLectures.exists(nomeAula)) {
			throw new ApplicationException("nome de aula invalido! o nome tem 6 caracteres, pelo menos 3 alfanuméricos, "
					+ "não tem espaços, e é único");
		}
		
		if(duracoes <= 0) {
			throw new ApplicationException("duracoes nao sao positivas ou 0!");
		}
		
		if(!durationLessThanMod(mod,duracoes)) {
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
		//LocalTime[] res = new LocalTime[horaInicio.length];
		LocalTime res;
		//for (int i = 0; i < horaInicio.length; i++) {
			try {
				res = LocalTime.parse(horaInicio);
			}catch(DateTimeParseException e) {
				throw new ApplicationException("Formatos de datas de inicio errados!");
			}
		//}
		return res;
	}


	private boolean durationLessThanMod(Modalidade mod, int duracoes) {
		
		/*for (int d : duracoes) {
			if (d < mod.getduracaoMin()) {
				return false;
			}
		}*/
		
		return duracoes < mod.getduracaoMin();
	}

	/*
	private boolean durationPositive(int[] duracoes) {
		for (int i : duracoes) {
			if (i <=0) {
				return false;
			}
		}
		return true;
	}*/


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
	
	
}
