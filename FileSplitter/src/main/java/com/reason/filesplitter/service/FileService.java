package com.reason.filesplitter.service;

import com.reason.filesplitter.dao.TextFileDao;
import com.reason.filesplitter.model.FileInfo;
import java.util.ArrayList;
import java.util.List;

public class FileService extends AbstractService<FileInfo, Integer>{

    private TextFileDao fileDao =  new TextFileDao();
    
    @Override
    public boolean save(FileInfo t) {
        try {
            if(isUnique(t)){
                fileDao.add(t);
                return true;
            }
            else{
                System.err.println("Такой файл уже существует в базе");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public FileInfo getOne(Integer i) {
        return fileDao.getOneById(i);
    }

    @Override
    public boolean update(FileInfo t) {
        try {
            fileDao.update(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(FileInfo t) {
        try {
            fileDao.removeById(t.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<FileInfo> getAll() {
        List<FileInfo> files = new ArrayList<>();
        fileDao.getAll().forEach(files::add);
        return files;
    }
    
    public FileInfo getOneByName(String name){
        return fileDao.getOneByName(name);
    }
    
    public boolean isUnique(FileInfo info){
        boolean result = true;
        for (FileInfo fileInfo : getAll()) {
           if(fileInfo.getName().equals(info)){
               result = false;
           } 
        }
        return result;
    }
}
