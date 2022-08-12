package com.example.salonView.src.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.salonView.config.BaseException;
import com.example.salonView.config.BaseResponse;
import com.example.salonView.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.salonView.config.BaseResponseStatus.*;
import static com.example.salonView.utils.ValidationRegex.*;
@RestController
public class UserController {

    private UserProvider userProvider;

    @Autowired
    public UserController(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @GetMapping("/users")
    public List<GetUserRes> getUser() {
        List<GetUserRes> userRes = userProvider.getUser();
        return userRes;
    }


    /**
     *
     * 카카오톡 로그인 OAuth 인가 code 받는 코드
     *
     */

    @ResponseBody
    @GetMapping("/auth/kakao/callback")
    public void  kakaoCallback(@RequestParam String code) throws BaseException {

        System.out.println(code);

    }
}