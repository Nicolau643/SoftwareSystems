package business;

public class Regular extends Inscricao{

	public Regular(Aula aula, Utente utente) {
		super(aula,utente);
	}

	@Override
	public double getCusto() {
		return this.getAula().getMod().getCustoHora() * this.getAula().getHorario().getDuracoes() / 60 
				* this.getAula().getHorario().getDays().length * 4;
	}

}
