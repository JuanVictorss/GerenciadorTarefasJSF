package br.com.esig.service;

import br.com.esig.model.Tarefa;
import br.com.esig.repository.TarefasRepository;
import br.com.esig.util.Transacional;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import javax.inject.Inject;

@Service
public class TarefaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TarefasRepository tarefas;

    @Transacional
    public void salvar(Tarefa tarefa) {
    	tarefas.guardar(tarefa);
    }

    @Transacional
    public void excluir(Tarefa tarefa) {
    	tarefas.remover(tarefa);
    }

}