package es.beebusiness.dao;

import java.io.Serializable;

public interface BaseGenericDAO<T, K extends Serializable> {
	T create(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(K key);
}
