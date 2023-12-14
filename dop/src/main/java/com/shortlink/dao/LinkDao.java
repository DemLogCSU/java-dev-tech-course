package com.shortlink.dao;

import com.shortlink.models.Link;
import com.shortlink.service.ConversionShortLink;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class LinkDao {
    private Session session;

    public LinkDao(Session session) {
        this.session = session;
    }

    public List<Link> getAll(){

        String sql = "select * from links";
        Query query = session.createSQLQuery(sql);
        List<Object[]> rows = query.list();
        ArrayList<Link> links = new ArrayList();
        for (Object[] row : rows) {
            links.add(new Link(Long.parseLong(row[0].toString()), row[2].toString(), row[3].toString(), row[1].toString(), Integer.parseInt(row[4].toString())));
        }
        return links;
    }
    public Link get(long id) throws HibernateException {
        return (Link) session.get(Link.class, id);
    }

    public Link getLinkBuShort(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(Link.class);
        return ((Link) criteria.add(Restrictions.eq("shortLink", "localhost:8080/api/l/" + name)).uniqueResult());
    }
    public void setUseLink(String name) throws HibernateException {
        Link link = getLinkBuShort(name);
        link.setUseCount();
        session.evict(link);
        session.saveOrUpdate(link);
    }

    public void updateLink(Long id, String mainLink, String shortLink,String desk) throws HibernateException {
        Link link = get(id);
        session.evict(link);
        session.saveOrUpdate(new Link(id, mainLink,shortLink, desk));
    }
    public long insertLink(String mainLink, String shortLink,String desk) throws HibernateException {
        return (Long) session.save(new Link(mainLink,shortLink, desk));
    }
    public void deleteLink(Long id) throws HibernateException {
        session.delete(get(id));
    }
}
