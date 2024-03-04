package Hibernate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DAODetalleVenta {
  private final SessionFactory sessionFactory;

    public DAODetalleVenta(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearDetalleVenta(DetalleVenta detalleVenta) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(detalleVenta);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(DAODetalleVenta.class.getName()).log(Level.SEVERE, "Error al crear un detalle de venta", ex);
        }
    }

    public void actualizarDetalleVenta(DetalleVenta detalleVenta) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(detalleVenta);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
             Logger.getLogger(DAODetalleVenta.class.getName()).log(Level.SEVERE, "Error al actualizar un detalle de venta", ex);
        }
    }

    public void eliminarDetalleVenta(DetalleVenta detalleVenta) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(detalleVenta);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
             Logger.getLogger(DAODetalleVenta.class.getName()).log(Level.SEVERE, "Error al eliminar un detalle de venta", ex);
        }
    }
    
    public List<DetalleVenta> buscarTodos() {
        try (Session session = sessionFactory.openSession()) {
            Query<DetalleVenta> query = session.createQuery("FROM detalleventa", DetalleVenta.class);
            return query.list();
        } catch (Exception ex) {
            Logger.getLogger(DAODetalleVenta.class.getName()).log(Level.SEVERE, "Error al buscar todos los detalles de venta", ex);
            return null;
        }
    }

    public DetalleVenta buscarPorId(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(DetalleVenta.class, id);
        } catch (Exception ex) {
             Logger.getLogger(DAODetalleVenta.class.getName()).log(Level.SEVERE, "Error al buscar un detalle de venta", ex);
            return null;
        }
    }  
}
