package com.rasl.controller;

import com.rasl.pojo.Tag;
import com.rasl.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ruslan on 28.02.2018.
 */

@Controller
public class TagController {
    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping("tags/list")
    public String list(Model model){
        List<Tag> tags = tagService.list();
        model.addAttribute("tags", tags);
        return "/tags/list";
    }

    @RequestMapping("/tags/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        tagService.delete(id);
        return "redirect:/tags/list";
    }

    @RequestMapping("/tags/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Tag tag = tagService.getById(id);
        model.addAttribute("tag", tag);
        return"/tags/form";
    }

    @RequestMapping("/tags/new")
    public String newGroup(Model model){
        model.addAttribute("tag", new Tag());
        return "/tags/form";
    }

    @RequestMapping(value = "/tags/save", method = RequestMethod.POST)
    public String save(Tag tag){
        tagService.save(tag);
        return "redirect:/tags/list";
    }

    @RequestMapping("/tags/details/{id}")
    public String details(@PathVariable Integer id, Model model){
        model.addAttribute("tag", tagService.getById(id));
        return "/tags/details";
    }
}
