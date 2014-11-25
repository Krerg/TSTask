package com.tsystems.daos;

import org.hibernate.SQLQuery;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by User on 16.10.2014.
 */
public interface DAOInterface<T> {
    public void persist(T item);

    public T read(int id);

    public void update(T item);

    public void delete(T item);

    public SQLQuery nativeSQL(String SQL);

    public Query JPQLQuery(String JPQL);

    public List<T> getAll();
}
