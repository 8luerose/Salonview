package com.springboot.security.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewsPhotoRes {
    private int reviewPhoto_id;
    private String photo;
}
