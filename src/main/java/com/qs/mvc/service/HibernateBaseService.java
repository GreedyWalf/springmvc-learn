package com.qs.mvc.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Repository
public class HibernateBaseService {
    @Resource
    private JdbcTemplate jdbcTemplate;

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public HibernateBaseService(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public HibernateBaseService(){}

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }


    public Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public String save(Object model) {
        return (String) this.getSession().save(model);
    }

    public void update(Object model) {
        this.getSession().update(model);
    }

    public void saveOrUpdate(Object model){
        this.getSession().saveOrUpdate(model);
    }

    public <T> void batchSaveOrUpdate(List<T> models) {
        Session session = this.getSession();
        for (int i = 0; i < models.size(); i++) {
            Object model = models.get(i);
            session.saveOrUpdate(model);
            if (i % 100 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Transactional
    public <T> T load(Class<T> entityClass, Serializable id) {
        return (T) this.getSession().load(entityClass, id);
    }

    public <T> T get(Class<T> entityClass, Serializable id) {
        return (T) this.getSession().get(entityClass, id);
    }
}
