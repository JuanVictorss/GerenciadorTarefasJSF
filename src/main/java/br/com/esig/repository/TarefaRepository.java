package br.com.esig.repository;

import br.com.esig.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("from Tarefa where titulo like :titulo")
    List<Tarefa> pesquisar(@Param("titulo") String nome);
}
