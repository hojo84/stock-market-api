package com.codecool.stockmarketapi.dao;

import java.util.List;

public interface GenericCrudDAO<T> {

    List<T> listAll();

    Long save(T type);

    T findById(Long id);

    void deleteById(Long id);
}
