package com.tsystems.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sasha_000 on 18.11.2014.
 */
@Controller
public class Main {
    @RequestMapping({"/f", "/Login"})
    public String showLoginPage(ModelMap model) {
        return "index";
    }

}
