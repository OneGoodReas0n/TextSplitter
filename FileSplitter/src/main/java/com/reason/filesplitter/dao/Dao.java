package com.reason.filesplitter.dao;

import java.util.List;

public abstract class Dao<T,I> {
    
    private final static String GETONEBYIDQUERY = "Select * from %s where %s=%s";
    private final static String REMOVEBYIDQUERY = "DELETE from %s where %s = %s";
    private final static String GETALLQUERY = "Select * from %s";
    
    abstract public T getOneById(I id);
    abstract public boolean add(T t);
    abstract public boolean update(T t);
    abstract public boolean removeById(I I);
    abstract public List<T> getAll();
    
    public String getOneByIdStatement(I i, String dbName, String idName){
        return String.format(GETONEBYIDQUERY, dbName,idName,i);
    }
    
    public String removeStatement(I i, String dbName, String idName){
        return String.format(REMOVEBYIDQUERY, dbName,idName,i);
    }
    
    public String getAllStatement(String dbName){
        return String.format(GETALLQUERY, dbName);
    }
}
