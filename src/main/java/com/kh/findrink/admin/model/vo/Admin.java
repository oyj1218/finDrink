package com.kh.findrink.admin.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Admin {
	private int adminNo;
	private String adminId;
	private String adminPw;
	private String adminNickname;
}
