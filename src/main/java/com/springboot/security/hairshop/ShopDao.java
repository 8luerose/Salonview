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


}


