package business;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import static javax.persistence.CascadeType.PERSIST;


@Entity
@NamedQuery(name = Aula.EXISTS_AULA, query = "Select Count(c) from Aula c "
		+ "where c.nome = :" + Aula.AULA_NAME)
@NamedQuery(name = Aula.GET_AULA, query = "Select c from Aula c " + 
		"where c.nome = :" + Aula.AULA_NAME)
@NamedQuery(name = Aula.GET_ATIVAS_AULA, query = "Select c from Aula c " + 
		"where c.activeClass is not null")
@NamedQuery(name = Aula.GET_BY_MOD, query = "Select c from Aula c JOIN c.mod m " + 
		"where m.nome = :" + Aula.MOD_NAME)
public class Aula {
	
	public static final String EXISTS_AULA = "Aula.existsByName";
	public static final String AULA_NAME = "nome";
	
	public static final String GET_AULA = "Aula.getByName";
	
	public static final String GET_ATIVAS_AULA = "Aula.getAulasAtivas";
	
	public static final String GET_BY_MOD = "Aula.getByModalidade";
	public static final String MOD_NAME = "name";
	
	
	@Id 
	@GeneratedValue
	private int id;
	@Column(nullable = false, unique = true)
	private String nome;
	@Embedded
	private Horario horario;
	@OneToOne
	private Modalidade mod;
	@OneToOne(cascade = PERSIST) // pq esta classe e responsavel por criar uma aula ativa, assim qnd e
								 // criada tbm e persistida
	private AulaAtiva activeClass;
	
	public Aula() {}

	public Aula(Modalidade modalidade, String nomeAula) {
		this.mod = modalidade;
		this.nome = nomeAula;
	}

	
	public void setHorario(DayOfWeek[] days, LocalTime horaInicio, int duracoes) {
		this.horario = new Horario(days,horaInicio,duracoes);
	}
	
	public String getNome() {
		return nome;
	}

	public Modalidade getModalidade() {
		return mod;
	}

	
	public Horario getHorario() {
		return horario;
	}


	public void ativarAula(Instalacao newInst, LocalDate newIni, LocalDate newEnd, int maxAlunos) {
		activeClass = new AulaAtiva(newInst,newIni,newEnd,maxAlunos);
	}
	
	public boolean isActive() {
		return activeClass != null;
	}

	public AulaAtiva getActiveClass() {
		return activeClass;
	}


	public void addSessao(Sessao s) {
		activeClass.addSessao(s);
		
	}

	public Modalidade getMod() {
		return mod;
	}
	
	public String getModName() {
		return mod.getName();
	}

	/*@Override
	public String toString() {
		return "Aula [id=" + id + ", nome=" + nome + ", horario=" + horario + ", mod=" + mod + ", activeClass="
				+ activeClass + "]";
	}*/
	
	

	
}
