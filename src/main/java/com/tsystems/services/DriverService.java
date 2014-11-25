package com.tsystems.services;


import com.tsystems.Util.ResultMessage;
import com.tsystems.daos.WagonDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsystems.daos.DriverDAO;
import com.tsystems.daos.ItemDAO;
import com.tsystems.daos.OrderDAO;
import com.tsystems.model.Driver;
import com.tsystems.model.Item;
import com.tsystems.model.Order;
import com.tsystems.model.Wagon;
import com.tsystems.Util.HibernateUtil;

import java.util.List;

@Service
public class DriverService {

    private static DriverService ds;

    @Autowired
    private DriverDAO dDAO;

    @Autowired
    private WagonDAO wDAO;

    @Autowired
    private OrderDAO oDAO;

    @Autowired
    private ItemDAO iDAO;

    private static Logger logger = Logger.getLogger(DriverService.class);

    private static final String SUCCES_DELIVER_ITEM = "Succesful delivery of item";
    private static final String SUCCES_FINISH_ORDER = "Succesful delivery of order";

    private static final String ERROR_CHANGE_STATUS = "Can not change status";
    private static final String SUCCES_CHANGE_STATUS = "Driver status has been changed";
    private static final String DRIVER_AT_WAGON = "The wagon has a driver already";

    private static final String MISMATCH_ORDER_ITEM = "Mismatch order/item";
    private static final String NO_WAGON_ATTACHED_ORDER = "There is no wagon attached to this order";
    private static final String MISMATCH_DRIVER_ORDER = "Mismatch driver/order";
    private static final String ERROR_DELIVER_ITEM = "No such order/driver/item in DB";

    /**
     * Service's constructor
     */
    public DriverService()
    {

    }

    /**
     * Gets a full information for driver, such as order information and co-drivers
     *
     * @param driverLicense number of driver license
     * @return Information in String type
     */
    public final ResultMessage getInformationForDriver(final int driverLicense)
    {
        logger.info("getInformarion for driver " + driverLicense);
        Driver tempDriver = dDAO.getDriver(driverLicense);
        if (tempDriver == null) {
            return new ResultMessage(null, false);
        } else {
            return new ResultMessage(tempDriver.fullInfo(), true);
        }
    }

    /**
     *
     * Delivers an item if it has a driver and order, and if order has a status "Shipped"
     *
     * @param itemID item ID in DB
     * @param driverLicense number of driver license
     * @param orderNumber Unique number of order
     * @return result of delivering item
     */
    public final ResultMessage deliverItem(final int itemID, final int driverLicense, final int orderNumber)
    {
        logger.info(new StringBuilder().append("Deliver item ").append(itemID).append(" ").append(driverLicense).toString());
            Item tempItem = iDAO.read(itemID);
            if (tempItem == null) {
                return new ResultMessage(ERROR_DELIVER_ITEM, false);
            }
            Driver tempDriver = dDAO.getDriver(driverLicense);
            Order tempOrderIN = oDAO.getOrder(orderNumber);
            Order tempOrder = tempItem.getOrder();
            if (tempDriver == null || tempOrder == null || tempOrderIN == null) {
                return new ResultMessage(ERROR_DELIVER_ITEM, false);
            }
            if (tempOrderIN.getNumber() != tempOrder.getNumber()) {
                return new ResultMessage(MISMATCH_ORDER_ITEM, false);
            }
            if (tempOrder.getWagon() == null) {
                return new ResultMessage(NO_WAGON_ATTACHED_ORDER, false);
            }
            for (Driver d : tempOrder.getWagon().getDriverList()) {
                if (d.getDriverLicense() == driverLicense) {
                    tempItem.setItemStatus(true);
                    List<Item> items = tempOrder.getItemList();
                    for (Item e : items) {
                        if (!e.isItemStatus()) {
                            return new ResultMessage(SUCCES_DELIVER_ITEM, true);
                        }
                    }
                    tempOrder.setOrderStatus(Order.CLOSED_STATUS);
                    iDAO.update(tempItem);
                    oDAO.update(tempOrder);
                    return new ResultMessage(SUCCES_FINISH_ORDER, true);
                }
            }
        return new ResultMessage(MISMATCH_DRIVER_ORDER, false);
    }

    /**
     *
     * Changes a driver status if a driver is working
     *
     * @param driverLicense number of driver license
     * @param status driver status that we will want to apply
     * @return information if changing status
     */
    public final ResultMessage changeStatus(final int driverLicense, final String status)
    {
            logger.info("Change status Driver:" + driverLicense);
            Driver driverTemp = dDAO.getDriver(driverLicense);
            Wagon wagonTemp = driverTemp.getWagon();
            if (wagonTemp == null) {
                return new ResultMessage(ERROR_CHANGE_STATUS, false);
            }
            Order orderTemp = wagonTemp.getOrder();
            if (orderTemp == null) {
                return new ResultMessage(ERROR_CHANGE_STATUS, false);
            }
            if (status.equals(Driver.DRIVING_STATUS)) {
                for (Driver d : wagonTemp.getDriverList()) {
                    if (d.getStatus().equals(Driver.DRIVING_STATUS)) {
                        return new ResultMessage(DRIVER_AT_WAGON, false);
                    }
                }
            }
            driverTemp.setStatus(status);
            dDAO.update(driverTemp);
            return new ResultMessage(SUCCES_CHANGE_STATUS, true);
        }
    }
