package com.rasl.controller;

import com.rasl.pojo.Task;
import com.rasl.services.StatusService;
import com.rasl.services.TagService;
import com.rasl.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TaskController {

    private TaskService taskService;
    private TagService tagService;
    private StatusService statusService;

    @Autowired
    public void setService(TaskService taskService){
        this.taskService = taskService;
    }
    @Autowired
    public void setTagService(TagService tagService){
        this.tagService = tagService;
    }
    @Autowired
    public void setStatusService(StatusService statusService){
        this.statusService = statusService;
    }

    @RequestMapping("task/list")
    public String list(Model model){
        List<Task> tasks = taskService.list();
        model.addAttribute("task", tasks);
        return "/task/list";
    }
}
