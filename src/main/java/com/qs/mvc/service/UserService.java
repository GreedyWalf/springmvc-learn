package com.qs.mvc.service;

import com.qs.mvc.entity.user.User;
import com.qs.mvc.service.base.BaseService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

public interface UserService extends BaseService<User> {

    User findByUserName(String userName);
}
