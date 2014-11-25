package com.tsystems.daos;

import com.tsystems.Util.HibernateUtil;
import com.tsystems.model.Wagon;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component("wagonDAO")
public class WagonDAO implements DAOInterface<Wagon> {

    Session session;

    public WagonDAO() {
        session = HibernateUtil.getSession();
    }

    @Override
    public void persist(Wagon item) {
        session.save(item);
    }

    @Override
    public Wagon read(int id) {
        Wagon w = (Wagon) session.get(Wagon.class, id);
        return w;
    }

    @Override
    public void update(Wagon item) {

        session.update(item);
    }

    @Override
    public void delete(Wagon item) {
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

    public Wagon getWagon(String registrationNumber) {
        SQLQuery query = session.createSQLQuery("SELECT * FROM wagon WHERE REGISTRATION_NUMBER = :registrationNumber");
        query.addEntity(Wagon.class);
        query.setParameter("registrationNumber", registrationNumber);
        List<Wagon> tempList = query.list();
        if (tempList.size() != 0) {
            return (Wagon) query.list().get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Wagon> getAll() {
        SQLQuery all = session.createSQLQuery("SELECT * from wagon");
        all.addEntity(Wagon.class);
        List allWagons = all.list();
        return allWagons;
    }
}