package com.rasl.controller;

import com.rasl.pojo.Task;
import com.rasl.pojo.WorkLog;
import com.rasl.services.TaskService;
import com.rasl.services.UserService;
import com.rasl.services.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Instant;

/**
 * Created by ruslan on 04.03.2018.
 */

@Controller
public class WorkLogControllerNew {

    private WorkLogService workLogService;
    private TaskService taskService;
    private UserService userService;

    @Autowired
    public void setWorkLogService(WorkLogService workLogService) {
        this.workLogService = workLogService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "workLogNew/start/{id}", produces = "application/json", method = RequestMethod.POST)
    public String startTask(@PathVariable Integer id, Model model){
        WorkLog workLog = new WorkLog();
        Task task = taskService.getById(id);

        return "redirect:/tasks/list";
    }

    @RequestMapping(value = "workLogNew/stop/{id}", produces = "application/json", method = RequestMethod.POST)
    public String stopTask(@PathVariable Integer id, Model model1){
        return "";
    }

}