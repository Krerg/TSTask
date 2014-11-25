package com.tsystems.daos;

import com.tsystems.Util.HibernateUtil;
import com.tsystems.model.Driver;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component("driverDAO")
public class DriverDAO implements DAOInterface<Driver> {
    Session session;

    public DriverDAO() {
        session = HibernateUtil.getSession();
    }

    @Override
    public void persist(Driver item) {
        session.save(item);
    }

    @Override
    public Driver read(int id) {
        Driver d = (Driver) session.get(Driver.class, id);
        return d;
    }

    public Driver getDriver(int driverLicense) {
        SQLQuery Query = session.createSQLQuery("SELECT * FROM DRIVER WHERE DRIVER_LICENSE = :driverLicense");
        Query.addEntity(Driver.class);
        Query.setParameter("driverLicense", driverLicense);
        List<Driver> result = Query.list();
        if (result.size() != 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void update(Driver item) {
        session.update(item);
    }

    @Override
    public void delete(Driver item) {
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
    public List<Driver> getAll() {
        SQLQuery all = session.createSQLQuery("SELECT  * from driver");
        all.addEntity(Driver.class);
        List<Driver> allDrivers = all.list();
        return allDrivers;
    }
}





