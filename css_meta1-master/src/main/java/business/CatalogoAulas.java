package business;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CatalogoAulas {

	private List<Aula> data;
	
	public CatalogoAulas() {
		this.data = new ArrayList<>();
	}

	public void add(Modalidade modalidade, String nomeAula, DayOfWeek[] days, LocalTime horaInicio, int duracoes) {
		
		Aula a = new Aula(modalidade,nomeAula);
		a.setHorario(days,horaInicio,duracoes);
		data.add(a);
		
	}


	public boolean exists(String nomeAula) {
		return data.stream().filter(a -> a.getNome().equals(nomeAula)).count() != 0;
		
	}

	public Aula getByName(String nomeAula) {
		System.out.println(data.get(0).getNome());
		for (Aula a : data) {
			if (a.getNome().equals(nomeAula)) {
				return a;
			}
		}
		return null;
	}

	public List<Aula> getAllActive() {
		List<Aula> res = new ArrayList<>();
		
		for (Aula aula : data) {
			if (aula.isActive()) {
				res.add(aula);
			}
		}
		
		return res;
	}

}
