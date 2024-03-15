/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author galva
 *  private static final String DB_URL = "jdbc:sqlserver://DesktopHoG\\SQLEXPRESS:1433;databaseName=IMCDB;encrypt=false";
 * 
 * 
 */
public class DBConnection {
 
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://DesktopHoG\\SQLEXPRESS:1433;databaseName=IMCDB;encrypt=false";
    private static final String DB_USERNAME = "IMCDB";
    private static final String DB_PASSWORD = "Coddu##HctoRR35";

    private static Connection connection = null;

    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
      Connection conn = null;
        try {
         conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return conn;
    }


    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
