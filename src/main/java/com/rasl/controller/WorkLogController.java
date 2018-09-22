package com.rasl.controller;

import com.rasl.pojo.Task;
import com.rasl.pojo.User;
import com.rasl.pojo.WorkLog;
import com.rasl.services.TaskService;
import com.rasl.services.UserService;
import com.rasl.services.WorkLogService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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

    @Builder @Getter @Setter
    static class JsonResponse {
        int id;
        long spentTime;
        boolean inProcess;
    }

    @RequestMapping(value = "workLog/api/start/{id}", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse start(@PathVariable Integer id, @RequestBody Long taskSpentTime){
        User currentUser = userService.getCurrentLoggedInUser();
        List<Task> taskListIntern = taskService.list(currentUser);
        for (Task task : taskListIntern) {
            if(task.getId() != id && task.isInProcess()){
                task.setInProcess(false);
                taskService.save(task);
            }
            if(task.getId() == id) {
                task.setSpentTime(taskSpentTime);
                task.setInProcess(true);
                taskService.save(task);
            }
        }
        return JsonResponse.builder()
                .id(id)
                .spentTime(taskSpentTime)
                .inProcess(true)
                .build();
    }


    @RequestMapping(value = "workLog/api/stop/{id}", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse stop(@PathVariable Integer id, @RequestBody Long taskSpentTime){
        User currentUser = userService.getCurrentLoggedInUser();
        List<Task> taskListIntern = taskService.list(currentUser);
        for (Task task : taskListIntern) {
            if(task.getId() == id){
                task.setSpentTime(taskSpentTime);
                task.setInProcess(false);
                taskService.save(task);
            }
        }
        return JsonResponse.builder()
                .id(id)
                .spentTime(taskSpentTime)
                .inProcess(false)
                .build();
    }
}