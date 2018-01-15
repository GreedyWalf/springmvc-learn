package com.qs.mvc.service.impl;

import com.qs.mvc.entity.user.User;
import com.qs.mvc.service.UserService;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

}
