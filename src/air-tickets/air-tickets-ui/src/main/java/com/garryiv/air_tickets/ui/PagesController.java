package com.garryiv.air_tickets.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @RequestMapping("/flight")
    public Object flight() {
        return "index.html";
    }
}
