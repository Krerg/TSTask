package com.tsystems.daos;

import com.tsystems.Util.HibernateUtil;
import com.tsystems.model.Item;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component("itemDAO")
public class ItemDAO implements DAOInterface<Item> {

    private Session session;

    public ItemDAO() {
        session = HibernateUtil.getSession();
    }

    @Override
    public void persist(Item item) {
        session.save(item);
    }

    @Override
    public Item read(int id) {
        Item i = (Item) session.get(Item.class, id);
        return i;
    }

    @Override
    public void update(Item item) {
        session.persist(item);
    }

    @Override
    public void delete(Item item) {
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

    public Item getItem(String name) {
        SQLQuery query = session.createSQLQuery("SELECT * from item WHERE ITEM_NAME = :itemName");
        query.addEntity(Item.class);
        query.setParameter("itemName", name);
        return (Item) query.list().get(0);
    }

    @Override
    public List<Item> getAll() {
        SQLQuery all = session.createSQLQuery("SELECT  * from item");
        all.addEntity(Item.class);
        List<Item> allItems = all.list();
        return allItems;
    }
}