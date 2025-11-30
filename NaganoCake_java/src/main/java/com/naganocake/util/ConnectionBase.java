package com.naganocake.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBase {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
        
        String url = "jdbc:mysql://localhost:3306/naganocake_db?serverTimezone=Asia/Tokyo&characterEncoding=UTF-8";
        String user = "Ncuser";
        String password = "nagano_pass_user";
        
        return DriverManager.getConnection(url, user, password);
    }
}