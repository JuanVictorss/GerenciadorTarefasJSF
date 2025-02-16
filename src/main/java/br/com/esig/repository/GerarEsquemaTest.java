package br.com.esig.repository;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.esig.model.Responsavel;
import br.com.esig.model.Tarefa;

public class GerarEsquemaTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Esig");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        try {
            em.getTransaction().begin();

            TypedQuery<Responsavel> query = em.createQuery("SELECT r FROM Responsavel r", Responsavel.class);
            List<Responsavel> responsaveis = query.getResultList();
            
            if (responsaveis.isEmpty()) {
                Responsavel maria = new Responsavel("Maria");
                Responsavel joao = new Responsavel("João");
                Responsavel ana = new Responsavel("Ana");
                Responsavel carlos = new Responsavel("Carlos");
                em.persist(maria);
                em.persist(joao);
                em.persist(ana);
                em.persist(carlos);
                responsaveis.add(maria);
                responsaveis.add(joao);
                responsaveis.add(ana);
                responsaveis.add(carlos);
            }

            Responsavel responsavelDev = responsaveis.get(0);
            Responsavel responsavelCompras = responsaveis.get(1);
            Responsavel responsavelRH = responsaveis.get(2);
            Responsavel responsavelFinanceiro = responsaveis.get(3);

            Tarefa tarefaDev = new Tarefa("Desenvolver API", "Criar API para integração com sistema X", 
                    responsavelDev, "ALTA", java.time.LocalDate.of(2025, 3, 1), true);
            Tarefa tarefaCompras = new Tarefa("Comprar equipamentos", "Comprar laptop e acessórios para equipe", 
                    responsavelCompras, "MÉDIA", java.time.LocalDate.of(2025, 2, 20), false);
            Tarefa tarefaRH = new Tarefa("Recrutamento de Desenvolvedores", "Entrevistar 5 candidatos", 
                    responsavelRH, "ALTA", java.time.LocalDate.of(2025, 3, 5), false);
            Tarefa tarefaFinanceiro = new Tarefa("Relatório Financeiro", "Preparar balanço trimestral", 
                    responsavelFinanceiro, "MÉDIA", java.time.LocalDate.of(2025, 2, 28), false);
            Tarefa tarefaMarketing = new Tarefa("Campanha Publicitária", "Criar estratégia para redes sociais", 
                    responsavelDev, "BAIXA", java.time.LocalDate.of(2025, 4, 10), false);
            
            em.persist(tarefaDev);
            em.persist(tarefaCompras);
            em.persist(tarefaRH);
            em.persist(tarefaFinanceiro);
            em.persist(tarefaMarketing);

            em.getTransaction().commit();
            System.out.println("✅ Tarefas adicionadas.");

            System.out.println("Tarefas no banco de dados:");
            listarTarefas(em);
            
            System.out.println("Pressione Enter para editar uma tarefa...");
            scanner.nextLine();

            editarTarefa(em, tarefaCompras.getNumero(), 
                "Comprar equipamentos", 
                "Comprar laptop, monitores, cadeiras ergonômicas e acessórios.",
                responsavelCompras, "BAIXA", java.time.LocalDate.of(2025, 2, 20), false);

            System.out.println("Tarefas no banco de dados após edição:");
            listarTarefas(em);

            System.out.println("Pressione Enter para deletar uma tarefa...");
            scanner.nextLine();

            em.getTransaction().begin();
            em.remove(tarefaDev);
            em.getTransaction().commit();
            System.out.println("Tarefa de desenvolvimento deletada: " + tarefaDev);

            System.out.println("Tarefas no banco de dados após remoção:");
            listarTarefas(em);

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            scanner.close();
        }
    }

    private static void listarTarefas(EntityManager em) {
        String jpql = "SELECT t FROM Tarefa t";
        TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        List<Tarefa> tarefas = query.getResultList();
        tarefas.forEach(System.out::println);
    }

    private static void editarTarefa(EntityManager em, Long numeroTarefa, String novoTitulo, 
        String novaDescricao, Responsavel novoResponsavel, String novaPrioridade, 
        java.time.LocalDate novaDeadline, boolean novoConcluido) {
        
        Tarefa tarefa = em.find(Tarefa.class, numeroTarefa);
        
        if (tarefa != null) {
            em.getTransaction().begin();
            tarefa.setTitulo(novoTitulo);
            tarefa.setDescricao(novaDescricao);
            tarefa.setResponsavel(novoResponsavel);  
            tarefa.setPrioridade(novaPrioridade);
            tarefa.setDeadline(novaDeadline);
            tarefa.setConcluido(novoConcluido);
            em.merge(tarefa);
            em.getTransaction().commit();
            System.out.println("Tarefa editada com sucesso: " + tarefa);
        } else {
            System.out.println("Tarefa não encontrada com o número: " + numeroTarefa);
        }
    }
}