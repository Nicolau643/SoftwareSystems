package business;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Horario {
	
	private DayOfWeek[] days;
	private LocalTime horaInicio;
	private int duracoes;

	public Horario(DayOfWeek[] days, LocalTime horaInicio, int duracoes) {
		this.days = days;
		this.horaInicio = horaInicio;
		this.duracoes = duracoes;
	}

	
	public DayOfWeek[] getDays() {
		return days;
	}
	
	public int getDuracoes() {
		return duracoes;
	}
	
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
}
