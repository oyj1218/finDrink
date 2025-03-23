package com.kh.findrink.reply.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reply {
	private int replyNo;
	private String replyContent;
	private String memberNickname;
	private String memberProfileImage;
	private int boardNo;
	private int memberNo;
	private int parentReplyNo;
	private int storeNo;
	private String createDate;
}
