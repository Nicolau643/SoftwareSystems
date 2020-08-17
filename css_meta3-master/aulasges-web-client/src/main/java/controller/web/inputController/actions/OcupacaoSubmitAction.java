package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;
import presentation.web.model.OcupacaoModel;

@Stateless
public class OcupacaoSubmitAction  extends Action {
	
	@EJB
	private IAulasServiceRemote aulasService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OcupacaoModel model = createHelper(request);
		
		
		if (isValid(model)) {
			try {
				aulasService.getOcup(model.getNomeInst(), model.getData());
				model.setAulasB();
				request.setAttribute("model", model);
				
				request.getRequestDispatcher("/ocupacao/ocupacao.jsp").forward(request, response);
				
			} catch (ApplicationException e) {
				model.addMessage("erro ao pedir ocupacao: " + e.getMessage());
			}
		}else {
			model.addMessage("Dados nao validos!");
		}
		
	}

	private boolean isValid(OcupacaoModel model) {
		boolean result = isFilled(model, model.getData(), "data esta vazia!");
		result &= isFilled(model, model.getNomeInst(), "instalacao vazia");
		return result;
	}

	private OcupacaoModel createHelper(HttpServletRequest request) {
		OcupacaoModel model = new OcupacaoModel();
		model.setHandler(aulasService);
		
		model.setData(request.getParameter("data"));
		model.setNomeInst(request.getParameter("nomeInst"));
		
		
		return model;
	}
	
	
	
}
