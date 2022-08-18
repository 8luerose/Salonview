package com.springboot.security.hairshop.model;

import com.springboot.security.review.model.GetReviewsRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetShopSearchRes {
    private String shop_img;
    private String shop_name;
    private String shop_address;
    private String shop_rating;

}
