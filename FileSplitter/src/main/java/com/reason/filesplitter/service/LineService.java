package com.reason.filesplitter.service;

import com.reason.filesplitter.dao.LineDao;
import com.reason.filesplitter.model.LineInfo;
import java.util.List;

public class LineService extends AbstractService<LineInfo, Integer>{

    private LineDao lineDao = new LineDao();
    
    @Override
    public boolean save(LineInfo t) {
        try {
            lineDao.add(t);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public LineInfo getOne(Integer i) {
        return lineDao.getOneById(i);
    }

    @Override
    public boolean update(LineInfo t) {
        try {
            lineDao.update(t);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(LineInfo t) {
        try {
            lineDao.removeById(t.getId());
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public List<LineInfo> getAll() {
        return lineDao.getAll();
    }  
    
}
