package com.qs.mvc.service;

import com.qs.mvc.entity.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

@EnableTransactionManagement
@Service
public class UserService {
    @Resource
    private SessionFactory sessionFactory;

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
    public String save(User user) {
        return (String) sessionFactory.openSession().save(user);
    }
}
