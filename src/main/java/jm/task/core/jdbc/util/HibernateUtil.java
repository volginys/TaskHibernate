package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

public class HibernateUtil {

     private Configuration configuration = new Configuration()
             .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/userdata")
             .setProperty("dialect", "org.hibernate.dialect.MySQLDialect")
             .setProperty("hibernate.connection.username", "root")
             .setProperty("hibernate.connection.password", "eoot")
             .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
             .addAnnotatedClass(jm.task.core.jdbc.model.User.class);

    private SessionFactory sessionFactory = configuration.buildSessionFactory();
    private Session session;
    private Transaction transaction;


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        getSessionFactory().close();
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Session openSession() {
        return getSessionFactory().openSession();
    }

    public void closeSession() {
        session.close();
    }

    public Session openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeTransactionSession() {
        transaction.commit();
        closeSession();
    }
}
