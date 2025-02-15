package br.com.esig.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.esig.model.Responsavel;
import br.com.esig.model.Tarefa;

import java.util.List;

public class CamadaPersistencia {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Esig");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Tarefas tarefas = new Tarefas(em);

		List<Responsavel> responsaveis = em.createQuery("SELECT r FROM Responsavel r", Responsavel.class)
				.getResultList();
		Responsavel responsavel = responsaveis.isEmpty() ? null : responsaveis.get(0);

		List<Tarefa> listaDeTarefas = tarefas.pesquisar("");
		System.out.println(listaDeTarefas);

		Tarefa tarefa = new Tarefa();
		tarefa.setTitulo("trabalho esig");
		tarefa.setDescricao("Projeto gerenciaddor de tarefas");
		tarefa.setResponsavel(responsavel);
		tarefa.setPrioridade("ALTA");
		tarefa.setDeadline(java.time.LocalDate.of(2025, 02, 16));
		tarefa.setConcluido(false);

		tarefas.guardar(tarefa);

		em.getTransaction().commit();

		List<Tarefa> listaDeTarefas2 = tarefas.pesquisar("");
		System.out.println(listaDeTarefas2);

		em.close();
		emf.close();
	}

}
