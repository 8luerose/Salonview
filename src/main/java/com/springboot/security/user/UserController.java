package com.springboot.security.user;

import com.springboot.security.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private  UserService userService;

    @Autowired
    public UserController(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

   @GetMapping("")
    public List<GetUserRes> getUser() {
        List<GetUserRes> userRes = userProvider.getUser();
        return userRes;
    }




}