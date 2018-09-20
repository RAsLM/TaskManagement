package com.rasl.controller;

import com.rasl.pojo.Task;
import com.rasl.pojo.WorkLog;
import com.rasl.services.TaskService;
import com.rasl.services.UserService;
import com.rasl.services.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

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

    @RequestMapping(value = "workLog/api/start/{id}", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<Task>> start(@PathVariable Integer id, @RequestBody List<Task> taskListExterne){
        List<Task> taskListIntern = taskListExterne;
        for (Task task : taskListIntern) {
            if(task.getId() != id && task.isInProcess()){
                task.setInProcess(false);
                taskService.save(task);
            }
            if(task.getId() == id) {
                task.setInProcess(true);
                taskService.save(task);
            }
        }
        return new ResponseEntity<>(taskListIntern, HttpStatus.OK) ;
    }

    @RequestMapping(value = "workLog/api/stop/{id}", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity stop(@PathVariable Integer id, @RequestBody List<Task> taskListExterne){
        List<Task> taskListIntern = taskListExterne;
        for (Task task : taskListIntern) {
            if(task.getId() == id){
                task.setInProcess(false);
                taskService.save(task);
            }
        }
        return new ResponseEntity<>(taskListIntern,HttpStatus.OK);
    }

}