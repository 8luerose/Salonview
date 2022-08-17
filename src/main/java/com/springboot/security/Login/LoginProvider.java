package com.springboot.security.Login;

import com.springboot.security.config.BaseException;
import org.springframework.beans.factory.annotation.Autowired;

import static com.springboot.security.config.BaseResponseStatus.DATABASE_ERROR;

public class LoginProvider {
    @Autowired
    private NaverLoginDao loginDao;

    //유저 회원가입 중복 확인
    public int checkUserExist(String userId)throws BaseException{
        try{
            return loginDao.checkUserExist(userId);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
