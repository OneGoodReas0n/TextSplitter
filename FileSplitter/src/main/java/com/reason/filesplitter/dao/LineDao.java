package com.reason.filesplitter.dao;

import com.reason.filesplitter.db.DbConnection;
import com.reason.filesplitter.model.LineInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LineDao extends Dao<LineInfo, Integer> {

    private final static String DBNAME = "textlines";
    private final static String ID = "line_id";

    @Override
    public LineInfo getOneById(Integer id) {
        LineInfo info = new LineInfo();
        try (Connection connection = DbConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(getOneByIdStatement(id, DBNAME, ID))) {
            while (result.next()) {
                info.setId(result.getInt("line_id"));
                info.setLine(result.getString("line_text"));
                info.setMaxWordLength(result.getInt("line_longestWordLength"));
                info.setMinWordLength(result.getInt("line_shortestWordLength"));
                info.setLineLength(result.getInt("line_length"));
                info.setAverageWordLength(result.getDouble("line_averageWordLength"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return info;
    }

    @Override
    public boolean add(LineInfo t) {
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
    public boolean update(LineInfo t) {
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
    public List<LineInfo> getAll() {
        List<LineInfo> lines = new ArrayList<>();
        try (Connection con = DbConnection.getConnection();
                PreparedStatement statement = con.prepareStatement(getAllStatement(DBNAME));
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                LineInfo info = new LineInfo(result.getInt("line_id"), result.getString("line_text"), 
                        result.getInt("line_longetstWordLength"), result.getInt("line_shortestWordLength"), 
                        result.getInt("line_length"), result.getDouble("line_averageWordLength"),result.getInt("file_id"));
                lines.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private PreparedStatement addPreparedStatement(Connection con, LineInfo info) {
        String query = String.format("INSERT INTO %s (line_text,line_longestWordLength,"
                + "line_shortestWordLength,line_length,line_averageWordLength,file_id) values(?,?,?,?,?,?)", DBNAME);
        PreparedStatement statement = null;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, info.getLine());
            ps.setInt(2, info.getMaxWordLength());
            ps.setInt(3, info.getMinWordLength());
            ps.setInt(4, info.getLineLength());
            ps.setDouble(5, info.getAverageWordLength());
            ps.setInt(6, info.getFileId());
            statement = ps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }

    private PreparedStatement updatePreparedStatement(Connection con, LineInfo info) {
        String query = String.format("UPDATE %s SET line_text=?,line_longestWordLength=?, line_shortestWordLength=?,"
                + "line_length=?, line_averageWordLength=?,file_id=? where ID=?", DBNAME) ;
        PreparedStatement stmt = null;
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, info.getLine());
            statement.setInt(2, info.getMaxWordLength());
            statement.setInt(3, info.getMinWordLength());
            statement.setInt(4, info.getLineLength());
            statement.setDouble(5, info.getAverageWordLength());
            statement.setInt(6, info.getFileId());
            statement.setInt(7, info.getId());
            stmt = statement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }

}
