package com.kh.findrink.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String createDate;
	private String updateDate;
	private int readCount;
	private char noticeStatus;
	private int adminNo;
}
