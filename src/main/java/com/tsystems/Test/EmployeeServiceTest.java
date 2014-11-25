//package ru.tsystems.Freight.Test;

/*import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.tsystems.Freight.Entity.Driver;
import ru.tsystems.Freight.Services.DriverService;
import ru.tsystems.Freight.Services.EmployeeService;

/**
 * Created by User on 27.10.2014.
 */
/*
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.tsystems.Freight.Services.EmployeeService;

public class EmployeeServiceTest {

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

    private EmployeeService employeeServices;

    @Before
    public void init() {
        employeeServices = EmployeeService.getService();
    }
    @Test
    public void test1() {
        Assert.assertEquals(employeeServices.addDriverToWagon("1234",12345),ERROR_DRIVER_ADD_TO_WAGON);
    }

    @Test
    public void test2() {
        Assert.assertEquals(employeeServices.addOrder(5678),SUCCES_ORDER_ADD);
    }

    @Test
    public void test3() {
        Assert.assertEquals(employeeServices.addWagon("YY12345678", 2, 2), ERROR_WAGON_ADD);
    }



}
*/