package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.handlers.IAulasServiceRemote;
import presentation.web.model.AtivarAulaModel;

@Stateless
public class AtivarAulaAction extends Action {

	@EJB 
	private IAulasServiceRemote aulasServices;
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AtivarAulaModel model = new AtivarAulaModel();
		model.setHandler(aulasServices);
		request.setAttribute("model", model);
		request.getRequestDispatcher("/ativarAula/ativarAula.jsp").forward(request, response);
		
	}

}
