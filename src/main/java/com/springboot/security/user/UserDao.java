package com.springboot.security.user;


import com.springboot.security.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



//로그인 user정보 DB에 저장

    public List<GetUserRes> userRes(){
        return this.jdbcTemplate.query("Select * from user",
                (rs, rowNum) -> new GetUserRes(
                        rs.getString("user_id"),
                        rs.getString("nickname"),
                        rs.getString("personal"),
                        rs.getString("face_shape"),
                        rs.getString("skin_tone"),
                        rs.getString("fashion_style"),
                        rs.getString("admin"),
                        rs.getString("gender"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                )

        );
    }


}
