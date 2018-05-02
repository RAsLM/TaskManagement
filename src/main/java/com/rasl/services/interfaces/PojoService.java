package com.rasl.services.interfaces;

import com.rasl.pojo.User;

import java.util.List;

/**
 *Pojo interface for services
 * @author Aslanov Ruslan
 * @version 1.0
 */

public interface PojoService<T> {
    List<T> list(User user);
    List<T> list();
    T getById(Integer id);
    T save(T obj);
    void delete(Integer id);
}