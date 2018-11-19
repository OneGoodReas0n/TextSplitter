package com.reason.filesplitter.service;

import java.util.List;

abstract public class AbstractService<T,I> {
    
    abstract public boolean save(T t);
    abstract public T getOne(I i);
    abstract public boolean update(T t);
    abstract public boolean delete(T t);
    abstract public List<T> getAll();
}
