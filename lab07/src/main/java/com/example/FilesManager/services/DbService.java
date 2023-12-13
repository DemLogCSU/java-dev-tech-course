package com.example.FilesManager.services;

import com.example.FilesManager.models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DbService {
    public static final DbService USER_SERVICE = new DbService();
    public User getUser(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.byNaturalId(User.class).using("login", login).load();
        session.close();
        return user;
    }
    public void addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

    public User getUserByCookies(Cookie[] cookies) {
        User user;
        if ((user = getUser(CookieUtil.getValue(cookies, "login"))) == null || !user.getPassword().equals(CookieUtil.getValue(cookies, "password"))) {
            return null;
        }
        return user;
    }
}
