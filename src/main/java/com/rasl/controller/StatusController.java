package com.rasl.controller;

import com.rasl.pojo.Status;
import com.rasl.services.StatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ruslan on 04.03.2018.
 */

@Controller
public class StatusController {
    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping("statuses/list")
    public String list(Model model){
        List<Status> statuses = statusService.list();
        model.addAttribute("statuses", statuses);
        return "/statuses/list";
    }

    @RequestMapping("/statuses/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        statusService.delete(id);
        return "redirect:/statuses/list";
    }

    @RequestMapping("/statuses/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Status status = statusService.getById(id);
        model.addAttribute("status", status);
        return"/statuses/form";
    }

    @RequestMapping("/statuses/new")
    public String newGroup(Model model){
        model.addAttribute("status", new Status());
        return "/statuses/form";
    }

    @RequestMapping(value = "/statuses/save", method = RequestMethod.POST)
    public String save(Status status){
        statusService.save(status);
        return "redirect:/statuses/list";
    }

    @RequestMapping("/statuses/details/{id}")
    public String details(@PathVariable Integer id, Model model) {
        model.addAttribute("status", statusService.getById(id));
        return "statuses/details";
    }
}
