package br.com.esig.repository;


import br.com.esig.model.Responsavel;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;

@Repository
public class ResponsaveisRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	@PersistenceContext
	private EntityManager manager;

	public ResponsaveisRepository() {

	}

	public ResponsaveisRepository(EntityManager manager) {
		this.manager = manager;
	}
	
    public List<Responsavel> pesquisar(String responsavel) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<Responsavel> criteriaQuery = criteriaBuilder.createQuery(Responsavel.class);
        Root<Responsavel> root = criteriaQuery.from(Responsavel.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("responsavel"), responsavel + "%"));

        TypedQuery<Responsavel> query = manager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public List<Responsavel> buscarTodos() {
        return manager.createQuery("SELECT r FROM Responsavel r", Responsavel.class).getResultList();
    }
    
    public Responsavel buscarPorId(Long id) {
        try {
            return manager.createQuery("SELECT r FROM Responsavel r WHERE r.id = :id", Responsavel.class)
                          .setParameter("id", id)
                          .getSingleResult();
        } catch (Exception e) {
            return null; 
        }
    }
}