package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "CREATE TABLE if not exists users " +
                "(id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
                "name VARCHAR(30), " +
                "lastname VARCHAR(30)," +
                "age TINYINT);";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "DROP TABLE IF EXISTS USERS";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        System.out.println("User с именем - "+user.getName()+" добавлен в базу данных");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(session.get(User.class,id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String hql = "FROM User";
        List<User> userList = session.createQuery(hql).list();
        session.getTransaction().commit();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "TRUNCATE TABLE USERS;";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void close() {
        sessionFactory.close();
    }
}
