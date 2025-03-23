package com.kh.findrink.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardType {
	private int boardNo;
	private String boardName;
	private String boardParent;
}
