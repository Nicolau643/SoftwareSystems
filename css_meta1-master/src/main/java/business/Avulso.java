package business;

public class Avulso extends Inscricao {

	public Avulso(Aula aula, Utente utente) {
		super(aula,utente);
	}


	@Override
	public double getCusto() {
		return this.getAula().getMod().getCustoHora() * this.getAula().getHorario().getDuracoes() / 60;
	}

}
