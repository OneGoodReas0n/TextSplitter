package com.reason.filesplitter.db;

public class DbConfig {
    
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/LineSplitter";
    private final static String NAME = "root";
    private final static String PASSWORD = "";

    public static String getDRIVER() {
        return DRIVER;
    }

    public static String getURL() {
        return URL;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
    
    
}
