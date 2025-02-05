package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {}


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY" +
                ", name VARCHAR(50)" +
                ", lastName VARCHAR(50)" +
                ", age TINYINT)";

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createNativeQuery(sql, User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
