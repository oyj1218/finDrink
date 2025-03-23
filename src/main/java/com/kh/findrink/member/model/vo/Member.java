package com.kh.findrink.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor    
public class Member {
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberNickname;
	private String memberTel;
	private String memberEmail;
	private String memberIntro;
	private String memberCategory;
	private String enrollDate;
	private String memberProfileImage;
	private String memberSalt;
	private char secessionFlag;
}
