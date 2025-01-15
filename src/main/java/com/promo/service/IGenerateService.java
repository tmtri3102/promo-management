package com.promo.service;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Integer id);

    void save(T t);

    void remove(Integer id);
}
