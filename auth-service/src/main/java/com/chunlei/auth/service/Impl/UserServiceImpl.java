package com.chunlei.auth.service.Impl;

import com.chunlei.auth.dao.UserDao;
import com.chunlei.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String name) {
        return userDao.findByName(name);
    }
}
