package com.example.etl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ETLApp {

  public Connection conectar() {
    Connection con = null;
    try {
      Class.forName("org.h2.Driver");
      con = DriverManager.getConnection( "jdbc:h2:.\\src\\main\\resources\\default", "sa", "");
      System.out.println("En linea");
    } catch (Exception ex) {
      System.out.println("Error " + ex);
    }
    return con;
  }

  public ResultSet consultar() {
    Connection con = conectar();
    ResultSet rs = null;
    try {
      PreparedStatement ps = con.prepareStatement("SELECT * FROM \"AllstarFull\"");
      rs = ps.executeQuery();

    } catch (Exception ex) {
      System.err.println("Error " + ex);
    }

    return rs;
  }

  public static void main(String[] args) {
    ETLApp con = new ETLApp();
    ResultSet rs = con.consultar();

    try {
      while (rs.next()) {
        JOptionPane.showMessageDialog(null,
            rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
      }
    } catch (Exception ex) {
      System.out.println(ex);
    }

  }
}
