package presentation.web.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import auxiliarObjects.AulaDTOAux;
import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IInscreverServiceRemote;

public class InscreverAulaModel extends Model{
	
	private static final List<String> TIPOS_DE_INSCRICAO = Arrays.asList("Regular","Avulso");
	
	private List<AulaDTOAux> aulas;
	private String modalidade;
	private String inscricao;
	private String nomeAula;
	private String numUtente;
	private String diaSemana;
	
	private IInscreverServiceRemote inscreverService;

	public void setHandler(IInscreverServiceRemote inscreverService) {
		this.inscreverService = inscreverService;
		
	}
	
	public void setAulasB(){
		try {
			aulas = new LinkedList<>();
			AulaDTO[] aul = inscreverService.inscAula(modalidade, inscricao);
			StringBuilder dias;
			for (AulaDTO aulaDTO : aul) {
				dias = new StringBuilder();
				for (String d : aulaDTO.getDiasSemana()) {
					dias.append(d+" ");
				}
				aulas.add(new AulaDTOAux(aulaDTO.getNome(), aulaDTO.getHorario(), dias.toString(), aulaDTO.getNomeInst(), aulaDTO.getVagas()));
			}
		} catch (ApplicationException e) {
			aulas =  new LinkedList<>();
		}
		
	}
	
	public Iterable<ModalidadeDTO> getModalidades() {
		try {
			return inscreverService.getModalidades();
		} catch (ApplicationException e) {
			return new ArrayList<>();
		}
	}
	
	public Iterable<AulaDTOAux> getAulas(){
		
		return aulas;
	}
	
	public Iterable<String> getTiposDeInscricao() {
		return TIPOS_DE_INSCRICAO;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNomeAula() {
		return nomeAula;
	}

	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}

	public String getNumUtente() {
		return numUtente;
	}

	public void setNumUtente(String numUtente) {
		this.numUtente = numUtente;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public IInscreverServiceRemote getInscreverService() {
		return inscreverService;
	}

	public void setInscreverService(IInscreverServiceRemote inscreverService) {
		this.inscreverService = inscreverService;
	}

	public void clearFields() {
		numUtente = nomeAula = diaSemana = "";
		
	}

	
}
