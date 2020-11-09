package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import user.Bot;
import java.util.List;
import java.util.Optional;

public class BotDao implements CrudDao<Bot> {
    private final SessionFactory sessionFactory;
    public BotDao(){
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://89.108.103.185:5432/postgres");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "botflow");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addAnnotatedClass(Bot.class);
        configuration.setProperty("hibernate.show_sql", "true");
        sessionFactory = configuration.buildSessionFactory();
    }
    @Override
    public void save(Bot model) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Bot model) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(model);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Bot model) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(model);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<Bot> get(String email) {
        return Optional.empty();
    }

    public List<Bot> getBots(String email) {
        Session session = sessionFactory.openSession();
        List<Bot> botList = session.createQuery("from Bot bot where bot.email = :email", Bot.class).setParameter("email", email).getResultList();
        session.close();
        return botList;
    }
}
