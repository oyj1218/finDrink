package com.kh.findrink.admin.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Report {
	private int reportNo;
	private String createDate;
	private char reportSatas;
	private String reportContent;
	private int memberNo;
	private int reportMemberNo;
}
