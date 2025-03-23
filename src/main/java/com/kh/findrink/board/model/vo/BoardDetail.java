package com.kh.findrink.board.model.vo;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDetail {

	private int boardNo;
	private int memberNo;
	
	private String boardCategory;
	private String boardTitle;
	private String boardContent;
	private String createDate;
	private String updateDate;
	private String boardName;

	private int readCount;
	private int boardCode;
	private int heartCount;
	
	private List<BoardImage> imageList;
	private Map<String, Object> replys;
}
