package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.handlers.IAulasServiceRemote;
import presentation.web.model.CriarAulaModel;

@Stateless
public class CriarAulaAction extends Action {

	@EJB
	private IAulasServiceRemote aulasService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CriarAulaModel ocupModel = new CriarAulaModel();
		ocupModel.setHandler(aulasService);
		//ocupModel.setInicialState();
		request.setAttribute("model", ocupModel);
		request.getRequestDispatcher("/criarAula/criarAula.jsp").forward(request, response);
		
	}
	
	
}
