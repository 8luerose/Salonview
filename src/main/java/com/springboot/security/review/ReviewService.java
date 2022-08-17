package com.springboot.security.review;

import com.springboot.security.config.BaseException;
import com.springboot.security.review.model.PostReviewReq;
import com.springboot.security.review.model.PostReviewRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.springboot.security.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ReviewService {


    //정보 받아와서 update처리
    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;

    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider) {
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;

    }
    public PostReviewRes createReview(String userIdx, PostReviewReq postReviewReq) throws BaseException {


        try{
            int postIdx = reviewDao.insertReview(userIdx, postReviewReq);
//            for(int i=0; i< postReviewReq.getPhoto().size(); i++) {
//                reviewDao.insertReviewPhoto(postIdx, postReviewReq.getPhoto().get(i));
//            }
            return new PostReviewRes(postIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
