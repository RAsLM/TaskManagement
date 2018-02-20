package com.rasl.services;

import com.rasl.pojo.Statuses;
import com.rasl.repositories.StatusesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ruslan on 20.02.2018.
 */
public class StatusesService implements PojoService<Statuses> {

    private StatusesRepository repository;

    @Autowired
    public void setRepository(StatusesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Statuses> list() {
        return repository.findAll();
    }

    @Override
    public Statuses getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public Statuses save(Statuses obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
