package com.tsystems.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sasha_000 on 18.11.2014.
 */
@Controller
public class Main {
    @RequestMapping(value = {"/", "/Login"}, method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        return "index";
    }

}
