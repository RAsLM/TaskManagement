package com.rasl.controller;

import com.rasl.pojo.Status;
import com.rasl.pojo.Tag;
import com.rasl.pojo.Task;
import com.rasl.services.StatusService;
import com.rasl.services.TagService;
import com.rasl.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("tasks/list")
    public String list(Model model){
        List<Task> tasks = taskService.list();
        model.addAttribute("tasks", tasks);
        return "/tasks/list";
    }

    @RequestMapping("/tasks/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        taskService.delete(id);
        return "redirect:/tasks/list";
    }

    @RequestMapping("/tasks/new")
    public String newTask(Model model){
        List<Tag> tags = tagService.list();
        List<Status> statuses = statusService.list();
        List<Task> parentTasks = taskService.list();
        model.addAttribute("tags", tags);
        model.addAttribute("statuses", statuses);
        model.addAttribute("parentTasks", parentTasks);
        model.addAttribute("task", new Task());
        return "/tasks/form";
    }

    @RequestMapping("/tasks/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        List<Tag> tags = tagService.list();
        List<Status> statuses = statusService.list();
        List<Task> parentTasks = taskService.list();
        Task task = taskService.getById(id);

        model.addAttribute("tags", tags);
        model.addAttribute("statuses", statuses);
        model.addAttribute("parentTasks", parentTasks);
        model.addAttribute("task", task);
        return "/tasks/form";
    }

    @RequestMapping(value = "/tasks/save", method = RequestMethod.POST)
    public String save(Task task){
        taskService.save(task);
        return "redirect:/tasks/list";
    }

    @RequestMapping("/tasks/details/{id}")
    public String details(@PathVariable Integer id, Model model) {
        model.addAttribute("task", taskService.getById(id));
        return "tasks/details";
    }
}
