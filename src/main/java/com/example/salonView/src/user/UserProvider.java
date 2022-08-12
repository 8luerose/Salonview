package com.example.salonView.src.user;


import com.example.salonView.config.BaseException;
import com.example.salonView.config.secret.Secret;
import com.example.salonView.src.user.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.salonView.config.BaseResponseStatus.*;


@Service
public class UserProvider {

    private final UserDao userDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<GetUserRes> getUser(){
        List<GetUserRes> userRes = userDao.userRes();

        return userRes;
    }
}
