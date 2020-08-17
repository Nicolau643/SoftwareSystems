package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;
import presentation.web.model.CriarAulaModel;

@Stateless
public class CriarAulaSubmitAction extends Action{
	
	@EJB
	private IAulasServiceRemote aulasService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CriarAulaModel model = createHelper(request);
		request.setAttribute("model", model);
		System.out.println("inicio!");
		if (validInput(model)) {
			
			int[] dias = getIntValues(model.getDias());
			
			try {
				System.out.println("modalidade =" + model.getModalidade() + " "+ model.getModalidade().length() );
				aulasService.criarAula(model.getModalidade(), model.getNomeAula(), dias, model.getHoraIni()
						, intValue(model.getDuracao()));
				model.clearFields();
				model.addMessage("Aula criada com sucesso!");
			} catch (ApplicationException e) {
				System.out.println("erro!");
				model.addMessage("Erro ao criar Aula! : " + e.getMessage());
				e.printStackTrace();
			}
			
		}else {
			System.out.println("erroooo!");
			model.addMessage("Erro dos dados Inseridos!");
		}
		System.out.println("passou!");
		request.getRequestDispatcher("/criarAula/criarAula.jsp").forward(request, response);
	}

	
	
	private int[] getIntValues(String dias) {
		String[] diasInput = dias.split(",");
		int[] diasInt = new int[diasInput.length];
		int i=0;
		for (String d : diasInput) {
			if (d.equals("Segunda")) {
				diasInt[i] = 1;
			}else if (d.equals("Terça")) {
				diasInt[i] = 2;
			}else if (d.equals("Quarta")) {
				diasInt[i] = 3;
			}else if (d.equals("Quinta")) {
				diasInt[i] = 4;
			}else if (d.equals("Sexta")) {
				diasInt[i] = 5;
			}else {
				return null;
			}
			i++;
		}
		return diasInt;
		
	}



	private boolean validInput(CriarAulaModel model) {
		boolean result = isFilled(model,model.getDias(),"Dias está vazio");
		
		result &= isFilled(model, model.getDuracao(), "duracao esta vazia") && intValue(model.getDuracao()) > 0;
		
		result &= isFilled(model, model.getHoraIni(), "hora esta vazia") && isValidHoras(model.getHoraIni());
		
		result &= isFilled(model, model.getNomeAula(), "nomeAUla esta vazio");
		
		return result;
	}



	private boolean isValidHoras(String horaIni) {
		return horaIni.matches(("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]"));
	}



	private CriarAulaModel createHelper(HttpServletRequest request) {
		// Create the object model
		CriarAulaModel model = new CriarAulaModel();
		model.setHandler(aulasService);

		// fill it with data from the request
		model.setDias(request.getParameter("dias"));
		model.setDuracao(request.getParameter("duracao"));
		model.setHoraIni(request.getParameter("horaIni"));
		model.setModalidade(request.getParameter("modalidade"));
		model.setNomeAula(request.getParameter("nomeAula"));
		
		return model;
	}	
}
