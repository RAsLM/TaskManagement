package com.rasl.services;

import com.rasl.pojo.Task;
import com.rasl.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruslan on 20.02.2018.
 */
@Service
public class TaskService implements PojoService<Task> {

    private TaskRepository repository;

    @Autowired
    public void setRepository(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> list() {
        return repository.findAll();
    }

    @Override
    public Task getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public Task save(Task obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
