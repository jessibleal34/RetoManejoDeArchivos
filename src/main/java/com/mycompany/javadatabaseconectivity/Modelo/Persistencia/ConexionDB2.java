/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javadatabaseconectivity.Modelo.Persistencia;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB2 {

    private static final String URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "jessi34_";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}