package com.kh.findrink.mypage.model.dao;

import static com.kh.findrink.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.kh.findrink.member.model.vo.Member;

public class MypageDAO {

	private Properties prop;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public MypageDAO() {
		try {
			prop = new Properties();
			String filePath = MypageDAO.class.getResource("/com/kh/findrink/sql/mypage-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		}catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public int passwordCheck(Connection conn, String memberId, String encryptPw) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("passwordCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, encryptPw);
			
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

	public int memberInfoUpdate(Connection conn, Member member) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("memberInfoUpdate");
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(member.getMemberPw());
			pstmt.setString(1, member.getMemberProfileImage());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberTel());
			pstmt.setString(4, member.getMemberNickname());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getMemberIntro());
			pstmt.setString(7, member.getMemberSalt());
			pstmt.setInt(8, member.getMemberNo());
			
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
}
