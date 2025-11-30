package com.naganocake.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

public class ConnectionTest {
    public static void main(String[] args) throws NamingException {
        try {
            Connection conn = ConnectionBase.getConnection();
            System.out.println("✅ データベース接続成功");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ データベース接続失敗: " + e.getMessage());
            e.printStackTrace();
        }
    }
}