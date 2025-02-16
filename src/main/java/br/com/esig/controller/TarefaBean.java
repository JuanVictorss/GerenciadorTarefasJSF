package br.com.esig.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.esig.model.Tarefa;
import br.com.esig.repository.Tarefas;

@Named
@ViewScoped
public class TarefaBean implements Serializable {

    private static final long serialVersionUID = 1L; 

    private Tarefa tarefa = new Tarefa();
    
    @Inject
    private Tarefas tarefas;
    
    private List<Tarefa> listaTarefas;
    
    @PostConstruct
    public void init() {
        listaTarefas = tarefas.todas();
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    
    public void cadastrar() {
        System.out.println("Titulo: " + tarefa.getTitulo() + 
                           " | Descrição: " + tarefa.getDescricao() + 
                           " | Responsável: " + tarefa.getResponsavel() + 
                           " | Prioridade: " + tarefa.getPrioridade() + 
                           " | Deadline: " + tarefa.getDeadline());
    }
    
    public List<Tarefa> getListaTarefas() {
        return listaTarefas;
    }
    
    public void excluirTarefa() {
    	tarefas.remover(tarefa);
    }

}
