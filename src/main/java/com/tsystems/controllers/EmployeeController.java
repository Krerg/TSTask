package com.tsystems.controllers;

import com.tsystems.Util.ResultMessage;
import com.tsystems.model.Driver;
import com.tsystems.model.Item;
import com.tsystems.model.Order;
import com.tsystems.model.Wagon;
import com.tsystems.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sasha_000 on 20.11.2014.
 */

@Controller
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    EmployeeService es;

    @RequestMapping(value = "/AddDriver", method = RequestMethod.POST)
    public String addDriver(ModelMap model, HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null, false);
        String addDriverResult = null;
        Object objectDriverLicense = request.getParameter("driverLicense");
        String name = (String) request.getParameter("name");
        String surname = (String) request.getParameter("surname");
        String patronymic = (String) request.getParameter("patronymic");
        if (objectDriverLicense.equals("") || name.equals("") || surname.equals("") || patronymic.equals("")) {
            addDriverResult = "Incorrect input";
            rs.setMessage(addDriverResult);
            rs.setSeccesful(false);
        } else {
            try {
                int driverLicense = Integer.parseInt((String) objectDriverLicense);
                rs = es.addDriver(driverLicense, surname, name, patronymic);
            } catch (Exception e) {
                addDriverResult = "There is an error";
                rs.setMessage(addDriverResult);
                rs.setSeccesful(false);
            }
        }
        request.setAttribute("operationResult", rs);
        formPage(request);
        return "employeePage";
    }

    @RequestMapping(value = "/AddWagon", method = RequestMethod.POST)
    public String addWagon(ModelMap model, HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null, false);
        String addWagonResult = null;
        String registrationNumber = request.getParameter("registrationNumber");
        Object ocapacityClass = request.getParameter("capacityClass");
        Object onumberDrivers = request.getParameter("numberDrivers");
        if (registrationNumber.equals("") || ocapacityClass == null || onumberDrivers == null) {
            addWagonResult = "Incorrect input";
            rs.setMessage(addWagonResult);
            rs.setSeccesful(false);
        } else {
            try {
                int capacityClass = Integer.parseInt((String) ocapacityClass);
                int numberDrivers = Integer.parseInt((String) onumberDrivers);
                rs = es.addWagon(registrationNumber, numberDrivers, capacityClass);
            } catch (Exception e) {
                addWagonResult = "There is an error";
                rs.setMessage(addWagonResult);
                rs.setSeccesful(false);
            }
        }
        request.setAttribute("operationResult", rs);
        formPage(request);
        return "employeePage";
    }

    @RequestMapping(value = "/AddOrder", method = RequestMethod.POST)
    public String addOrder(ModelMap model, HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null, false);
        String addOrderResult = "";
        Object oNumber = request.getParameter("orderNumber");
        int number = 0;
        try {
            number = Integer.parseInt((String) oNumber);
            rs = es.addOrder(number);
        } catch (NumberFormatException e) {
            addOrderResult = "Incorrect input";
            rs.setMessage(addOrderResult);
            rs.setSeccesful(false);
        } catch (Exception e) {
            addOrderResult = "Error";
            rs.setMessage(addOrderResult);
            rs.setSeccesful(false);
        }
        request.setAttribute("operationResult", rs);
        formPage(request);
        return "employeePage";
    }

    @RequestMapping(value = "/AddWagonToOrder", method = RequestMethod.POST)
    public String addWagontoOrder(ModelMap model, HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null, false);
        String addWagonToOrderResult = "";
        String wagonNumber = request.getParameter("wagonNumber");
        String orderNumber = request.getParameter("orderNumber");
        if (!wagonNumber.equals("") && !orderNumber.equals("")) {
            try {
                rs = es.addWagonToOrder(Integer.parseInt(orderNumber), wagonNumber);
            } catch (NumberFormatException r) {
                addWagonToOrderResult = "Incorrect Input";
                rs.setMessage(addWagonToOrderResult);
                rs.setSeccesful(false);
            } catch (Exception e) {
                addWagonToOrderResult = "Error";
                rs.setMessage(addWagonToOrderResult);
                rs.setSeccesful(false);
            }
        } else {
            addWagonToOrderResult = "Incorrect Input";
            rs.setMessage(addWagonToOrderResult);
            rs.setSeccesful(false);
        }
        request.setAttribute("operationResult", rs);
        formPage(request);
        return "employeePage";
    }

    @RequestMapping(value = "/AddItem", method = RequestMethod.POST)
    public String addItem(ModelMap model, HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null, false);
        String addItemResult = "";
        String itemName = request.getParameter("itemName");
        Object oweight = request.getParameter("weight");
        try {
            rs = es.addItem(itemName, Integer.parseInt((String) oweight));
        } catch (NumberFormatException e) {
            addItemResult = "Incorrect Input";
            rs.setMessage(addItemResult);
            rs.setSeccesful(false);
        }
        request.setAttribute("operationResult", rs);
        formPage(request);
        return "employeePpage";
    }

    @RequestMapping(value = "/AddDriverToWagon", method = RequestMethod.POST)
    public String addDriverToWagon(ModelMap model, HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null, false);
        String addDriverToWagonResult = "";
        String wagonNumber = request.getParameter("wagonNumber");
        String driverLicense = request.getParameter("driverLicense");
        try {
            rs = es.addDriverToWagon(wagonNumber, Integer.parseInt(driverLicense));
        } catch (NumberFormatException e) {
            addDriverToWagonResult = "Incorrect Input";
            rs.setMessage(addDriverToWagonResult);
            rs.setSeccesful(false);
        } catch (Exception e) {
            addDriverToWagonResult = "Error";
            rs.setMessage(addDriverToWagonResult);
            rs.setSeccesful(false);
        }
        request.setAttribute("operationResult", rs);
        formPage(request);
        return "employeePage";
    }

    @RequestMapping(value = "/AddItemToOder", method = RequestMethod.POST)
    public String addItemToOrder(ModelMap model, HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null, false);
        String addItemToOrderResult = "";
        String itemName = request.getParameter("itemName");
        Object oOrderNumber = request.getParameter("orderNumber");
        try {
            rs = es.addItemToOrder(Integer.parseInt((String) oOrderNumber), itemName);
        } catch (NumberFormatException e) {
            addItemToOrderResult = "Incorrect input";
            rs.setMessage(addItemToOrderResult);
            rs.setSeccesful(false);
        }
        request.setAttribute("operationResult", rs);
        formPage(request);
        return "employeePage";
    }

    @RequestMapping(value = "/CloseOrder", method = RequestMethod.POST)
    public String closeOrder(ModelMap model, HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null,false);
        String closeOrderResult = "";
        Object oOrderNumber = request.getParameter("orderNumber");
        try {
            rs = es.finishOrder(Integer.parseInt((String) oOrderNumber));
        } catch (Exception e) {
            closeOrderResult = "Error";
            rs.setMessage(closeOrderResult);
            rs.setSeccesful(false);
        }
        request.setAttribute("operationResult", rs);
        formPage(request);
        return "employeePage";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPage(ModelMap model, HttpServletRequest request)
    {
        formPage(request);
        return  "employeePage";
    }

    private void formPage(final HttpServletRequest request) {
        List<Driver> drivers = es.getAllDrivers();
        List<Wagon> wagons = es.getAllWagons();
        List<Order> orders = es.getAllOrders();
        List<Item> items = es.getAllItems();
        request.setAttribute("Drivers", drivers);
        request.setAttribute("Wagons", wagons);
        request.setAttribute("Orders", orders);
        request.setAttribute("Items", items);
        request.setAttribute("UserName", SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
