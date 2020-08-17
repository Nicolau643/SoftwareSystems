package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;
import presentation.web.model.AtivarAulaModel;

@Stateless
public class AtivarAulaSubmitAction extends Action {
	
	@EJB 
	private IAulasServiceRemote aulasServices;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AtivarAulaModel model = createHelper(request);
		request.setAttribute("model", model);
		
		if (validInput(model)) {
			
			try {
				System.out.println("inst " + model.getInstalacao());
				aulasServices.ativaAula(model.getInstalacao(), model.getNomeAula(), model.getDataIni(),
						model.getDataFim(), intValue(model.getMaxAlunos()));
				model.clearFields();
				model.addMessage("Aula ativada com sucesso!");
			} catch (ApplicationException e) {
				model.addMessage("Erro a ativar: " + e.getMessage());
			}
			
		}else {
			model.addMessage("Erro na Validacao dos dados!");
		}
		
		request.getRequestDispatcher("/ativarAula/ativarAula.jsp").forward(request, response);
		
	}
	
	private boolean validInput(AtivarAulaModel model) {
		boolean result = isFilled(model, model.getNomeAula(), "nome da aula esta vazio!");
		
		result &= isFilled(model, model.getDataIni(), "data de inicio esta vazia!");
		
		result &= isFilled(model, model.getDataFim(), "data de fim esta vazia!");
		
		result &= isFilled(model, model.getMaxAlunos(), "numero maximo de alunos esta vazio!") && intValue(model.getMaxAlunos())>0;
		
		return result;
	}

	private AtivarAulaModel createHelper(HttpServletRequest request) {
		// Create the object model
		AtivarAulaModel model = new AtivarAulaModel();
		model.setHandler(aulasServices);
		
		System.out.println("instalacao action = "+ request.getParameter("instalacao"));
		
		// fill it with data from the request
		model.setInstalacao(request.getParameter("instalacao"));
		model.setNomeAula(request.getParameter("nomeAula"));
		model.setDataIni(request.getParameter("dataIni"));
		model.setDataFim(request.getParameter("dataFim"));
		model.setMaxAlunos(request.getParameter("maxAlunos"));
		
		
		return model;
	}	

}
