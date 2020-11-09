package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import user.User;
import javax.persistence.NoResultException;
import java.util.Optional;

public class UserDao implements CrudDao<User> {
    private final SessionFactory sessionFactory;
    public UserDao(){
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://89.108.103.185:5432/postgres");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "botflow");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.show_sql", "true");
        sessionFactory = configuration.buildSessionFactory();
    }
    @Override
    public void save(User model) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(User model) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(model);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User model) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(model);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<User> get(String email) {
        User user;
        Session session = sessionFactory.openSession();
        try {
            user = session.createQuery("from User user where user.email = :email", User.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException exception) { user = null; }
        session.close();
        if (user != null) return Optional.of(user);
        return Optional.empty();
    }
}
