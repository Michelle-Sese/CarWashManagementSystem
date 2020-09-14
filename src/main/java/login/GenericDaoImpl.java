package login;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * JPA implementation of the GenericDAO.
 * 
 * @author Mikebavon
 * 
 * @param <T>
 *            The persistent type
 * @param <ID>
 *            The primary key type
 */
public abstract class GenericDaoImpl<T, ID extends Serializable> {

	private final Class<T> persistentClass;
	
	@PersistenceContext
	private EntityManager em;
	
	private Session session;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public GenericDaoImpl(final Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	/**
	 * Set entity manager
	 *
	 * @param em {@link EntityManager}
	 */
	public void setEm(EntityManager em){
		this.em = em;
		this.session = (Session) this.em.getDelegate();
		
	}

	/**
	 * get entity manager
	 * @return {@link EntityManager}
	 */
	public EntityManager getEm(){
		return this.em;
	}

	/**
	 * Returns hibernate session
	 *
	 * @return
	 */
	public Session getSession() {
		return this.session;
	}

	/**
	 * Get the Class of the entity.
	 *
	 * @return the class
	 */
	public Class<T> getEntityClass() {
		return persistentClass;
	}

	/**
	 * save an entity. This can be either a INSERT or UPDATE in the database.
	 *
	 * @param entity the entity to save
	 *
	 * @return the saved entity
	 */
	public T save(T entity) throws Exception{
		entity = em.merge(entity);
		return entity;
	}

	/**
	 * delete an entity from the database.
	 *
	 * @param entity the entity to delete
	 */
	public void delete(T entity) throws Exception{
		em.remove(entity);
	}

	/**
	 * delete an entity by its primary key
	 *
	 * @param id the primary key of the entity to delete
	 */
	public void deleteById(final ID id) throws Exception{
		T entity = em.find(persistentClass, id);
		if(entity != null) em.remove(entity);
	}

	/**
	 * delete batch entities by their primary keys array
	 *
	 * @param ids [] the primary key of entities to delete
	 */
	public void delete(ID ids []) throws Exception{
		int size = ids.length;

		for(int idx = 0; idx < size; idx++){
			if(ids[idx] != null) {
				T entity = em.find(persistentClass, ids[idx]);
				if(entity != null)
					em.remove(entity);
			}
		}

	}

	/**
	 * Find an entity by its primary key
	 *
	 * @param id the primary key
	 * @return the entity
	 */
	public T findById(final UUID id) {
		final T result = em.find(persistentClass, id);
		return result;
	}

	/**
	 * Find using a named query.
	 *
	 * @param queryName the name of the query
	 * @param params the query parameters
	 *
	 * @return the list of entities
	 */
	public List<T> findByNamedQuery(final String queryName, Object... params) {
		javax.persistence.Query query = em.createNamedQuery(queryName);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		
		final List<T> result = (List<T>) query.getResultList();
		return result;
	}

	/**
	 * Find using a named query.
	 *
	 * @param queryName the name of the query
	 * @param params the query parameters
	 *
	 * @return the list of entities
	 */
	public List<T> findByNamedQuery(final String queryName, final Map<String, ? extends Object> params) {
		javax.persistence.Query query = em.createNamedQuery(queryName);

		for (final Map.Entry<String, ? extends Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		final List<T> result = (List<T>) query.getResultList();
		return result;
	}
	
}