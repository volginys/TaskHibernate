package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static Configuration configuration = new Configuration()
             .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/userdata")
             .setProperty("dialect", "org.hibernate.dialect.MySQLDialect")
             .setProperty("hibernate.connection.username", "root")
             .setProperty("hibernate.connection.password", "eoot")
             .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
             .addAnnotatedClass(jm.task.core.jdbc.model.User.class);

    public static SessionFactory getSessionFactory() {
        ServiceRegistry serviceRegistry  = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
