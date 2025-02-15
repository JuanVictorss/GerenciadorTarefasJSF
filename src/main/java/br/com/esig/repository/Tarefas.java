package br.com.esig.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.esig.model.Tarefa;

public class Tarefas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Tarefas() {
		
	}	
	
	public Tarefas(EntityManager manager) {
		this.manager = manager;
	}
	
	public Tarefa porNumero(Long numero) {
		return manager.find(Tarefa.class, numero);
	}
	
	public List<Tarefa> pesquisar(String tituloPesquisado) {
		TypedQuery<Tarefa> query = manager.createQuery("from Tarefa where titulo like :titulo", Tarefa.class);
		
		query.setParameter("titulo", tituloPesquisado + "%");
		
		return query.getResultList();
	}
	
	public Tarefa guardar(Tarefa tarefa) {
		return manager.merge(tarefa);
	}

	public void remover(Tarefa tarefa) {
		tarefa = porNumero(tarefa.getNumero());
		manager.remove(tarefa);
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	
}
