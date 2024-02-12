/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp01practica01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Logica {
    private Connection conexion;

    public Logica() {
        conexion = Conexion.obtenerConexion();
    }

    public void crearEmpleado(Empleado empleado) {
        try {
            String sql = "INSERT INTO empleados(nombre, direccion, telefono) VALUES (?, ?, ?)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, empleado.getNombre());
            pst.setString(2, empleado.getDireccion());
            pst.setString(3, empleado.getTelefono());
            pst.executeUpdate();
            System.out.println("Empleado creado correctamente.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Empleado> leerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try {
            String sql = "SELECT * FROM empleados";
            PreparedStatement pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setClave(rs.getInt("clave"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setTelefono(rs.getString("telefono"));
                empleados.add(empleado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return empleados;
    }

    public void actualizarEmpleado(Empleado empleado) {
        try {
            String sql = "UPDATE empleados SET nombre=?, direccion=?, telefono=? WHERE clave=?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, empleado.getNombre());
            pst.setString(2, empleado.getDireccion());
            pst.setString(3, empleado.getTelefono());
            pst.setInt(4, empleado.getClave());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Empleado actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún empleado con la clave especificada.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarEmpleado(int clave) {
        try {
            String sql = "DELETE FROM empleados WHERE clave=?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, clave);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Empleado eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún empleado con la clave especificada.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
