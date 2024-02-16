/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp01practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private static final String BD = "ejemplo";
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection obtenerConexion() {
        try {
            Connection connection = DriverManager.getConnection(URL + BD, USER, PASSWORD);
            Logger.getLogger(Conexion.class.getName()).log(Level.INFO, "Se conectó a la base de datos correctamente.");
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.INFO, "No se pudo conectar a la base de datos.", ex);
            throw new RuntimeException("Error al conectar a la base de datos", ex);
        }
    }
}



