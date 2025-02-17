package br.com.esig.repository;

import br.com.esig.model.Tarefa;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


@Repository
public class TarefasRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public TarefasRepository() {

	}

	public TarefasRepository(EntityManager manager) {
		this.manager = manager;
	}

	public Tarefa porId(Long id) {
		return manager.find(Tarefa.class, id);
	}

	public List<Tarefa> pesquisar(String nome) {
		String jpql = "from Tarefa where titulo like :titulo";
		
		TypedQuery<Tarefa> query = manager
				.createQuery(jpql, Tarefa.class);
		
		query.setParameter("titulo", nome + "%");
		
		return query.getResultList();
	}
	
	public List<Tarefa> todas() {
         return manager.createQuery("from Tarefa", Tarefa.class).getResultList();
    }

	public Tarefa guardar(Tarefa tarefas) {
		return manager.merge(tarefas);
	}

	public void remover(Tarefa tarefas) {
		tarefas = porId(tarefas.getId());
		manager.remove(tarefas);
	}
}