package com.kh.findrink.member.model.controller;

import java.sql.Connection;

import com.kh.findrink.member.model.dao.MemberDAO;
import com.kh.findrink.member.model.vo.Member;

import static com.kh.findrink.common.JDBCTemplate.*;

public class MemberService {

	private MemberDAO dao = new MemberDAO();
	
	public String selectSalt(String id) throws Exception {
		Connection conn = getConnection();
		
		String salt = dao.selectSalt(conn, id);
		
		close(conn);
		
		return salt;
	}
	
	public String selectPw(String id) throws Exception {
		Connection conn = getConnection();
		
		String pw = dao.selectPw(conn, id);
		
		close(conn);
		
		return pw;
	}
	
	public Member login(String id, String password) throws Exception {
		Connection conn = getConnection();

		Member mem = dao.login(conn, id, password);
		
		close(conn);
		
		return mem;
	}

	public int signUp(Member newMem) throws Exception {
		Connection conn = getConnection();

		int result = dao.signUp(conn, newMem);
		
		if( result > 0 ) commit(conn);
		else			 rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int idDupCheck(String memberId) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.idDupCheck(conn, memberId);
		
		close(conn);
		
		return result;
	}

	public int nicknameDupCheck(String memberNickname) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.nicknameDupCheck(conn, memberNickname);
		
		close(conn);
		
		return result;
	}



	public int secessionMember(int memberNo, String encryptPw) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.secessionMember(conn, memberNo, encryptPw);
		
		if( result > 0 )	commit(conn);
		else				rollback(conn);
		
		close(conn);
		
		return result;
	}

}
