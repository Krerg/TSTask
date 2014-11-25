package com.tsystems.services;

import com.tsystems.Util.ResultMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsystems.daos.DriverDAO;
import com.tsystems.daos.ItemDAO;
import com.tsystems.daos.OrderDAO;
import com.tsystems.daos.WagonDAO;
import com.tsystems.model.Driver;
import com.tsystems.model.Item;
import com.tsystems.model.Order;
import com.tsystems.model.Wagon;
import com.tsystems.Util.HibernateUtil;

import java.util.List;

/**
 * Created by User on 18.10.2014.
 */

@Service
public class EmployeeService {

    @Autowired
    private WagonDAO wDAO;

    @Autowired
    private DriverDAO dDAO;

    @Autowired
    private OrderDAO oDAO;

    @Autowired
    private ItemDAO iDAO;

    private static EmployeeService es;

    private static Logger logger = Logger.getLogger(EmployeeService.class);

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

    /**
     * Service constructor
     */
    public EmployeeService() {

    }

    /**
     * add a wagon to DB
     *
     * @param registrationNumber Wagon's registration number
     * @param numberOfDrivers    Max number of drivers in wagon
     * @param capacity           Wagon's capacity class (1,2,3)
     * @return information of adding wagon into db
     */
    public final ResultMessage addWagon(final String registrationNumber, final int numberOfDrivers, final int capacity) {
        logger.info("Add wagon " + registrationNumber);
        if (wDAO.getWagon(registrationNumber) != null) {

            return new ResultMessage(EXISTING_WAGON_NUMBER, false);
        } else {
            if (validateWagonNumber(registrationNumber)) {
                Wagon temp = new Wagon();
                temp.setRegistrationNumber(registrationNumber);
                temp.setRequiredNumberOfDrivers(numberOfDrivers);
                temp.setCapacityClass(capacity);
                temp.setOrder(null);
                try {
                    wDAO.persist(temp);
                    return new ResultMessage(SUCESS_WAGON_ADD, true);
                } catch (Exception e) {
                    logger.error("Error while persist wagon");
                    return new ResultMessage(ERROR_WAGON_ADD, false);
                }
            }
            return new ResultMessage(INCORRECT_WAGON_NUMBER, false);
        }
    }

    /**
     * adds a driver to db
     *
     * @param driverLicense driver's number of license
     * @param surname       drivers's surname
     * @param name          driver's name
     * @param patronymic    driver's patronymic
     * @return information of adding driver
     */
    public final ResultMessage addDriver(final int driverLicense, final String surname, final String name, final String patronymic) {
        logger.info("Add driver " + driverLicense);
        if (dDAO.getDriver(driverLicense) != null) {
            return new ResultMessage(EXISTING_DRIVER_LICENSE, false);
        } else {
            Driver temp = new Driver();
            temp.setSurname(surname);
            temp.setName(name);
            temp.setPatronymic(patronymic);
            temp.setDriverLicense(driverLicense);
            temp.setStatus(Driver.NOT_WORKING);
            temp.setInWagon(false);
            temp.setWagon(null);
            try {
                dDAO.persist(temp);
                return new ResultMessage(SUCCES_DRIVER_ADD, true);
            } catch (Exception e) {
                return new ResultMessage(ERROR_DRIVER_ADD, false);
            }

        }
    }

    /**
     * Adds an item to DB
     *
     * @param itemName item's name
     * @param weight   item's weight
     * @return information of adding item into DB
     */
    public final ResultMessage addItem(final String itemName, final int weight) {
        logger.info("Add item");
        Item temp = new Item();
        temp.setItemName(itemName);
        temp.setWeight(weight);
        temp.setItemStatus(Item.NOT_DELIVERED_STATUS);
        temp.setGps("");
        temp.setOrder(null);
        try {
            iDAO.persist(temp);
        } catch (Exception e) {
            logger.error("Error while persist item");
            return new ResultMessage(ERROR_ITEM_ADD, false);
        }
        return new ResultMessage(SUCCES_ITEM_ADD, true);
    }

    /**
     * @return List of all Orders
     */
    public  final List<Order> getAllOrders() {
        logger.info("Get all orders");
        return oDAO.getAll();
    }

    /**
     * @return List of all items
     */
    public final List<Item> getAllItems() {
        logger.info("Get all items");
        return iDAO.getAll();
    }

    /**
     * @return List of all wagons
     */
    public final List<Wagon> getAllWagons() {
        logger.info("Get all wagons");
        return wDAO.getAll();
    }

    /**
     * @return List of all drivers
     */
    public final List<Driver> getAllDrivers() {
        logger.info("Get all drivers");
        return dDAO.getAll();
    }

    /**
     * @param c character that will be validated
     * @return Logic state that tells number or character is input parameter
     */
    public final boolean validateNumber(final char c) {
        if (c >= '0')
            if (c <= '9') {
                return true;
            }
        return false;
    }

    /**
     * Verifies the wagon number
     *
     * @param wagonNumber Wagon's registration number
     * @return Logic state, if wagon number fit the standard returns true, else return false
     */
    public final boolean validateWagonNumber(final String wagonNumber) {

        if (7 != wagonNumber.length()) {
            return false;
        }
        if (validateNumber(wagonNumber.charAt(0)) || validateNumber(wagonNumber.charAt(1))) {
            return false;
        } else {
            for (int i = 2; i < wagonNumber.length(); i++) {
                if (!validateNumber(wagonNumber.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Adds an order to DB
     *
     * @param number Order's unique number
     * @return information of adding order into DB
     */
    public final ResultMessage addOrder(final int number) {
        logger.info("Add order " + number);
        if (oDAO.getOrder(number) != null) {
            return new ResultMessage(EXISTING_ORDER, false);
        }
        Order temp = new Order();
        temp.setNumber(number);
        temp.setItemList(null);
        temp.setOrderStatus("Created");
        try {
            oDAO.persist(temp);
            return new ResultMessage(SUCCES_ORDER_ADD, true);
        } catch (Exception e) {
            logger.error("Error while persisting Order");
            return new ResultMessage(ERROR_ORDER_ADD, false);
        }
    }

    /**
     * Adds a wagon to a order, if it possible
     *
     * @param orderNumber order's number
     * @param wagonNumber wagon's registration number
     * @return inrormation of adding wagon to order
     */
    public final ResultMessage addWagonToOrder(final int orderNumber, final String wagonNumber) {
        logger.info("Add wagon " + wagonNumber + " to Order " + orderNumber);
        Wagon wagonTemp = wDAO.getWagon(wagonNumber);
        Order orderTemp = oDAO.getOrder(orderNumber);

        if (orderTemp.getOrderStatus().equals(Order.DELIVERED_STATUS)) {
            return new ResultMessage(ORDER_DELIVERED, false);
        }

        if (wagonTemp == null || orderTemp == null || wagonTemp.getDriverList() == null || wagonTemp.getDriverList().size() == 0) {
            return new ResultMessage(NO_DRIVERS_AT_WAGON, false);
        }

        int summ = 0;
        int wagonCapacity = 0;
        for (Item i : orderTemp.getItemList()) {
            summ += i.getWeight();
        }

        switch (wagonTemp.getCapacityClass()) {
            case 0:
                wagonCapacity = 1000;
                break;
            case 1:
                wagonCapacity = 5000;
                break;
            case 2:
                wagonCapacity = 10000;
            default:
                break;
        }
        if (summ > wagonCapacity) {
            return new ResultMessage(TOO_MUCH_WEIGHT, false);
        }

        wagonTemp.setOrder(orderTemp);
        orderTemp.setWagon(wagonTemp);
        orderTemp.setOrderStatus(Order.SHIPPED_STATUS);
        wDAO.persist(wagonTemp);
        oDAO.persist(orderTemp);
        return new ResultMessage(SUCCES_WAGON_ADD_TO_ORDER, true);
    }

    /**
     * Adds driver to wagon if wagon is empty and if driver is not in other wagon
     *
     * @param wagonNumber wagon's registration number
     * @param driverID driver's license number
     * @return information about adding driver to wagon in string type
     */
    public final ResultMessage addDriverToWagon(final String wagonNumber, final int driverID) {
        logger.info("Add driver " + driverID + " to wagon " + wagonNumber);
        Wagon wagonTemp = wDAO.getWagon(wagonNumber);
        Driver driverTemp = dDAO.getDriver(driverID);

        if (wagonTemp == null || driverTemp == null) {
            return new ResultMessage(ERROR_DRIVER_ADD_TO_WAGON, false);
        }
        if (wagonTemp.getDriverList().size() >= wagonTemp.getRequiredNumberOfDrivers()) {
            return new ResultMessage(TOO_MANY_PEOPLE_IN_WAGON, false);
        }

        driverTemp.setStatus(Driver.AT_WORK_STATUS);
        driverTemp.setWagon(wagonTemp);
        wagonTemp.getDriverList().add(driverTemp);
        wDAO.update(wagonTemp);
        dDAO.update(driverTemp);
        return new ResultMessage(SUCCES_DRIVER_ADD_TO_WAGON, true);
    }

    /**
     * Adds item to order if item is not delivered and order is not closed
     *
     * @param orderNumber order's number
     * @param item item's name
     * @return result of adding item to order in string type
     */
    public final ResultMessage addItemToOrder(final int orderNumber, final String item) {
        logger.info("Add item " + item + " to order " + orderNumber);
        Order orderTemp = oDAO.getOrder(orderNumber);
        if (orderTemp.getOrderStatus().equals(Order.DELIVERED_STATUS)) {
            return new ResultMessage(ORDER_DELIVERED, false);
        }

        Item itemTemp = iDAO.getItem(item);

        if (orderTemp == null || itemTemp == null || orderTemp.getWagon() == null) {
            return new ResultMessage(ERROR_ITEM_ADD_TO_ORDER, false);
        }
        if (itemTemp.isItemStatus()) {
            return new ResultMessage(ALREADY_DELIVERED, false);
        }

        itemTemp.setOrder(orderTemp);
        orderTemp.getItemList().add(itemTemp);
        orderTemp.setOrderStatus(Order.CONFIRMED_STATUS);
        iDAO.update(itemTemp);
        oDAO.update(orderTemp);
        return new ResultMessage(SUCCES_ITEM_ADD_TO_ORDER, true);
    }

    /**
     * Finish order if all of the items are delivered
     *
     * @param orderNumber order's number
     * @return information about finishing order in string type
     */
    public final ResultMessage finishOrder(final int orderNumber) {
        logger.info("Finish order" + orderNumber);
        Order tempOrder = oDAO.getOrder(orderNumber);
        Wagon tempWagon = tempOrder.getWagon();
        if (tempOrder == null || tempWagon == null) {
            HibernateUtil.commitTransaction();
            return new ResultMessage(ERROR_FINISH_ORDER, false);
        }
        List<Driver> tempDrivers = tempWagon.getDriverList();
        for (Driver d : tempDrivers) {
            if (d.getStatus().equals(Driver.DRIVING_STATUS)) {
                HibernateUtil.commitTransaction();
                return new ResultMessage(DRIVER_AT_WAGON, false);
            }
        }
        tempWagon.setOrder(null);
        for (Driver d : tempDrivers) {
            d.setStatus(Driver.NOT_WORKING);
            d.setWagon(null);
            dDAO.update(d);
        }

        oDAO.update(tempOrder);
        wDAO.update(tempWagon);
        HibernateUtil.commitTransaction();
        return new ResultMessage(SUCCES_FINISH_ORDER, true);
    }
}
