package com.rasl.services;

import com.rasl.pojo.Tag;
import com.rasl.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruslan on 20.02.2018.
 */
@Service
public class TagService implements PojoService<Tag> {

    private TagRepository repository;

    @Autowired
    public void setRepository(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tag> list() {
        return repository.findAll();
    }

    @Override
    public Tag getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public Tag save(Tag obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
