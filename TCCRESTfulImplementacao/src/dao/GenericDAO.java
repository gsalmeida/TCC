package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("RESTfulTCCPersistenceUnit");
	private EntityManager em;
	private Class<T> classeEntidade;

	public void beginTransaction() {
		setEm(emf.createEntityManager());
		getEm().getTransaction().begin();
	}

	public void commit() {
		getEm().getTransaction().commit();
	}

	public void rollback() {
		getEm().getTransaction().rollback();
	}

	public void closeTransaction() {
		getEm().close();
	}

	public void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	public void flush() {
		getEm().flush();
	}

	public void joinTransaction() {
		setEm(emf.createEntityManager());
		getEm().joinTransaction();
	}

	public GenericDAO() {
	}

	public GenericDAO(Class<T> classeEntidade) {
		this.setClasseEntidade(classeEntidade);
	}

	public void save(T classeEntidade) {
		getEm().persist(classeEntidade);
	}

	public void delete(Class<T> classeEntidade, Long id) {
		T entidadeParaRemover = getEm().find(classeEntidade, id);
		getEm().remove(entidadeParaRemover);
	}

	public T update(T classeEntidade) {
		return getEm().merge(classeEntidade);
	}

	public T find(int idEntidade) {
		return getEm().find(getClasseEntidade(), idEntidade);
	}

	public T findReferenceOnly(int idEntidade) {
		return getEm().getReference(getClasseEntidade(), idEntidade);
	}

	// Using the unchecked because JPA does not have a
	// em.getCriteriaBuilder().createQuery()<T> method
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = getEm().getCriteriaBuilder().createQuery();
		cq.select(cq.from(getClasseEntidade()));
		return getEm().createQuery(cq).getResultList();
	}

	// Using the unchecked because JPA does not have a
	// query.getSingleResult()<T> method
	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parametros) {
		T resultado = null;
		try {
			Query query = getEm().createNamedQuery(namedQuery);
			// Method that will populate parameters if they are passed not null
			// and empty
			if (parametros != null && !parametros.isEmpty()) {
				populateQueryParameters(query, parametros);
			}
			resultado = (T) query.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return resultado;
	}

	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

	public Class<T> getClasseEntidade() {
		return classeEntidade;
	}

	// não verifica a conversão. o cast. Por isso usa-se
	// @SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	public void setClasseEntidade(Object classeEntidade) {
		this.classeEntidade = (Class<T>) classeEntidade;
	}

	public void setClasseEntidade(Class<T> classeEntidade) {
		this.classeEntidade = classeEntidade;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}