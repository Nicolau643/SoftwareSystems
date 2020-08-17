package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.dto.AulaDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IInscreverServiceRemote;
import presentation.web.model.InscreverAulaModel;

@Stateless
public class InscreverAulaSubmitAction extends Action {
	
	@EJB 
	private IInscreverServiceRemote inscreverServices;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		if (action.equals("Procurar")) {
			
			InscreverAulaModel model = createHelper(request);
			model.setAulasB();
			request.setAttribute("model", model);
			
			request.getRequestDispatcher("/inscreverAula/inscreverAula.jsp").forward(request, response);
			
		}else {
			
			InscreverAulaModel model = createHelper(request);
			request.setAttribute("model", model);
			
		if (isValid(model)) {
			
			
			AulaDTO aulaDto = model.getInscricao().equals("Regular") ? new AulaDTO(0,model.getNomeAula(), null):new AulaDTO(0,model.getNomeAula(), model.getDiaSemana());
			
			try {
				inscreverServices.escolhe(model.getInscricao(), aulaDto, intValue(model.getNumUtente()));
				model.clearFields();
				model.addMessage("Inscrito com sucesso!");
			} catch (ApplicationException e) {
				model.addMessage("Erro ao Inscrever: " + e.getMessage());
			}
			
		}else {
			model.addMessage("Dados Inválidos!");
		}
		
		request.getRequestDispatcher("/inscreverAula/inscreverAula.jsp").forward(request, response);
		}
	}

	private boolean isValid(InscreverAulaModel model) {
		boolean result = isFilled(model, model.getNomeAula(), "nome aula vazio!");
		
		result &= isFilled(model, model.getNumUtente(), "numero de utente vazio!") && intValue(model.getNumUtente()) > 0;
		
		if (model.getInscricao().equals("Avulso")) {
			result &= isFilled(model, model.getDiaSemana(), "aula avulso e nao tem dia da semana!");
		}
		return result;
	}

	private InscreverAulaModel createHelper(HttpServletRequest request) {
		InscreverAulaModel model = new InscreverAulaModel();
		model.setHandler(inscreverServices);
		
		model.setNomeAula(request.getParameter("nomeAula"));
		model.setDiaSemana(request.getParameter("diaSemana"));
		model.setInscricao(request.getParameter("inscricao"));
		model.setNumUtente(request.getParameter("numUtente"));
		model.setModalidade(request.getParameter("modalidade"));
		
		return model;
	}

}
