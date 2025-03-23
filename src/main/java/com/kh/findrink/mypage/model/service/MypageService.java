package com.kh.findrink.mypage.model.service;

import static com.kh.findrink.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.findrink.member.model.vo.Member;
import com.kh.findrink.mypage.model.dao.MypageDAO;

public class MypageService {
	
	private MypageDAO dao = new MypageDAO();
	
	public int passwordCheck(String memberId, String encryptPw) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.passwordCheck(conn, memberId, encryptPw);
		
		close(conn);
		
		return result;
	}

	public int memberInfoUpdate(Member member) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.memberInfoUpdate(conn, member);
		
		if( result > 0 )	commit(conn);
		else				rollback(conn);
		
		close(conn);
		
		return result;
	}
}
