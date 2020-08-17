package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.handlers.IInscreverServiceRemote;
import presentation.web.model.InscreverAulaModel;

@Stateless
public class InscreverAulaAction extends Action {
	
	@EJB
	private IInscreverServiceRemote inscreverService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InscreverAulaModel ocupModel = new InscreverAulaModel();
		ocupModel.setHandler(inscreverService);
		//ocupModel.setInicialState();
		request.setAttribute("model", ocupModel);
		request.getRequestDispatcher("/inscreverAula/inscreverAula.jsp").forward(request, response);
		
	}
	

}
