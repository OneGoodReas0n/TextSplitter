package com.reason.filesplitter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DbConfig.getDRIVER());
            conn = DriverManager.getConnection(DbConfig.getURL(), DbConfig.getNAME(), DbConfig.getPASSWORD());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
