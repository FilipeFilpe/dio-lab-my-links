package com.filipe.my_links.service;

import java.util.List;

public interface CrudService<T, ID> {
    List<T> findAll();
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}
