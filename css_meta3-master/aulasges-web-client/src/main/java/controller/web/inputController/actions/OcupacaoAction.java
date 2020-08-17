package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.handlers.IAulasServiceRemote;
import presentation.web.model.OcupacaoModel;

@Stateless
public class OcupacaoAction extends Action{
	
	@EJB
	private IAulasServiceRemote aulasServices;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OcupacaoModel ocupModel = new OcupacaoModel();
		ocupModel.setHandler(aulasServices);
		//ocupModel.setInicialState();
		request.setAttribute("model", ocupModel);
		request.getRequestDispatcher("/ocupacao/ocupacao.jsp").forward(request, response);
		
	}

}
