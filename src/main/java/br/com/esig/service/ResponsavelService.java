package br.com.esig.service;

import br.com.esig.model.Responsavel;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;



public class ResponsavelService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager entityManager;

    public List<String> listarNomes() {
        TypedQuery<Responsavel> query = entityManager.createQuery("SELECT r FROM Responsavel r", Responsavel.class);
        List<Responsavel> responsaveis = query.getResultList();
        return responsaveis.stream().map(Responsavel::getNome).collect(Collectors.toList());
    }
    
    
}