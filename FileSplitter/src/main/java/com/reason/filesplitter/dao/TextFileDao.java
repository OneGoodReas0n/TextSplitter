package com.reason.filesplitter.dao;

import com.reason.filesplitter.db.DbConnection;
import com.reason.filesplitter.model.FileInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TextFileDao extends Dao<FileInfo, Integer>{

    private final static String DBNAME = "files";
    private final static String ID = "file_id";
    
    @Override
    public FileInfo getOneById(Integer id) {
        FileInfo info = new FileInfo();
        try (Connection connection = DbConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(getOneByIdStatement(id, DBNAME, ID))) {
            while (result.next()) {
                info.setId(result.getInt("ID"));
                info.setName(result.getString("file_name"));
                info.setLinesNumber(result.getInt("file_linesNumber"));
                info.setLinesNumber(result.getInt("file_wordNumber"));
                info.setLinesNumber(result.getInt("file_symbolNumber"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return info;
    }

    @Override
    public boolean add(FileInfo t) {
        try (Connection con = DbConnection.getConnection();
                PreparedStatement preparedStatement = addPreparedStatement(con, t)) {
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(FileInfo t) {
        try (Connection con = DbConnection.getConnection();
                PreparedStatement preparedStatement = updatePreparedStatement(con, t)) {
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeById(Integer i) {
        try (Connection con = DbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(removeStatement(i, DBNAME, ID))) {
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<FileInfo> getAll() {
        List<FileInfo> files = new ArrayList<>();
        try (Connection con = DbConnection.getConnection();
                PreparedStatement statement = con.prepareStatement(getAllStatement(DBNAME));
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                FileInfo file = new FileInfo(result.getInt("file_id"), result.getString("file_name"), 
                        result.getInt("file_linesNumber"),result.getInt("file_wordNumber"),
                        result.getInt("file_symbolNumber"));
                files.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return files;
    }
    
    public FileInfo getOneByName(String name){
        FileInfo needed = new FileInfo();
        for (FileInfo file : getAll()) {
            if(file.getName().equals(name)){
                needed = file;
            }
        }
        return needed;
    }
    
    private PreparedStatement addPreparedStatement(Connection con, FileInfo file) {
        String query = String.format("INSERT INTO %s (file_name,file_linesNumber,"
                + "file_wordNumber,file_symbolNumber) values(?,?,?,?)", DBNAME);
        PreparedStatement statement = null;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, file.getName());
            ps.setInt(2, file.getLinesNumber());
            ps.setInt(3, file.getWordNumber());
            ps.setInt(4, file.getSymbolNumber());
            statement = ps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }

    private PreparedStatement updatePreparedStatement(Connection con, FileInfo file) {
        String query = String.format("UPDATE %s SET file_name = ?, file_linesNumber=?,"
                + "file_wordNumber=?, file_symbolNumber=? where file_id=?", DBNAME) ;
        PreparedStatement stmt = null;
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, file.getName());
            statement.setInt(2, file.getLinesNumber());
            statement.setInt(3, file.getWordNumber());
            statement.setInt(4, file.getSymbolNumber());
            statement.setInt(5, file.getId());
            stmt = statement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }
    
}
