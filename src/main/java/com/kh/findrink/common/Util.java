package com.kh.findrink.common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.kh.findrink.member.model.controller.MemberService;

public class Util {

	private final static int SALT_LENGTH = 32;
	
	public static String makeSalt() {
		
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String salt = "";
		
		for( int i = 0; i < SALT_LENGTH; i++ ) {
			salt += str.charAt((int)(Math.random()* str.length()));
		}
		
		return salt;
	}
	
	public static String getSalt(String id) {
		
		String salt = null;
		
		try {
			MemberService service = new MemberService();
			
			salt = service.selectSalt(id);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return salt;
	}
	
	public static String encrypt(String origin) {
		
		String encrypt = null;
				
		MessageDigest md = null;
		
		try {
			
			md = MessageDigest.getInstance("SHA-512");
			
			byte[] bytes = origin.getBytes(Charset.forName("UTF-8"));
			
			md.update(bytes);
			
			encrypt = Base64.getEncoder().encodeToString(md.digest());
			
			
		} catch( NoSuchAlgorithmException e ) {
			e.printStackTrace();
		}
		
		return encrypt;
	}

	// 개행 문자 -> <br> 변경 메서드
	public static String newLineHandling(String content) {
		
		return content.replaceAll("\r\n|\n|\r|\n\r", "<br>");
		
		// textarea의 엔터 : \r\n
		// \r : 캐리지 리턴(첫 번째로 돌아가기) -> 현재는 개행문자로 인식
		// \n : new line(다음 줄로 이동)
	}
	
	// XSS : 관리자가 아닌 이용자가 악성 스크립트를 삽입해서 공격
	
	// Cross Site Scripting(XSS, 크로스 사이트 스크립팅) 공격 방지 처리 메서드
	public static String XSSHandling(String content) {
		
		// <, >, &, " 문자를 HTML 코드가 아닌 문자 그대로 보이도록 변경

		if(content != null) {
			
			content = content.replaceAll("&", "&amp;");
			content = content.replaceAll("<", "&lt;");
			content = content.replaceAll(">", "&gt;");
			content = content.replaceAll("\"", "&quot;");
		}
		
		return content;
	}
	
}
