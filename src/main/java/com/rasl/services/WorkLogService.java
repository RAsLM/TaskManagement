package com.rasl.services;

import com.rasl.pojo.WorkLog;
import com.rasl.repositories.WorkLogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ruslan on 20.02.2018.
 */
public class WorkLogService implements PojoService<WorkLog> {

    private WorkLogRepository repository;

    @Autowired
    public void setRepository(WorkLogRepository repository) {
        this.repository = repository;
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
}
