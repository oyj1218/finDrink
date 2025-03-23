package com.kh.findrink.reply.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Score {
	private int scoreValue;
	private int storeNo;
	private int memberNo;
	private int boardNo;
}
