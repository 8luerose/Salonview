package com.springboot.security.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class NaverLoginDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int insertAnUserOAuth(Map<String, String> user) {
        String sql = "insert into user"
                + "(user_id,nickname, gender, created_at, updated_at) "
                + "values(?, ?, ?, sysdate(), sysdate())";

        return jdbcTemplate.update(sql,
                user.get("user_id"),
                user.get("nickname"),
                user.get("gender")
        );

    }
    // 회원 확인 sql
    public int checkUserExist(String userId) {
        String sql = "select exists( select user_id from user where user_id=?)";
        String checkUserExistParams = userId;
        return this.jdbcTemplate.queryForObject(sql,
                int.class,
                checkUserExistParams);

    }
}
