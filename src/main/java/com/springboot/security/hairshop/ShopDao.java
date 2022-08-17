package com.springboot.security.hairshop;

import com.springboot.security.hairshop.model.GetShopRes;
import com.springboot.security.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ShopDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


//    String checkUserExistParams = userId;
//        return this.jdbcTemplate.queryForObject(sql,
//    int.class,
//    checkUserExistParams);


    //헤어샵 찾기: 헤어샵 이름으로 검색 시, 헤어샵의 이름, 주소 보여주기
    public List<GetShopRes> shopListRes(String shopName){
        String sql="select * from shop where shop_name like ? ";
        String wrappedKeyword = "%" + shopName + "%";

        return this.jdbcTemplate.query(sql,
                (rs, rowNum) ->
                        new GetShopRes(
                            rs.getInt("shop_id"),
                            rs.getString("shop_name"),
                            rs.getString("shop_address")
                ),wrappedKeyword);
    }
    public GetShopRes shopRes(int shopId) {
        String sql="select * from shop where shop_id=? ";
        return this.jdbcTemplate.queryForObject(sql,
                (rs, rowNum) ->
                        new GetShopRes(
                                rs.getInt("shop_id"),
                                rs.getString("shop_name"),
                                rs.getString("shop_address")
                        ),shopId);
    }


    //헤어샵 조회: 헤어샵 검색 후 보여줄 헤어샵 목록들 (ex 서울 송파구 잠실동 근처 헤어샵)

    /*

    해당 쿼리를 기반으로 작성할 예정

    #헤어샵 조회 쿼리
    SELECT shop_img, shop_name, shop_address, avg(rating),shop_view
    FROM shop
    JOIN review on review.shop_id=shop.shop_id and shop.status='ACTIVE' and review.status='ACTIVE'
            #GROUP BY review.shop_id
    GROUP BY shop.shop_view
    ORDER BY shop.shop_view DESC;

    #헤어샵 살롱뷰 에디터 픽
    SELECT shop_img, shop_name, shop_address, shop_view
    FROM shop
    #GROUP BY shop.shop_view;
    ORDER BY shop.shop_view DESC
    LIMIT 3;

    *
    * */


}


