package com.kh.findrink.store.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Store {
	private int storeNo;
	private String storeName;
	private String storeAddress;
	private String storeTel;
	private char storeState;
	private double storeScore;
	private String storeIntro;
	private String storeImage;
	private int memberNo;
}
