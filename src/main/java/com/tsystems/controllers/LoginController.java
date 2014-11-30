package com.tsystems.controllers;

import com.tsystems.model.User;
import com.tsystems.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Created by sasha_000 on 20.11.2014.
 */
@Controller
public class LoginController {

    @Autowired
    LoginService ls;

    @RequestMapping(value = {"/Auth"}, method = RequestMethod.POST)
    public String loginPOST(ModelMap model) {
            return "index";
    }


    @RequestMapping(value = {"/Auth"}, method = RequestMethod.GET)
    public String loginGET(ModelMap model) {
        Collection<GrantedAuthority> d = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role = null;
        //    String type = ls.validateLogin(request.getParameter("j_username"),request.getParameter("j_password"));
        for (GrantedAuthority authority : d) {
            role = authority.getAuthority();
        }
        if (role.equals(User.EMPLOYEE_TYPE)) {
            return "forward:/Employee/";
        } else if (role.equals(User.DRIVER_TYPE)) {
            return "forward:/Driver/";
        } else {
            return "index";
        }
    }
}
