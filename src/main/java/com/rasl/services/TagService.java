package com.rasl.services;

import com.rasl.pojo.Tag;
import com.rasl.pojo.User;
import com.rasl.repositories.TagRepository;
import com.rasl.services.interfaces.PojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Tag> list(User user) {
        return repository.findAll().stream()
                .filter(category -> user.equals(category.getUser()))
                .collect(Collectors.toList());
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