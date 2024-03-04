
package Hibernate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DAOVenta {
     private final SessionFactory sessionFactory;

    public DAOVenta(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearVenta(Venta venta) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(venta);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(DAOVenta.class.getName()).log(Level.SEVERE, "Error al crear una venta", ex);
        }
    }

    public void actualizarVenta(Venta venta) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(venta);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
             Logger.getLogger(DAOVenta.class.getName()).log(Level.SEVERE, "Error al actualizar una venta", ex);
        }
    }

    public void eliminarVenta(Venta venta) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(venta);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
             Logger.getLogger(DAOVenta.class.getName()).log(Level.SEVERE, "Error al eliminar una venta", ex);
        }
    }
    
    public List<Venta> buscarTodos() {
        try (Session session = sessionFactory.openSession()) {
            Query<Venta> query = session.createQuery("FROM venta", Venta.class);
            return query.list();
        } catch (Exception ex) {
            Logger.getLogger(DAOVenta.class.getName()).log(Level.SEVERE, "Error al buscar todas las ventas", ex);
            return null;
        }
    }

    public Venta buscarPorId(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Venta.class, id);
        } catch (Exception ex) {
             Logger.getLogger(DAOVenta.class.getName()).log(Level.SEVERE, "Error al buscar una venta", ex);
            return null;
        }
    }
}
