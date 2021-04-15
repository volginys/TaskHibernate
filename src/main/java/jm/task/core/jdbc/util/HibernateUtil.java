package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static Configuration configuration = new Configuration()
             .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/userdata")
             .setProperty("dialect", "org.hibernate.dialect.MySQLDialect")
             .setProperty("hibernate.connection.username", "root")
             .setProperty("hibernate.connection.password", "eoot")
             .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
             .addAnnotatedClass(jm.task.core.jdbc.model.User.class);

    public static SessionFactory getSessionFactory() {
        return configuration.buildSessionFactory();
    }
}
