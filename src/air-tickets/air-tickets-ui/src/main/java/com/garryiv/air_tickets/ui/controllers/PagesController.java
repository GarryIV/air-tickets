package com.garryiv.air_tickets.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController {

    @RequestMapping("/flight")
    public Object flight() {
        return new ModelAndView("/index.html");
    }

    @RequestMapping("/user/**")
    public Object user() {
        return new ModelAndView("/index.html");
    }

    @RequestMapping("/staff/**")
    public Object staff() {
        return new ModelAndView("/index.html");
    }
}
