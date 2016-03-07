package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import model.Produto;

public class ProdutoDAO extends GenericDAO<Produto> {
	public ProdutoDAO() {
		super();
	}

	private static final long serialVersionUID = 1L;

	public Produto find(Long id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RESTfulTCCPersistenceUnit");
		EntityManager entityManager = factory.createEntityManager();
		Produto produto1 = entityManager.find(Produto.class, id);
		factory.close();
		entityManager.close();
		return produto1;
	}

	public Long getIdAnteriorRegistro(Long id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RESTfulTCCPersistenceUnit");
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createQuery("SELECT p FROM Produto p WHERE p.id < :id ORDER BY id DESC ")
				.setMaxResults(1);
		query.setParameter("id", id);
		try {
			Produto p = (Produto) query.getSingleResult();
			factory.close();
			entityManager.close();
			return p.getId();
		} catch (NoResultException e) {
			factory.close();
			entityManager.close();
			e.printStackTrace();
		}
		return null;
	}

	public Long getIdProximoRegistro(Long id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RESTfulTCCPersistenceUnit");
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createQuery("SELECT p FROM Produto p WHERE p.id > :id ORDER BY id ASC")
				.setMaxResults(1);
		query.setParameter("id", id);
		try {
			Produto p = (Produto) query.getSingleResult();
			factory.close();
			entityManager.close();
			return p.getId();
		} catch (NoResultException e) {
			factory.close();
			entityManager.close();
			e.printStackTrace();
		}
		return null;
	}

	public Long getIdPrimeiroRegistro() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RESTfulTCCPersistenceUnit");
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createQuery("SELECT p FROM Produto p ORDER BY id ASC").setMaxResults(1);
		try {
			Produto p = (Produto) query.getSingleResult();
			factory.close();
			entityManager.close();
			return p.getId();
		} catch (NoResultException e) {
			factory.close();
			entityManager.close();
			e.printStackTrace();
		}
		return null;
	}

	public Long getIdUltimoRegistro() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RESTfulTCCPersistenceUnit");
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createQuery("SELECT p FROM Produto p ORDER BY id DESC").setMaxResults(1);
		try {
			Produto p = (Produto) query.getSingleResult();
			factory.close();
			entityManager.close();
			return p.getId();
		} catch (NoResultException e) {
			factory.close();
			entityManager.close();
			e.printStackTrace();
		}
		return null;
	}

	public List<Produto> findAll() {
		beginTransaction();
		CriteriaQuery cq = getEm().getCriteriaBuilder().createQuery();
		cq.select(cq.from(Produto.class));
		List<Produto> produtosRetorno = getEm().createQuery(cq).getResultList();
		commit();
		closeTransaction();
		return produtosRetorno;
	}

}
