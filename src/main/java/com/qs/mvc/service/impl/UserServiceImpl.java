package com.qs.mvc.service.impl;

import com.qs.mvc.entity.user.User;
import com.qs.mvc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Resource
    private SessionFactory sessionFactory;


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
    public String insertUser(User user) {
        if(user == null || StringUtils.isBlank(user.getId())){
            throw new RuntimeException("user or userId is null or empty!");
        }

        String userId = user.getId();
        Date createTime = user.getCreateTime();
        if(createTime == null){
           user.setCreateTime(new Date());
        }

        String createBy = user.getCreateBy();
        if(StringUtils.isBlank(createBy)){
            user.setCreateBy(userId);
        }

        //调用session的save方法后，会自动生成主键
        String _id = save(user);
        //将自动生成的主键更新为自己设置的主键
        update("id", userId, user);
        return userId;
    }
}
