package business;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Avulso extends Inscricao {

	@OneToOne
	private Sessao sessao;
	
	public Avulso() {}
	
	public Avulso(Sessao s, Aula aula, Utente utente) {
		super(aula,utente);
		sessao = s;
	}


	@Override
	public double getCusto() {
		return sessao.getAula().getMod().getCustoHora() * sessao.getAula().getHorario().getDuracoes() / 60;
	}
	
	public Sessao getSessao() {
		return sessao;
	}

}
