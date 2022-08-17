package com.springboot.security.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private String user_id;
    private String nickname;
    private String personal;
    private String face_shape;
    private String skin_tone;
    private String fashion_style;
    private String admin;
    private String gender;
    private String status;
    private Timestamp created_at;
    private Timestamp updated_at;


}
