/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp01practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omar
 */
public class ConexionDB {
     private static ConexionDB cx=null;
    private Connection con = null;
    
    public static ConexionDB getInstance(){
        if(cx==null){
            cx=new ConexionDB();
        }
        return cx;
    }
    
    
    private ConexionDB(){
        try{
            String BD = "ejemplo";
            String URL = "jdbc:postgresql://localhost:5432/";
            String USER = "postgres";
            String PASSWORD = "postgres";
            con = DriverManager.getConnection(URL + BD, USER, PASSWORD);
        } catch(SQLException ex){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, "No se pudo establecer la conexión", ex);
        }
    }
    
    public boolean execute(String sql) throws SQLException {
        Statement st = null;
        try {
            st = con.createStatement();
            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO, sql);
            st.execute(sql);
            return true;
        } catch(SQLException e) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, "No se pudo realizar la acción", e);
            return false;
        } finally {
            if(st != null) {
                try {
                    st.close();
                } catch(SQLException e) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, "No se pudo cerrar la conexión", e);
                }
            }
        }
    }

    public boolean execute(TransactionDB tra){
        return tra.execute(con);
    }

    
    public List select(SelectionDB sel){
        return sel.select(con);
    }
    
    public ResultSet select(String sql){
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            return rs;
        } catch(SQLException e) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, "No se pudo realizar la accion", e);
            return null;
        }
        finally {
            if(st != null) {
                try {
                    st.close();
                } catch(SQLException e) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, "No se pudo cerrar la conexion", e);
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                } catch(SQLException e) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, "No se pudo cerrar la conexion", e);
                }
            }
            
        }
    }
}
