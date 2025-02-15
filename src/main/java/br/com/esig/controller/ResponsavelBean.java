package br.com.esig.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.esig.model.Responsavel;
import br.com.esig.repository.ResponsavelRepository;

@Named
@RequestScoped
public class ResponsavelBean implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Responsavel responsavel;
    private List<Responsavel> responsaveis;
    
    @Inject
    private ResponsavelRepository responsaveisRepository;
    
    @PostConstruct
    public void init() {
    	responsavel = new Responsavel();
    	carregarResponsaveis();
    }
    
    public void carregarResponsaveis() {
    	responsaveis = responsaveisRepository.listarResponsaveis();
    }
    
    public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Responsavel> getResponsaveis() {
        return responsaveis;
    }
}
