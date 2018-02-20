package com.rasl.services;

import com.rasl.pojo.Tasks;
import com.rasl.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ruslan on 20.02.2018.
 */
public class TasksService implements PojoService<Tasks> {

    private TasksRepository repository;

    @Autowired
    public void setRepository(TasksRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tasks> list() {
        return repository.findAll();
    }

    @Override
    public Tasks getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public Tasks save(Tasks obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
