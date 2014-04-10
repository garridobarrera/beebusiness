package es.beebusiness.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.IBaseGenericDAO;

@Transactional(propagation=Propagation.REQUIRED)
public abstract class AbstractBaseGenericDAOImpl<T, K extends Serializable> implements IBaseGenericDAO<T, K> {

    @PersistenceContext
    protected EntityManager em;

    protected Class<T> classType;

    public AbstractBaseGenericDAOImpl() {
        super();
        this.classType = (Class<T>)getParameterClass(getClass(), 0);
    }


    @Override
    public T create(T entity) {
        this.em.persist(entity);
        return entity;
    }


    @Override
    public T update(T entity) {
        return this.em.merge(entity);
    }


    @Override
    public void delete(T entity) {
        this.em.remove(entity);
    }

    @Transactional(readOnly=true)
    @Override
    public T findById(K key) {
        return this.em.find(classType, key);
    }
    
    @Transactional(propagation=Propagation.SUPPORTS)
    @Override
    public T getReference(K key){
    	return this.em.getReference(classType, key);
    }

    private static Class<?> getParameterClass(Class<?> clazz, int index) {
        return (Class<?>)(((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments()[index]);
    }
}