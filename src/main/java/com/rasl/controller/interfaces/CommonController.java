package com.rasl.controller.interfaces;

import org.springframework.ui.Model;

public interface CommonController {
    String list(Model model);
    String detailes(Integer id, Model model);
    String card();
}