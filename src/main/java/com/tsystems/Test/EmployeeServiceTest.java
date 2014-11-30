package com.tsystems.Test;

import com.tsystems.Util.HibernateUtil;
import com.tsystems.daos.DriverDAO;
import com.tsystems.daos.OrderDAO;
import com.tsystems.daos.WagonDAO;
import com.tsystems.services.EmployeeService;
import org.junit.*;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceTest{

    public static final String SUCCES_ORDER_ADD = "Succesful order adding";
    public static final String ERROR_ORDER_ADD = "Error while adding order";
    public static final String EXISTING_ORDER = "Existing order in DB";

    public static final String SUCESS_WAGON_ADD = "Succesful wagon adding";
    public static final String EXISTING_WAGON_NUMBER = "Existing wagon on DB";
    public static final String ERROR_WAGON_ADD = "Error";
    public static final String INCORRECT_WAGON_NUMBER = "Incorrect wagon number";

    public static final String EXISTING_DRIVER_LICENSE = "Existing driver on DB";
    public static final String SUCCES_DRIVER_ADD = "Succesful driver adding";
    public static final String ERROR_DRIVER_ADD = "Error while driver adding";

    public static final String SUCCES_WAGON_ADD_TO_ORDER = "Successful wagon adding to order";
    public static final String ERROR_WAGON_ADD_TO_ORDER = "Error while adding wagon to order";
    public static final String NO_DRIVERS_AT_WAGON = "There are no drivers at this wagon";

    public static final String ERROR_DRIVER_ADD_TO_WAGON = "Error while adding driver to wagon";
    public static final String SUCCES_DRIVER_ADD_TO_WAGON = "Successful driver adding to wagon";
    public static final String TOO_MANY_PEOPLE_IN_WAGON = "Too many people in wagon";

    public static final String ERROR_FINISH_ORDER = "Error while finishing order";
    public static final String DRIVER_AT_WAGON = "There is a driver at wagon wheel";
    public static final String SUCCES_FINISH_ORDER = "Successful finishing order";

    public static final String SUCCES_ITEM_ADD = "Successful item adding";
    public static final String ERROR_ITEM_ADD = "Error while creating item";

    public static final String SUCCES_ITEM_ADD_TO_ORDER = "Successful item adding to order";
    public static final String ERROR_ITEM_ADD_TO_ORDER = "Error while adding item to order";
    public static final String TOO_MUCH_WEIGHT = "Too much weight";
    public static final String ALREADY_DELIVERED = "Item already delivered";

    public static final String ORDER_DELIVERED = "Order already delivered";

    public static final String DELETE_DRIVER_SCRIPT = "DELETE FROM DRIVER;";
    public static final String DELETE_WAGON_SCRIPT = "DELETE FROM WAGON;";
    public static final String DELETE_ORDER_SCRIPT = "DELETE FROM ORDER_;";
    public static final String DELETE_ITEM_SCRIPT = "DELETE FROM ITEM;";


    @Autowired
    private EmployeeService employeeServices;

    @Before
    public void init() {
        employeeServices = new EmployeeService();
        employeeServices.dDAO = new DriverDAO();
        employeeServices.wDAO = new WagonDAO();
        employeeServices.oDAO = new OrderDAO();
        HibernateUtil.startTransaction();
        HibernateUtil.getSession().createSQLQuery(DELETE_DRIVER_SCRIPT).executeUpdate();
        HibernateUtil.getSession().createSQLQuery(DELETE_ORDER_SCRIPT).executeUpdate();
        HibernateUtil.getSession().createSQLQuery(DELETE_ITEM_SCRIPT).executeUpdate();
        HibernateUtil.getSession().createSQLQuery(DELETE_WAGON_SCRIPT).executeUpdate();
        HibernateUtil.commitTransaction();
    }

    @Test
    public void driverAddSucces()
    {
        Assert.assertEquals(employeeServices.addDriver(12345678,"Mylnikov","Alexander","Igorevich").getMessage(), SUCCES_DRIVER_ADD);
    }

    @Test
    public void driverAddError() {
        Assert.assertEquals(employeeServices.addDriverToWagon("1234",12345).getMessage(), ERROR_DRIVER_ADD_TO_WAGON);
    }

    @Test
    public void wagonAddSucces() {
        Assert.assertEquals(employeeServices.addWagon("PP12345",3,3).getMessage(), SUCESS_WAGON_ADD);
    }



    @Test
    public void addOrderSucces() {
        Assert.assertEquals(employeeServices.addOrder(5678).getMessage(), SUCCES_ORDER_ADD);
    }

    @Test
    public void driverAddIncorrect() {
        Assert.assertEquals(employeeServices.addWagon("YY12345678", 2, 2).getMessage(), INCORRECT_WAGON_NUMBER);
    }



}
