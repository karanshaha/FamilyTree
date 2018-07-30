package com.karan.family.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBConfiguration {

  private static Connection conn = null;
  static final String JDBC_DRIVER = "org.h2.Driver";
  static final String DB_URL = "jdbc:h2:mem:testdb;INIT=runscript from 'E:/demoConfigServer/FamilyTreeApplication/familytreeapplication/src/main/resources/data.sql';DB_CLOSE_ON_EXIT=TRUE";

  private JDBConfiguration() {

  }

  public static synchronized Connection getJDBCConnection() {

    if (conn == null) {
      synchronized (JDBConfiguration.class) {
        if (conn == null) {
          conn = buildSessionFactory();
        }
      }
    }
    return conn;
  }

  private static Connection buildSessionFactory() {
    Properties properties = new Properties();
    properties.put("user", "sa");
    properties.put("password", "");
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, properties);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return conn;
  }

}
