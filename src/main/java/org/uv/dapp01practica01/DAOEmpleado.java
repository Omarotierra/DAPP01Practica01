package org.uv.dapp01practica01;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmpleado {
    private final Connection conexion;

    public DAOEmpleado() {
        conexion = Conexion.obtenerConexion();
    }
 public boolean guardar(PojoEmpleado empleado){
        try {
            String sql = "INSERT INTO empleados(nombre, direccion, telefono) VALUES (?, ?, ?)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, empleado.getNombre());
            pst.setString(2, empleado.getDireccion());
            pst.setString(3, empleado.getTelefono());
            pst.execute();
            System.out.println("Empleado creado correctamente.");
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
 }   
 public boolean modificar(PojoEmpleado empleado){
     try {
            String sql = "UPDATE empleados SET nombre=?, direccion=?, telefono=? WHERE id=?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, empleado.getNombre());
            pst.setString(2, empleado.getDireccion());
            pst.setString(3, empleado.getTelefono());
            pst.setInt(4, empleado.getId());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Empleado actualizado correctamente.");
                 return true;
            } else {
                System.out.println("No se encontró ningún empleado con la clave especificada.");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
     
 }
 public boolean eliminar(int id) {
    try {
        String sql = "DELETE FROM empleados WHERE id=?";
        PreparedStatement pst = conexion.prepareStatement(sql);
        pst.setInt(1, id);
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Empleado eliminado correctamente.");
            return true;
        } else {
            System.out.println("No se encontró ningún empleado con la clave especificada.");
            return false;
        }
    } catch (SQLException ex) {
        Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

 public PojoEmpleado buscarById(int id){
     try {
            String sql = "SELECT * FROM empleados where id = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet reg=pst.executeQuery();
            if (reg.next()){
                PojoEmpleado empleado = new PojoEmpleado();
                 empleado.setId(reg.getInt(1));
                 empleado.setNombre(reg.getString(2));
                 empleado.setDireccion(reg.getString(3));
                 empleado.setTelefono(reg.getString(4));
                 return empleado;
            }
            return null;
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
 }

 public List<PojoEmpleado> buscarAll() {
    List<PojoEmpleado> listaEmpleados = new ArrayList<>();
    try {
        String sql = "SELECT * FROM empleados";
        PreparedStatement pst = conexion.prepareStatement(sql);
        ResultSet reg = pst.executeQuery();
        while (reg.next()) {
            PojoEmpleado empleado = new PojoEmpleado();
            empleado.setId(reg.getInt(1));
            empleado.setNombre(reg.getString(2));
            empleado.setDireccion(reg.getString(3));
            empleado.setTelefono(reg.getString(4));
            listaEmpleados.add(empleado);
        }
        return listaEmpleados;
    } catch (SQLException ex) {
        Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
 
}

}
