package com.tsystems.daos;

import com.tsystems.Util.HibernateUtil;
import com.tsystems.model.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by User on 21.10.2014.
 */

@Component("userDAO")
public class UserDAO implements DAOInterface<User> {

    private Session session;

    public UserDAO() {
        session = HibernateUtil.getSession();
    }

    @Override
    public void persist(User item) {
        session.persist(item);
    }

    @Override
    public User read(int id) {
        return (User) session.get(User.class, id);
    }

    @Override
    public void update(User item) {
        session.persist(item);
    }

    @Override
    public void delete(User item) {
        session.delete(item);
    }

    @Override
    public SQLQuery nativeSQL(String SQL) {
        return null;
    }

    @Override
    public Query JPQLQuery(String JPQL) {
        return null;
    }

    @Override
    public List<User> getAll() {
        SQLQuery all = session.createSQLQuery("SELECT  * from user");
        all.addEntity(User.class);
        List<User> allOrders = all.list();
        return allOrders;
    }

    public User getUser(String name) {
        SQLQuery sq = session.createSQLQuery("SELECT * from user WHERE NAME = :nameParameter ");
        sq.addEntity(User.class);
        sq.setParameter("nameParameter", name);
        List<User> lu = sq.list();
        if (lu.size() == 0) {
            return null;
        } else {
            return lu.get(0);
        }
    }
}
