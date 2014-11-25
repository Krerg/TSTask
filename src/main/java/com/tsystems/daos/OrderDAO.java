package com.tsystems.daos;

import com.tsystems.Util.HibernateUtil;
import com.tsystems.model.Order;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component("orderDAO")
public class OrderDAO implements DAOInterface<Order> {

    Session session;

    public OrderDAO() {
        session = HibernateUtil.getSession();
    }

    @Override
    public void persist(Order item) {
        session.save(item);
    }

    @Override
    public Order read(int id) {
        Order o = (Order) session.get(Order.class, id);
        return o;
    }

    @Override
    public void update(Order item) {
        session.persist(item);
    }

    @Override
    public void delete(Order item) {
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
    public List<Order> getAll() {
        SQLQuery all = session.createSQLQuery("SELECT * FROM order_");
        all.addEntity(Order.class);
        List<Order> allOrders = all.list();
        return allOrders;
    }

    public Order getOrder(int orderNumber) {
        SQLQuery query = session.createSQLQuery("SELECT * FROM order_ WHERE NUMBER = :orderNumber");
        query.addEntity(Order.class);
        query.setParameter("orderNumber", orderNumber);
        List<Order> list = query.list();
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }


}