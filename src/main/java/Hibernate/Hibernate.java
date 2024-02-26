package Hibernate;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Hibernate {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties settings = new Properties();
            Configuration configuration = new Configuration();

            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/ejemplo");
            settings.put(Environment.USER, "postgres");
            settings.put(Environment.PASS, "postgres");

            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(settings);
            // Agregar las entidades
            configuration.addAnnotatedClass(PojoEmpleado.class);

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}

