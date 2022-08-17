package com.springboot.security.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewsRes {
    private int review_id;
    private int shop_id;
    private String user_id;
    private String hair_type;
    private String hair_length;
    private String hair_tag;
    private String kindness;
    private String price;
    private String rating;
    private String content;
    private List<GetReviewsPhotoRes> photo;

}
