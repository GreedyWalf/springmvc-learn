package com.qs.mvc.service.impl;

import com.qs.mvc.entity.user.User;
import com.qs.mvc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public User findByUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new RuntimeException("param userName is null or empty!");
        }

        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("userName", userName))
                .setMaxResults(1);
        return (User) criteria.uniqueResult();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
    public int insertUser(User user) {
        Assert.notNull(user, "user is null");
        String[] fields = {"userId", "userName", "password", "email", "mobile","createBy","createTime","lastModifyBy","lastModifyTime"};
        String[] values = {user.getId(), user.getUserName(), user.getPassword(), user.getEmail(), user.getEmail(), user.getId(), "2018-01-01", user.getId(), "2018-01-01"};
        return insertEntity(fields, values, user);
    }
}
