package com.tsystems.controllers;

import com.tsystems.Util.ResultMessage;
import com.tsystems.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sasha_000 on 20.11.2014.
 */
@Controller
//@RequestMapping("/Driver")
public class DriverController {

    @Autowired
    DriverService ds;

    @RequestMapping(value = "/GetInfo", method = RequestMethod.POST)
    public String getInfo(ModelMap model, HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null, false);
        Object oDriverLicense = request.getParameter("driverLicense");
        String driverInfo = "";
        try {
            rs = ds.getInformationForDriver(Integer.parseInt((String) oDriverLicense));
        } catch (NumberFormatException e) {
            driverInfo = "Incorrect input";
            rs.setMessage(driverInfo);
            rs.setSeccesful(false);
        }
        request.setAttribute("operationResult", rs);
        return "driverPage";
    }

    @RequestMapping(value = "/DeliverItem", method = RequestMethod.POST)
    public String deliverItem(ModelMap model, HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null, false);
        String deliverResult = "";
        Object oOrderNumber = request.getParameter("orderNumber");
        Object oItemID = request.getParameter("itemID");
        Object oDriverLicense = request.getParameter("driverLicense");
        try {
            rs = ds.deliverItem(Integer.parseInt((String) oItemID), Integer.parseInt((String) oDriverLicense), Integer.parseInt((String) oOrderNumber));
        } catch (NumberFormatException e) {
            deliverResult = "Incorrect input";
            rs.setMessage(deliverResult);
            rs.setSeccesful(false);
        }
        request.setAttribute("operationResult", rs);
        return "driverPage";
    }

    @RequestMapping(value = "/ChangeStatus", method = RequestMethod.POST)
    public String changeStatus(final ModelMap model, final HttpServletRequest request) {
        ResultMessage rs = new ResultMessage(null, false);
        String changeStatusResult = "";
        Object oDriverLicense = request.getParameter("driverLicense");
        String status = request.getParameter("status");
        try {
            rs = ds.changeStatus(Integer.parseInt((String) oDriverLicense), status);
        } catch (NumberFormatException e) {
            changeStatusResult = "Incorrect Input";
            rs.setMessage(changeStatusResult);
            rs.setSeccesful(false);
        }
        request.setAttribute("operationResult", rs);
        return "driverPage";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPage(final ModelMap model, final HttpServletRequest request)
    {
        formPage(request);
        return "driverPage";
    }

    public void formPage(HttpServletRequest request)
    {
        request.setAttribute("UserName", SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
