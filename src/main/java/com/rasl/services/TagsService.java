package com.rasl.services;

import com.rasl.pojo.Tags;
import com.rasl.repositories.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ruslan on 20.02.2018.
 */
public class TagsService implements PojoService<Tags> {

    private TagsRepository repository;

    @Autowired
    public void setRepository(TagsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tags> list() {
        return repository.findAll();
    }

    @Override
    public Tags getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public Tags save(Tags obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
