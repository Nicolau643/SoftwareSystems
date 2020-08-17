package business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Regular extends Inscricao{
	
	@OneToMany
	private List<Sessao> sessoes;
	
	public Regular() {}
	
	public Regular(List<Sessao> sessoes, Aula aula, Utente utente) {
		super(aula,utente);
		this.sessoes = sessoes;
	}

	@Override
	public double getCusto() {
		return getAula().getMod().getCustoHora() * getAula().getHorario().getDuracoes() / 60 
				* getAula().getHorario().getDays().length * 4;
	}
	
	public List<Sessao> getSessoes() {
		return sessoes;
	}
}
