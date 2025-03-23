package com.kh.findrink.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.kh.findrink.common.Util;

public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {

		String value = null;

		switch(name) {
		case "inputPw" :
		case "memberPw" :
			value = Util.encrypt(super.getParameter(name));
			break;
		default : value = super.getParameter(name);
		}

		return value;
	}
}

