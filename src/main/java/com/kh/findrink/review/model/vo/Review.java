package com.kh.findrink.review.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Review {
	private int memberNo;
	private int boardNo;
	private int storeNo;
	
	private String storeName;
	private String memberNickname;
	private String memberProfileImage;
	private String thumbnail;
	private String reviewTitle;
	private String reviewContent;
	private String createDate;
	
	private int replyCount;
	private int heartCount;
}
