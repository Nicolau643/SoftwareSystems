package client;

import application.AulasService;
import application.InscreverService;
import business.AulaDTO;
import business.TodaysDate;
import facade.exceptions.ApplicationException;
import facade.startup.AulasGes;

public class SimpleClientAulasGes {

	public static void main(String[] args) throws ApplicationException {
		AulasGes app = new AulasGes();
		
			final int regular = 0;
			final int avulso = 1;
			app.run();
			
			InscreverService inscService = app.getInscreverService();
			inscService.populate();
			AulasService aulas = app.getAulasService();
			
			int[] d = {2,4};
			int[] d1 = {1,3,5};
			
			//1º caso de uso
			aulas.criarAula("Pilates", "PLT001",d , "09:15", 55);
			aulas.criarAula("Pilates", "PLT002",d , "12:15",55);
			aulas.criarAula("Pilates", "PLT003",d1 , "12:15",55);
			//aulas.criarAula("GAP", "GAP001",d1 , "09:45",45);
			aulas.criarAula("GAP", "GAP001",d1 , "09:45",50);
			aulas.criarAula("Step", "STP001",d1 , "09:15",45);
			//2ªcaso de uso
			aulas.ativaAula("Estudio 1", "PLT001", TodaysDate.diaCorrente.toString(), "2020-07-31", 2);
			aulas.ativaAula("Estudio 1", "PLT002", TodaysDate.diaCorrente.toString(), "2020-07-31", 2);
			aulas.ativaAula("Estudio 1", "GAP001", TodaysDate.diaCorrente.toString(), "2020-07-31", 2);
			//aulas.ativaAula("Estudio 2", "STP001", TodaysDate.diaCorrente.toString(), "2020-07-31", 2);
			//aulas.ativaAula("Estudio 1", "STP001", TodaysDate.diaCorrente.toString(), "2020-07-31", 2);
			//3ºcaso de uso
			System.out.println("---------\nModalidades disponiveis\n---------");
			for (String i : inscService.getModalidades()) {
				System.out.println(i);
			}
			System.out.println("------------------");
			
			///12
			AulaDTO[] as = inscService.inscAula("Pilates", regular);
			
			for (AulaDTO a : as) {
				System.out.println(a);
			}
			
			inscService.escolhe(regular, as[0], 1);
			
			System.out.println("------------------");
			
			///13
			as = inscService.inscAula("Pilates", regular);
			
			for (AulaDTO a : as) {
				System.out.println(a);
			}
			
			inscService.escolhe(regular, as[1], 3);
			
			System.out.println("------------------");
			
			///14
			as = inscService.inscAula("Pilates", regular);
			
			for (AulaDTO a : as) {
				System.out.println(a);
			}
			
			inscService.escolhe(regular, as[0], 2);
			
			System.out.println("------------------");
			
			///15
			as = inscService.inscAula("Pilates", avulso);
			
			for (AulaDTO a : as) {
				System.out.println(a.toStringAvulso());
			}
			
			inscService.escolhe(avulso, as[0], 4);
			
			System.out.println("------------------");
		
			///16
			as = inscService.inscAula("Pilates", avulso);
			
			if (as.length == 0) {
				System.out.println("Nao ha aulas");
			}else {
				for (AulaDTO a : as) {
					System.out.println(a);
				}
			}
			
			System.out.println("------------------");
			
			///17
			as = inscService.inscAula("Pilates", regular);
			
			if (as.length == 0) {
				System.out.println("Nao ha aulas");
			}else {
				for (AulaDTO a : as) {
					System.out.println(a);
				}
			}
			
			inscService.escolhe(regular, as[0], 5);
			
			System.out.println("------------------");
			
			
			as = aulas.getOcup("Estudio 1", TodaysDate.diaCorrente.plusDays(1).toString());
			
			for (AulaDTO aulaDTO : as) {
				System.out.println(aulaDTO.toStringOcup());
			}
			
			System.out.println("ola");
			
	}
	
}
