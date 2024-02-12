/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.uv.dapp01practica01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author omar
 */
public class DAPP01Practica01 {

    public static void main(String[] args) {
        Statement st= null;
        Connection con= null;
        try {
            String url = "jdbc:postgresql://Localhost:5432/ejemplo";
            String usr = "postgres";
            String pwd= "postgres";
            con = DriverManager.getConnection(url, usr, pwd);
            
            st=con.createStatement();
            String sql = "INSERT INTO empleados(nombre, direccion, telefono) VALUES ('Omar', 'Calle Real', '282812831')";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAPP01Practica01.class.getName()).log(Level.INFO, "Se conecto", ex);
        }
 finally{

            try{
            if(con!=null)
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAPP01Practica01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}