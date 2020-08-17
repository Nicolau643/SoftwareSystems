package business;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Sessao {
	
	private DayOfWeek day;
	private LocalDate date;
	private LocalTime timeInit;
	private int duration;
	private Aula aula;
	private int vagas;
	
	public Sessao(DayOfWeek day,LocalDate date, LocalTime timeInit, int duration,Aula aula) {
		super();
		this.day = day;
		this.date = date;
		this.timeInit = timeInit;
		this.duration = duration;
		this.aula = aula;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public LocalTime getTimeInit() {
		return timeInit;
	}

	public int getDuration() {
		return duration;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public Aula getAula() {
		return aula;
	}
	
	public boolean hasVagas() {
		return vagas != 0;
	}
	
	public int getVagas() {
		return vagas;
	}

	public void decVaga() {
		vagas--;
		
	}
}
