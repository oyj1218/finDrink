package com.kh.findrink.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board {
	private int boardNo;
	private int memberNo;
	private int boardCode;
	
	private String boardTitle;
	private String boardContent;
	private String memberNickname;
	private String createDate;
	private String updateDate;

	private char boardState;
	private String boardCategory;
	
	private int readCount;
	private int heartCount;
}

