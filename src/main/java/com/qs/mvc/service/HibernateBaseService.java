package com.qs.mvc.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class HibernateBaseService {

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

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public String save(Object model) {
        return (String) this.getSession().save(model);
    }

    public void update(Object model) {
        this.getSession().update(model);
    }
}
