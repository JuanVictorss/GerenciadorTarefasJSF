package br.com.esig.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.esig.model.Tarefa;
import br.com.esig.repository.Tarefas;
import br.com.esig.util.Transacional;

public class CadastrarTarefaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Tarefas tarefas;
	
	@Transacional
	public void salvar(Tarefa tarefa) {
		tarefas.guardar(tarefa);
	}
	
	@Transacional
	public void excluir(Tarefa tarefa) {
		tarefas.remover(tarefa);
	}
}
