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
}