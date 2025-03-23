package com.kh.findrink.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import static com.kh.findrink.common.JDBCTemplate.*;
import com.kh.findrink.member.model.dao.MemberDAO;
import com.kh.findrink.member.model.vo.Member;

public class MemberDAO {
	
	private Properties prop;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public MemberDAO() {
		try {
			prop = new Properties();

			String filePath = MemberDAO.class.getResource("/com/kh/findrink/sql/member-sql.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String selectSalt(Connection conn, String id) throws Exception {
		String salt = null;
		
		try {
			String sql = prop.getProperty("selectSalt");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				salt = rs.getString(1);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return salt;
	}

	public String selectPw(Connection conn, String id) throws Exception {
		String password = null;
		
		try {
			String sql = prop.getProperty("selectPw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				password = rs.getString(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return password;
	}

	public Member login(Connection conn, String id, String password) throws Exception {
		Member mem = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				mem = new Member();
				
				mem.setMemberNo(rs.getInt("MEMBER_NO"));
				mem.setMemberId(rs.getString("MEMBER_ID"));
				mem.setMemberName(rs.getString("MEMBER_NM"));
				mem.setMemberTel(rs.getString("MEMBER_TEL"));
				mem.setMemberNickname(rs.getString("MEMBER_NICK"));
				mem.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				mem.setMemberIntro(rs.getString("MEMBER_INT"));
				mem.setMemberCategory(rs.getString("MEMBER_CATEGORY"));
				mem.setEnrollDate(rs.getString("ENROLL_DT"));
				mem.setMemberProfileImage(rs.getString("MEMBER_IMG"));
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return mem;
	}

	public int signUp(Connection conn, Member newMem) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newMem.getMemberId());
			pstmt.setString(2, newMem.getMemberPw());
			pstmt.setString(3, newMem.getMemberName());
			pstmt.setString(4, newMem.getMemberTel());
			pstmt.setString(5, newMem.getMemberNickname());
			pstmt.setString(6, newMem.getMemberEmail());
			pstmt.setString(7, newMem.getMemberIntro());
			pstmt.setString(8, newMem.getMemberCategory());
			pstmt.setString(9, newMem.getMemberSalt());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int idDupCheck(Connection conn, String memberId) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("idDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				result = rs.getInt(1);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public int nicknameDupCheck(Connection conn, String memberNickname) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("nicknameDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberNickname);
			
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				result = rs.getInt(1);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}



	public int secessionMember(Connection conn, int memberNo, String encryptPw) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("secession");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, encryptPw);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
