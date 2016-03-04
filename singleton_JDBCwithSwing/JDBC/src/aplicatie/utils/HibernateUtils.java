package aplicatie.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Administrator on 10/27/2015.
 */
public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static Session getSession() {
        if (sessionFactory == null) {
            configureUsingHibernateConfigXMLFile();
        }
        return sessionFactory.getCurrentSession();
    }

    private static void configureUsingHibernateConfigXMLFile(){
        // Create configuration instance
        Configuration configuration = new Configuration();

        // Pass hibernate configuration file
        configuration.configure("hibernate.cfg.xml");

        // Since version 4.x, service registry is being used
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();

        // Create session factory instance
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }


}
