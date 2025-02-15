package br.com.esig.repository;


import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.esig.model.Tarefa;

public class GerarEsquemaTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Esig");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in); // Scanner para ler a entrada do usuário

        try {
            // Adicionar duas tarefas: uma de desenvolvimento e outra de compras
            em.getTransaction().begin();

            Tarefa tarefaDev = new Tarefa("Desenvolver API", "Criar API para integração com sistema X", 
                    "Maria", "ALTA", java.time.LocalDate.of(2025, 03, 01), true);
            Tarefa tarefaCompras = new Tarefa("Comprar equipamentos", "Comprar laptop e acessórios para equipe de desenvolvimento", 
                    "João", "MÉDIA", java.time.LocalDate.of(2025, 02, 20), false);

            // Persistir as tarefas
            em.persist(tarefaDev);
            em.persist(tarefaCompras);

            em.getTransaction().commit();
            System.out.println("✅ Tarefas adicionadas.");

            // Listar todas as tarefas
            System.out.println("Tarefas no banco de dados:");
            listarTarefas(em);
            
            System.out.println("Pressione Enter para editar a tarefa de desenvolvimento...");
            scanner.nextLine(); // Esperar o usuário apertar Enter

            // Editar a tarefa de compras (aumentar a descrição e diminuir a prioridade)
            editarTarefa(em, tarefaCompras.getNumero(), 
                "Comprar equipamentos", 
                "Comprar laptop, monitores, cadeiras ergonômicas e acessórios para equipe de desenvolvimento, incluindo software de licenciamento.",
                "João", "BAIXA", java.time.LocalDate.of(2025, 02, 20), false);

            // Listar novamente as tarefas após edição
            System.out.println("Tarefas no banco de dados após edição:");
            listarTarefas(em);

            // Pedir para o usuário pressionar Enter antes de deletar a tarefa de dev
            System.out.println("Pressione Enter para deletar a tarefa de desenvolvimento...");
            scanner.nextLine(); // Esperar o usuário apertar Enter

            // Deletar a tarefa de desenvolvimento após pressionar Enter
            em.getTransaction().begin();
            em.remove(tarefaDev);
            em.getTransaction().commit();
            System.out.println("🗑️ Tarefa de desenvolvimento deletada: " + tarefaDev);

            // Listar novamente as tarefas após remoção
            System.out.println("Tarefas no banco de dados após remoção:");
            listarTarefas(em);

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("❌ Erro: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            scanner.close(); // Fechar o scanner
        }
    }

    private static void listarTarefas(EntityManager em) {
        String jpql = "SELECT t FROM Tarefa t";
        TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        List<Tarefa> tarefas = query.getResultList();
        tarefas.forEach(t -> System.out.println(t));
    }

    // Função para editar uma tarefa
    private static void editarTarefa(EntityManager em, Long numeroTarefa, String novoTitulo, 
        String novaDescricao, String novoResponsavel, String novaPrioridade, 
        java.time.LocalDate novaDeadline, boolean novoConcluido) {
        
        // Buscar a tarefa pelo numero
        Tarefa tarefa = em.find(Tarefa.class, numeroTarefa);
        
        if (tarefa != null) {
            // Iniciar transação
            em.getTransaction().begin();
            
            // Atualizar todos os campos da tarefa
            tarefa.setTitulo(novoTitulo);
            tarefa.setDescricao(novaDescricao);
            tarefa.setResponsavel(novoResponsavel);
            tarefa.setPrioridade(novaPrioridade);
            tarefa.setDeadline(novaDeadline);
            tarefa.setConcluido(novoConcluido);
            
            // Salvar as alterações
            em.merge(tarefa);
            
            // Confirmar a transação
            em.getTransaction().commit();
            
            System.out.println("✅ Tarefa editada com sucesso: " + tarefa);
        } else {
            System.out.println("❌ Tarefa não encontrada com o número: " + numeroTarefa);
        }
    }
}
