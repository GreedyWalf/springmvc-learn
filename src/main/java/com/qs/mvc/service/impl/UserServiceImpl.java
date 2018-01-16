package com.qs.mvc.service.impl;

import com.qs.mvc.entity.user.User;
import com.qs.mvc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
}
