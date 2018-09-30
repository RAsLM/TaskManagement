package com.rasl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping({"/", "/home"})
    public String index() {
        return "index";
    }

}