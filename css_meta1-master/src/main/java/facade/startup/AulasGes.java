package facade.startup;

import business.AtivaAulaHandler;
import business.CatalogoAulas;
import business.CatalogoInscricoes;
import business.CatalogoInstalacao;
import business.CatalogoModadlidade;
import business.CatalogoUtente;
import business.CriaAulaHandler;
import business.InscreverAulaHandler;
import business.OcupacaoHandler;

public class AulasGes {
	
	private CatalogoInscricoes catInsc;
	private CatalogoUtente catUtente;
	private CatalogoModadlidade catMod;
	private CatalogoAulas catAulas;
	private CatalogoInstalacao catInst;
	
	private AtivaAulaHandler ativaAulaHandler;
	private CriaAulaHandler criaAulaHandler;
	private InscreverAulaHandler inscreverAulaHandler;
	private OcupacaoHandler ocupHandler;
	
	public AulasGes() {
		catAulas = new CatalogoAulas();
		catUtente = new CatalogoUtente();
		catMod = new CatalogoModadlidade();
		catAulas = new CatalogoAulas();
		catInst = new CatalogoInstalacao();
		
		ativaAulaHandler = new AtivaAulaHandler(catInst, catAulas);
		criaAulaHandler = new CriaAulaHandler(catAulas, catMod);
		inscreverAulaHandler = new InscreverAulaHandler(catAulas, catInst, catMod, catInsc, catUtente);
		ocupHandler = new OcupacaoHandler(catInst);
	}

	public AtivaAulaHandler getAtivaAulaHandler() {
		return ativaAulaHandler;
	}

	public CriaAulaHandler getCriaAulaHandler() {
		return criaAulaHandler;
	}

	public InscreverAulaHandler getInscreverAulaHandler() {
		return inscreverAulaHandler;
	}

	public OcupacaoHandler getOcupHandler() {
		return ocupHandler;
	}
	
	

}
