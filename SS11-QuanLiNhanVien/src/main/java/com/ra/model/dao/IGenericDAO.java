package com.ra.model.dao;

import java.util.List;

public interface IGenericDAO<T, ID> {
    List<T> findAll();

    List<T> findByName(String name);

    List<T> sortByName(String name);

    T findById(ID id);

    boolean saveOrUpDate(T t, ID id);

    void delete(ID id);
}
