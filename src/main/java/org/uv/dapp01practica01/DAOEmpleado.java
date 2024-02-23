package org.uv.dapp01practica01;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmpleado implements IDAOGeneral<PojoEmpleado, Integer> {
   
    @Override
    public boolean guardar(PojoEmpleado pojo) {
        ConexionDB con = ConexionDB.getInstance();
        TransactionDB tra = new TransactionDB<PojoEmpleado>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "INSERT INTO empleados (id, nombre, direccion, telefono) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstm = con.prepareStatement(sql);
                    pstm.setInt(1, pojo.getId());
                    pstm.setString(2, pojo.getNombre());
                    pstm.setString(3, pojo.getDireccion());
                    pstm.setString(4, pojo.getTelefono());
                    pstm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        return con.execute(tra);
    }

   @Override
public boolean modificar(PojoEmpleado pojo, Integer id) {
    ConexionDB con = ConexionDB.getInstance();
    TransactionDB tra = new TransactionDB<PojoEmpleado>(pojo) {
        @Override
        public boolean execute(Connection con) {
            try {
                String sql = "UPDATE empleados SET nombre=?, direccion=?, telefono=? WHERE id=?";
                PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setString(1, pojo.getNombre());
                pstm.setString(2, pojo.getDireccion());
                pstm.setString(3, pojo.getTelefono());
                pstm.setInt(4, id); // Usar el id proporcionado en lugar del id de pojo
                pstm.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    };
    boolean res = con.execute(tra);
    return res;
}


  @Override
public boolean eliminar(Integer id) {
    ConexionDB con = ConexionDB.getInstance();
    TransactionDB tra = new TransactionDB<PojoEmpleado>(null) {
        @Override
        public boolean execute(Connection con) {
            try {
                String sql = "DELETE FROM empleados WHERE id=?";
                PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setInt(1, id); // Usamos el parámetro id en lugar de clave
                pstm.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    };
    boolean res = con.execute(tra);
    return res;
}


    @Override
    public PojoEmpleado buscarById(Integer id) {
      SelectionDB sel = new SelectionDB(id) {
            @Override
            public List select(Connection con) {
                
                List<PojoEmpleado> listaEmpleados = new ArrayList<>();
               
                String sql = "SELECT * FROM empleados WHERE id = ?";

                try (PreparedStatement pstm = con.prepareStatement(sql)) {
                    pstm.setInt(1, id);
                    ResultSet resultSet = pstm.executeQuery();

                    if (resultSet.next()) {
                        PojoEmpleado empleado = new PojoEmpleado();
                        empleado.setId(id);
                        empleado.setNombre(resultSet.getString("nombre"));
                        empleado.setDireccion(resultSet.getString("direccion"));
                        empleado.setTelefono(resultSet.getString("telefono"));
                        
                        listaEmpleados.add(empleado);
                    }
                } catch (SQLException ex) {Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "Error al buscar", ex);
                }
                return listaEmpleados;
            }
            
        };     
        ConexionDB con = ConexionDB.getInstance();
        List<PojoEmpleado> listaEmpleados = con.select(sel);
        return listaEmpleados.get(0);
    }
    
    @Override
    public List<PojoEmpleado> buscarAll() {
      ConexionDB con = ConexionDB.getInstance();
    SelectionDB sel = new SelectionDB(null) {
        @Override
        public List<PojoEmpleado> select(Connection con) {
            List<PojoEmpleado> listaEmpleados = new ArrayList<>();
            String sql = "SELECT * FROM empleados";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                ResultSet resultSet = pstm.executeQuery();
                while (resultSet.next()) {
                    PojoEmpleado empleado = new PojoEmpleado();
                    empleado.setId(resultSet.getInt("id"));
                    empleado.setNombre(resultSet.getString("nombre"));
                    empleado.setDireccion(resultSet.getString("direccion"));
                    empleado.setTelefono(resultSet.getString("telefono"));
                    listaEmpleados.add(empleado);
                }
            } catch (SQLException e) {
                Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "Error al buscar empleados", e);
            }
            return listaEmpleados;
        }
    };

    List<PojoEmpleado> listaEmpleados = con.select(sel);
    return listaEmpleados;
    }
}
