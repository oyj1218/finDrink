package com.kh.findrink.review.model.vo;

import java.util.List;
import java.util.Map;

import com.kh.findrink.board.model.vo.Board;
import com.kh.findrink.board.model.vo.BoardImage;
import com.kh.findrink.reply.model.vo.Heart;
import com.kh.findrink.reply.model.vo.Reply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReviewDetail {
	private int memberNo;
	private int boardNo;
	private int storeNo;
	
	private String memberNickname;
	private String memberProfileImage;
	private String reviewTitle;
	private String reviewContent;
	private String createDate;
	private String updateDate;
	
	private int heartCount;
	private Map<String, Object> replys;
	private List<BoardImage> images;
}
