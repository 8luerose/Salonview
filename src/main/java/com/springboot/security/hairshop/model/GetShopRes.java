package com.springboot.security.hairshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetShopRes {
    private int shop_id;
    private String shop_name;
    private String shop_address;

}
