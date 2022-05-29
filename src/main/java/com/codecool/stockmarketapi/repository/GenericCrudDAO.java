package com.codecool.stockmarketapi.repository;

import java.util.List;

public interface GenericCrudDAO<T> {

    List<T> listAll();

    Long save(T entity);

    T findById(Long id);

    void deleteById(Long id);
}
