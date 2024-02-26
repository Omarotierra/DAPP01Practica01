package Hibernate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class EmpleadoDAOH {
     private final SessionFactory sessionFactory;

    public EmpleadoDAOH(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearEmpleado(PojoEmpleado empleado) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(empleado);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(EmpleadoDAOH.class.getName()).log(Level.SEVERE, "Error al crear un empleado", ex);
        }
    }

    public void actualizarEmpleado(PojoEmpleado empleado) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(empleado);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
             Logger.getLogger(EmpleadoDAOH.class.getName()).log(Level.SEVERE, "Error al actualizar un empleado", ex);
        }
    }

    public void eliminarEmpleado(PojoEmpleado empleado) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(empleado);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
             Logger.getLogger(EmpleadoDAOH.class.getName()).log(Level.SEVERE, "Error al eliminar un empleado", ex);
        }
    }
    
    public List<PojoEmpleado> buscarTodos() {
        try (Session session = sessionFactory.openSession()) {
            Query<PojoEmpleado> query = session.createQuery("FROM empleados", PojoEmpleado.class);
            return query.list();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoDAOH.class.getName()).log(Level.SEVERE, "Error al buscar todos los empleados", ex);
            return null;
        }
    }

    public PojoEmpleado buscarPorId(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(PojoEmpleado.class, id);
        } catch (Exception ex) {
             Logger.getLogger(EmpleadoDAOH.class.getName()).log(Level.SEVERE, "Error al buscar un empleado", ex);
            return null;
        }
    }
}
