package com.kh.findrink.review.model.vo;

import java.util.List;

import com.kh.findrink.member.model.vo.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reviewer {
	private Member member;
	private List<Review> reviews;
}
