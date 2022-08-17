package com.springboot.security.Login;


import com.springboot.security.user.UserDao;
import com.springboot.security.user.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService {
    private final UserDao userDao;
    private final UserProvider userProvider;

    @Autowired
    public LoginService(UserDao userDao, UserProvider userProvider) {
        this.userDao = userDao;
        this.userProvider = userProvider;


    }

}
