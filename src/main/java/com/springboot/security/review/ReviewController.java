package com.springboot.security.review;

import com.springboot.security.config.BaseException;
import com.springboot.security.config.BaseResponse;
import com.springboot.security.review.model.PostReviewReq;
import com.springboot.security.review.model.PostReviewRes;
import com.springboot.security.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.springboot.security.config.BaseResponseStatus.POST_REVIEWS_EMPTY_CONTENT;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;

    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService){
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
    }


    //리뷰 작성
    @PostMapping("/new")
    public BaseResponse<PostReviewRes> createReiew (HttpSession session, @RequestBody PostReviewReq postReviewReq){
        if(postReviewReq.getContent() == null){
            return new BaseResponse<>(POST_REVIEWS_EMPTY_CONTENT);
        }
        if(postReviewReq.getHair_type()==null){
            return new BaseResponse<>(POST_REVIEWS_EMPTY_CONTENT);
        }
        if(postReviewReq.getHair_length()==null){
            return new BaseResponse<>(POST_REVIEWS_EMPTY_CONTENT);
        }
        if(postReviewReq.getHair_tag()==null){
            return new BaseResponse<>(POST_REVIEWS_EMPTY_CONTENT);
        }
        if(postReviewReq.getKindness()==null){
            return new BaseResponse<>(POST_REVIEWS_EMPTY_CONTENT);
        }
        if(postReviewReq.getPrice()==null){
            return new BaseResponse<>(POST_REVIEWS_EMPTY_CONTENT);
        }
        if(postReviewReq.getRating()==null){
            return new BaseResponse<>(POST_REVIEWS_EMPTY_CONTENT);
        }
        if(postReviewReq.getShop_name()==null){
            return new BaseResponse<>(POST_REVIEWS_EMPTY_CONTENT);
        }
        try{
            String userId =(String)session.getAttribute("currentUserId");
            PostReviewRes postReviewRes = reviewService.createReview(userId,postReviewReq);
            return new BaseResponse<>(postReviewRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
