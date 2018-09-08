package com.rasl.controller;

import com.rasl.pojo.Task;
import com.rasl.pojo.WorkLog;
import com.rasl.services.TaskService;
import com.rasl.services.UserService;
import com.rasl.services.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.aspectj.bridge.Version.getTime;

/**
 * Created by ruslan on 04.03.2018.
 */

@Controller
public class WorkLogController {

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

    @RequestMapping("workLog/start/{id}")
    public String startTask(@PathVariable Integer id, Model model){
        WorkLog workLog = new WorkLog();
        Task task = taskService.getById(id);
        workLogService.stopAllTasks(id);
        workLog.setTask(task);
        workLog.setUser(userService.getCurrentLoggedInUser());
        workLog.setStartTime(Instant.now());
        task.setInProcess(true);
        taskService.save(task);
        workLogService.save(workLog);


        System.out.println("Задача с id: " + task.getId() + " запущена");
        System.out.println("WorkLog id: " + workLog.getId() + " Время начала " + workLog.getStartTime());

        return "redirect:/tasks/list";
    }

    @RequestMapping("workLog/stop/{id}")
    public String stopTask(@PathVariable Integer id, Model model1){
        WorkLog workLog = workLogService.getById(workLogService.getLastWL(id));
        workLog.setUser(userService.getCurrentLoggedInUser());
        workLog.setEndTime(Instant.now());
        workLogService.save(workLog);

        Task task = taskService.getById(id);
        task.setInProcess(false);
        int wasted = workLogService.getSpendTime(id);
        task.setSpentTime(wasted);
        taskService.save(task);


        System.out.println("Задача с id: " + id + " остановлена");
        System.out.println("WorkLog id: " + workLog.getId() + " Время конца " + workLog.getEndTime());
        System.out.println(wasted);

        return "redirect:/tasks/list";
    }

}