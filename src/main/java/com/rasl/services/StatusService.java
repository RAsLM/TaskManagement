package com.rasl.services;

import com.rasl.pojo.Status;
import com.rasl.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruslan on 20.02.2018.
 */
@Service
public class StatusService implements PojoService<Status> {

    private StatusRepository repository;

    @Autowired
    public void setRepository(StatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Status> list() {
        return repository.findAll();
    }

    @Override
    public Status getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public Status save(Status obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
