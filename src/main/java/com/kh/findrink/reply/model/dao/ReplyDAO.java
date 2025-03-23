package com.kh.findrink.reply.model.dao;

import static com.kh.findrink.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.findrink.board.model.vo.Pagination;
import com.kh.findrink.reply.model.vo.Reply;

public class ReplyDAO {
	
	private Properties prop;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public ReplyDAO() {
		try {
			prop = new Properties();
			String filePath = ReplyDAO.class.getResource("/com/kh/findrink/sql/reply-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		}catch( Exception e ) {
			e.printStackTrace();
		}
		
	}
	/** 댓글 목록 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(Connection conn, int boardNo) throws Exception {
		
		List<Reply> rList = new ArrayList<Reply>();
		
		try {
			String sql = prop.getProperty("selectReplyList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Reply r = new Reply();
				
				r.setReplyNo( rs.getInt(1) );
				r.setReplyContent( rs.getString(2) );
				r.setCreateDate( rs.getString(3) );
				r.setBoardNo( rs.getInt(4) );
				r.setMemberNo( rs.getInt(5) );
				r.setMemberNickname( rs.getString(6) );
				r.setMemberProfileImage( rs.getString(7) );
				
				
			
				rList.add(r);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return rList;
	}

		/** 댓글 작성 DAO
	 * @param conn
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Connection conn, Reply reply) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertReply");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getParentReplyNo());
			pstmt.setInt(3, reply.getMemberNo());
			pstmt.setInt(4, reply.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

//	public int deleteReply(Connection conn, int replyNo) throws Exception {
//		int result = 0;
//		
//		try {
//			String sql = prop.getProperty("deleteReply");
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, replyNo);
//			
//			result = pstmt.executeUpdate();
//		}finally {
//			close(pstmt);
//		}
//		
//		return result;
//	}


	/** 댓글 삭제 DAO
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(Connection conn, int replyNo) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteReply");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 댓글 수정 DAO
	 * @param conn
	 * @param replyNo
	 * @param replyContent
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(Connection conn, int replyNo, String replyContent) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateReply");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, replyContent);
			pstmt.setInt(2, replyNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public int getReplyCount(Connection conn, int boardNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("getReplyCount");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
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
	public List<Reply> selectReplyPagination(Connection conn, int boardNo, Pagination pagination) throws Exception {
		List<Reply> list = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectPagination");
			
			int start = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				Reply reply = new Reply();
				
				reply.setReplyNo(rs.getInt("REPLY_NO"));
				reply.setMemberNo(rs.getInt("MEMBER_NO"));
				reply.setCreateDate(rs.getString("CREATE_DT"));
				reply.setMemberNickname(rs.getString("MEMBER_NICK"));
				reply.setMemberProfileImage(rs.getString("MEMBER_IMG"));
				reply.setReplyContent(rs.getString("REPLY_CONTENT"));
				
				list.add(reply);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
}
