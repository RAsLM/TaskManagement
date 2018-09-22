package com.rasl.services;

import com.rasl.pojo.User;
import com.rasl.pojo.WorkLog;
import com.rasl.repositories.WorkLogRepository;
import com.rasl.services.interfaces.PojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ruslan on 20.02.2018.
 */
@Service
public class WorkLogService implements PojoService<WorkLog> {

    private WorkLogRepository repository;
    private TaskService taskService;
    private UserService userService;

    @Autowired
    public void setRepository(WorkLogRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<WorkLog> list(User user) {
        return repository.findAll().stream()
                .filter(category -> user.equals(category.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkLog> list() {
        return repository.findAll();
    }

    @Override
    public WorkLog getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public WorkLog save(WorkLog obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }

    private Integer findLastWL(Integer id) {
        Integer lastWorkLog = null;
        User currentUser = userService.getCurrentLoggedInUser();
        List<WorkLog> workLogs = list(currentUser);
        List<WorkLog> workLogsSortByTaskId = new ArrayList<>();
        for (WorkLog workLogI : workLogs) {
            if (workLogI.getTask().getId().equals(id)){
                workLogsSortByTaskId.add(workLogI);
            }
        }
        for (WorkLog w : workLogsSortByTaskId) {
            if((lastWorkLog = w.getId()) <= w.getId()){
                lastWorkLog = w.getId();
            }
        }
        return lastWorkLog;
    }

    public void stopAllTasks(Integer id, Long spendTime){
        User currentUser = userService.getCurrentLoggedInUser();
        List<WorkLog> workLogs = list(currentUser);

        for (WorkLog workLog : workLogs) {
            if (!workLog.getTask().getId().equals(id)){
                workLog.setSpendTime(spendTime);
                workLog.getTask().setInProcess(false);
                taskService.save(workLog.getTask());
                save(workLog);
            }
        }
    }

    public Integer getLastWL(Integer id){
        return findLastWL(id);
    }
}