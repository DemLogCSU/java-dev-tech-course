package com.example.FilesManager.dao;

import com.example.FilesManager.models.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public void insertUser(User user){
        session.persist(user);
    }

    public User getByLogin(String login) throws HibernateException {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<User> criteria = builder.createQuery(User.class);
//        criteria.select(el )
//        return ((User) criteria.add(Restrictions.eq("login", login)).uniqueResult());
//
//        Query query= session.getCurrentSession().
//                createQuery("from Category where name=:name");
//        query.setParameter("name", name);

        return new User();
    }
    public User get(long id) throws HibernateException {
        return (User) session.get(User.class, id);
    }

}
