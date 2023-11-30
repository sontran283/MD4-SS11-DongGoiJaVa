package com.ra.model.service;

import java.util.List;

public interface IGenericService<T, ID> {
    List<T> findAll(int noPage);

    List<T> findByName(String name);

    List<T> sortByName(String name);

    T findById(ID id);

    boolean saveOrUpDate(T t, ID id);

    void delete(ID id);
}
