package com.shortlink.service;

import com.shortlink.dao.LinkDao;
import com.shortlink.models.Link;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Link.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "tully");
        configuration.setProperty("hibernate.connection.password", "tully");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }



    public void printConnectInfo() {
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Iterable<Link> getAllLinks() throws DBException {
        try {
            Session session = sessionFactory.openSession();
            LinkDao dao = new LinkDao(session);
            Iterable<Link> dataSet = dao.getAll();
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }
    public Link getLink(long id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            LinkDao dao = new LinkDao(session);
            Link dataSet = dao.get(id);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }
    public String getOriginalUrl(String shortLink) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            LinkDao dao = new LinkDao(session);
            String dataSet = dao.getLinkBuShort(shortLink).getMainLink();
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void setUseLink(String shortLink) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            LinkDao dao = new LinkDao(session);
            dao.setUseLink(shortLink);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void addLink(String name, String desk) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            LinkDao dao = new LinkDao(session);
            dao.insertLink(name, ConversionShortLink.convert(name), desk);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }
    public void updateLink(Long id, String name, String desk) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            LinkDao dao = new LinkDao(session);
            dao.updateLink(id, name, ConversionShortLink.convert(name), desk);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void deleteLink(Long id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            LinkDao dao = new LinkDao(session);
            dao.deleteLink(id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }
    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
