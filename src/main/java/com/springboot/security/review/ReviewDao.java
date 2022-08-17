package com.springboot.security.review;

import com.springboot.security.review.model.PostReviewPhotoReq;
import com.springboot.security.review.model.PostReviewReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ReviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //리뷰 작성
    public int insertReview(String userIdx, PostReviewReq postReviewReq){
        String insertPostQuery =
                "        INSERT INTO reviews(userIdx, content)\n" +
                        "        VALUES (?, ?);";
        Object[] insertPostParams = new Object[]{userIdx,postReviewReq.getContent()};
        this.jdbcTemplate.update(insertPostQuery, insertPostParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);

    }

    // 리뷰 이미지 작성
//    public int insertReviewPhoto(int postIdx, PostReviewPhotoReq postReviewPhotoReq){
//        String insertPostImgQuery =
//                "        INSERT INTO PostImgUrl(postIdx, imgUrl)\n" +
//                        "        VALUES (?, ?);";
//        Object[] insertPostImgParams = new Object[]{postIdx,postReviewPhotoReq.getPhoto()};
//        this.jdbcTemplate.update(insertPostImgQuery, insertPostImgParams);
//
//        String lastInserIdQuery = "select last_insert_id()";
//        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
//    }
}
