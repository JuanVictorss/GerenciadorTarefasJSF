package br.com.esig.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;

import javax.inject.Named;

import br.com.esig.model.Prioridade;
import br.com.esig.model.Responsavel;
import br.com.esig.model.Tarefa;

import br.com.esig.repository.ResponsavelRepository;
import br.com.esig.repository.TarefaRepository;

import br.com.esig.service.TarefaService;
import br.com.esig.util.FacesMessages;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;


@Named
@ViewScoped
public class TarefasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private TarefaService tarefaService;

    private List<Tarefa> listaTarefas;

    private List<Responsavel> listaResponsaveis;

    private String termoPesquisa;

    private Converter responsavelConverter;
        
    private Tarefa tarefa;
    
    private int idResponsavel;
    
    public void prepararNovaTarefa() {
    	tarefa = new Tarefa();
    }


    public void prepararEdicao(){Long id = tarefa.getId();}

    public void salvar() throws IOException {
        if(tarefa.getResponsavel() == null){
            tarefa.setResponsavel(new Responsavel());
            tarefa.getResponsavel().setId(idResponsavel);
        }
        tarefaRepository.save(tarefa);
        
        atualizarRegistros();
    }
    
    public void excluir() throws IOException {
        tarefaRepository.deleteById(tarefa.getId());
        tarefa = null;
        
        atualizarRegistros();
    }
    
    public void pesquisar() {
        termoPesquisa = "%" + termoPesquisa + "%";
    	listaTarefas = tarefaRepository.pesquisar(termoPesquisa);

    }
    
    public void todasTarefas() {
    	listaTarefas = tarefaRepository.findAll();
    }
    
    public void carregarResponsaveis(){
    	listaResponsaveis = responsavelRepository.findAll();
    }
    
    public List<Responsavel> getResponsaveis(){
    	return listaResponsaveis;
    }


    private void atualizarRegistros() throws IOException {
        if (jaHouvePesquisa()) {
            pesquisar();
        } else {
        	todasTarefas();
        }
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("GestaoTarefas.xhtml");
    }
    
    private boolean jaHouvePesquisa() {
        return termoPesquisa != null && !"".equals(termoPesquisa);
    }
    
    public List<Tarefa> getListaTarefas() {
        return listaTarefas;
    }
    
    public String getTermoPesquisa() {
        return termoPesquisa;
    }
    
    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }
    
    public Prioridade[] getTiposTarefa() {
        return Prioridade.values();
    }
    
    public Converter getResponsaveisConverter() {
        return responsavelConverter;
    }
    
    public Tarefa getTarefa() {
        return tarefa;
    }
    
    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    
    public boolean isTarefaSeleciona() {
        return tarefa != null && tarefa.getId() != null;
    }

	public int getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(int idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

}