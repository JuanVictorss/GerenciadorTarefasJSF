package br.com.esig.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.esig.model.Responsavel;

public class ResponsavelRepository {

    @Inject
    private EntityManager manager;

    public List<Responsavel> listarResponsaveis() {
        TypedQuery<Responsavel> query = manager.createQuery(
            "SELECT r FROM Responsavel r", Responsavel.class); 
        return query.getResultList();
    }
}
