/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Abstract DAO class.
 * @author alexis
 * @param <T> The entity class
 */
public abstract class AbstractDAO<T> {
    
    private final Class<T> entityClass;
        
    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;    
    }
    
    protected abstract EntityManager getEntityManager();
    
    public T create(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public T edit(T entity) {
        T ent = getEntityManager().merge(entity);
        return ent;
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    public void removeById(Object id) {
        T entity = getById(id);
        if (entity != null) {
            remove(entity);
        }
    }

    public T getById(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public List<T> executeNamedQuery(String query) {
        return getEntityManager().createNamedQuery(query, entityClass).getResultList();
    }
    
}
