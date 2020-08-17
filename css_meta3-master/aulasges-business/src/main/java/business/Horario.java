package business;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
public class Horario {
	
	public Horario() {}
	
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<DayOfWeek> days;
	
	@Column(nullable = false)
	@Convert(converter = LocalTimeAttributeConverter.class)
	private LocalTime horaInicio;
	
	@Column(nullable = false)
	private int duracoes;

	public Horario(DayOfWeek[] days, LocalTime horaInicio, int duracoes) {
		this.days = new ArrayList<>();
		for (DayOfWeek dayOfWeek : days) {
			this.days.add(dayOfWeek);
		}
		this.horaInicio = horaInicio;
		this.duracoes = duracoes;
	}

	
	public DayOfWeek[] getDays() {
		DayOfWeek[] array = new DayOfWeek[days.size()];
		return days.toArray(array);
	}
	
	public int getDuracoes() {
		return duracoes;
	}
	
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
}
