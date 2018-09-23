package com.rasl.controller;

import com.rasl.pojo.Status;
import com.rasl.pojo.Tag;
import com.rasl.pojo.Task;
import com.rasl.pojo.User;
import com.rasl.services.StatusService;
import com.rasl.services.TagService;
import com.rasl.services.TaskService;
import com.rasl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private TaskService taskService;
    private TagService tagService;
    private StatusService statusService;
    private UserService userService;

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
    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/tasks/list")
    public String list() {
        return "/tasks/list";
    }

    @RequestMapping(value = "/getTable", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Task>> tasks(){
        User currentUser = userService.getCurrentLoggedInUser();
        List<Task> tasks = taskService.list(currentUser);
        for (Task task: tasks) {
            if(task.getSpentTime() == null){
                task.setSpentTime(0L);
            }
        }
        tasks = taskService.list(currentUser);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    @RequestMapping("/tasks/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        taskService.delete(id);
        return "redirect:/tasks/list";
    }

    @RequestMapping("/tasks/new")
    public String newTask(Model model){
        User currentUser = userService.getCurrentLoggedInUser();
        List<Tag> tags = tagService.list(currentUser);
        List<Status> statuses = statusService.list(currentUser);
        List<Task> parentTasks = taskService.list(currentUser);
        model.addAttribute("tags", tags);
        model.addAttribute("statuses", statuses);
        model.addAttribute("parentTasks", parentTasks);
        model.addAttribute("task", new Task());
        return "/tasks/form";
    }

    @RequestMapping("/tasks/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        User currentUser = userService.getCurrentLoggedInUser();
        List<Tag> tags = tagService.list(currentUser);
        List<Status> statuses = statusService.list(currentUser);
        List<Task> parentTasks = taskService.list(currentUser);
        Task task = taskService.getById(id);

        model.addAttribute("tags", tags);
        model.addAttribute("statuses", statuses);
        model.addAttribute("parentTasks", parentTasks);
        model.addAttribute("task", task);
        return "/tasks/form";
    }

    @RequestMapping(value = "/tasks/save", method = RequestMethod.POST)
    public String save(Task task){
        task.setUser(userService.getCurrentLoggedInUser());
        taskService.save(task);
        return "redirect:/tasks/list";
    }

    @RequestMapping("/tasks/details/{id}")
    public String details(@PathVariable Integer id, Model model) {
        model.addAttribute("task", taskService.getById(id));
        return "tasks/details";
    }
}