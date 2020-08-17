package business;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQuery(name = Sessao.GET_ALL, query = "SELECT s from Sessao s")
public class Sessao {
	
	public static final String GET_ALL = "Sessao.getAll";
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Instalacao instalacao;
	@ManyToOne
	private Aula aula;
	
	@Enumerated(EnumType.STRING)
	private DayOfWeek day;
	
	@Column
	private LocalDate date;
	
	@Column
	private LocalTime timeInit;
	
	private int duration;
	
	private int vagas;
	
	
	
	public Sessao() {}
	
	public Sessao(DayOfWeek day,LocalDate date, LocalTime timeInit, int duration,Aula aula,int vagas,Instalacao instalacao) {
		super();
		this.day = day;
		this.date = date;
		this.timeInit = timeInit;
		this.duration = duration;
		this.aula = aula;
		this.vagas = vagas;
		this.instalacao = instalacao;
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
		return vagas > 0;
	}
	
	public int getVagas() {
		return vagas;
	}

	public void decVaga() {
		vagas--;
		
	}
	
	public Instalacao getInstalacao() {
		return instalacao;
	}

	@Override
	public String toString() {
		return "Sessao [id=" + id + ", day=" + day + ", date=" + date + ", timeInit=" + timeInit + ", duration="
				+ duration + ", aula=" + aula + ", vagas=" + vagas + "]";
	}
	
	
}
