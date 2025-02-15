package br.com.esig.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.esig.model.Tarefa;

@Named
@ViewScoped
public class TarefaBean implements Serializable {

    private static final long serialVersionUID = 1L; 

    private Tarefa tarefa;

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
}
