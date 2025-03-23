package com.kh.findrink.review.model.dao;

import static com.kh.findrink.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.findrink.board.model.vo.Board;
import com.kh.findrink.board.model.vo.BoardImage;
import com.kh.findrink.board.model.vo.Pagination;
import com.kh.findrink.member.model.vo.Member;
import com.kh.findrink.review.model.vo.Review;
import com.kh.findrink.review.model.vo.ReviewDetail;
import com.kh.findrink.review.model.vo.Reviewer;
import com.kh.findrink.store.model.vo.Store;

public class ReviewDAO {

	private Properties prop;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public ReviewDAO() {
		try {
			prop = new Properties();
			String filePath = ReviewDAO.class.getResource("/com/kh/findrink/sql/review-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	public int getReviewCount(Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("countReview");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				result = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	public int getReviewCountMember(Connection conn, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("countReviewMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
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
	
	public int getReviewCountStore(Connection conn, int storeNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("getReivewCountStore");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, storeNo);
			
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

	public List<Review> selectReviews(Connection conn, Pagination pagination) throws Exception {
		List<Review> boardList = new ArrayList<Review>();
		
		try {
			String sql = prop.getProperty("selectReviewList");
			
			int start = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
						
			while( rs.next() ) {
				Review review = new Review();
					
				review.setMemberNo(rs.getInt("MEMBER_NO"));
				review.setBoardNo( rs.getInt("BOARD_NO"));
				review.setMemberNickname(rs.getString("MEMBER_NICK"));
				review.setThumbnail(rs.getString("THUMBNAIL"));
				review.setMemberProfileImage(rs.getString("MEMBER_IMG"));
				review.setReviewTitle(rs.getString("BOARD_TITLE"));
				review.setCreateDate(rs.getString("CREATE_DT"));
				
				boardList.add(review);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}
	
	public List<Review> selectReviewWithMember(Connection conn, Pagination pagination, int memberNo) throws Exception {
		List<Review> reviewList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectReviewWithMember");
			
			int start = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				Review review = new Review();
					
				review.setMemberNo(rs.getInt("MEMBER_NO"));
				review.setBoardNo( rs.getInt("BOARD_NO"));
				review.setMemberNickname(rs.getString("MEMBER_NICK"));
				review.setThumbnail(rs.getString("THUMBNAIL"));
				review.setMemberProfileImage(rs.getString("MEMBER_IMG"));
				review.setReviewTitle(rs.getString("BOARD_TITLE"));
				review.setReviewContent(rs.getString("BOARD_CONTENT"));
				review.setCreateDate(rs.getString("CREATE_DT"));
				
				reviewList.add(review);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return reviewList;
	}

	public List<Review> selectReviewAll(Connection conn, int memberNo) throws Exception {
		List<Review> reviewList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectReviewAll");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				Review review = new Review();
				
				review.setBoardNo(rs.getInt("BOARD_NO"));
				review.setStoreName(rs.getString("STORE_NAME"));
				review.setReviewTitle(rs.getString("BOARD_TITLE"));
				review.setReviewContent(rs.getString("BOARD_CONTENT"));
				review.setThumbnail(rs.getString("THUMBNAIL"));
				review.setCreateDate(rs.getString("CREATE_DT"));
				review.setHeartCount(rs.getInt("HEART_COUNT"));
				review.setReplyCount(rs.getInt("REPLY_COUNT"));
				
				reviewList.add(review);
			}
		}catch( Exception e ) {
			e.printStackTrace();
		}
		
		return reviewList;
	}

	public Member selectMember(Connection conn, int memberNo) throws Exception {
		Member member = null;
		
		try {
			String sql = prop.getProperty("selectMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				member = new Member();
				
				member.setMemberNo(rs.getInt("MEMBER_NO"));
				member.setMemberNickname(rs.getString("MEMBER_NICK"));
				member.setMemberIntro(rs.getString("MEMBER_INT"));
				member.setMemberProfileImage(rs.getString("MEMBER_IMG"));
			}
		}finally {
			close(pstmt);
		}
		
		return member;
	}

	public int selectStoreNo(Connection conn, int memberNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("findStoreNo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
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

	public List<Review> selectReviewByStoreNo(Connection conn, int storeNo, Pagination pagination) throws Exception {
		List<Review> reviewList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectReviewByStoreNo");

			int start = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, storeNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);			
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				Review review = new Review();
								
				review.setMemberNickname(rs.getString("MEMBER_NICK"));
				review.setCreateDate(rs.getString("CREATE_DT"));
				review.setReviewContent(rs.getString("BOARD_CONTENT"));
				review.setReplyCount(rs.getInt("REPLY_COUNT"));
				review.setHeartCount(rs.getInt("HEART_COUNT"));
				
				reviewList.add(review);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return reviewList;
	}

	public List<BoardImage> selectImage(Connection conn, int reviewNo) throws Exception {
		List<BoardImage> imageList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNo);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				BoardImage image = new BoardImage();
				
				image.setBoardNo(rs.getInt("BOARD_NO"));
				image.setImageNo(rs.getInt("IMG_NO"));
				image.setImageLevel(rs.getInt("IMG_LEVEL"));
				image.setImageReName(rs.getString("IMG_RENAME"));
				image.setImageOriginal(rs.getString("IMG_ORIGINAL"));
				
				imageList.add(image);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return imageList;
		
	}

	public ReviewDetail selectReviewDetail(Connection conn, int reviewNo) throws Exception {
		ReviewDetail detail = null;
		
		try {
			String sql = prop.getProperty("selectReviewDetail");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNo);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				detail = new ReviewDetail();
				
				detail.setBoardNo(rs.getInt("BOARD_NO"));
				detail.setReviewTitle(rs.getString("BOARD_TITLE"));
				detail.setReviewContent(rs.getString("BOARD_CONTENT"));
				detail.setMemberNickname(rs.getString("MEMBER_NICK"));
				detail.setHeartCount(rs.getInt("HEART_COUNT"));
				detail.setCreateDate(rs.getString("CREATE_DT"));
				detail.setMemberProfileImage(rs.getString("MEMBER_IMG"));
				detail.setMemberNo(rs.getInt("MEMBER_NO"));
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return detail;
	}

	public int deleteReview(Connection conn, int boardNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteReview");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertHeart(Connection conn, int boardNo, int memberNo) throws Exception {
		int result = 0;
		
		try {
			boolean isExist = isExistHeart(boardNo, memberNo);
			
			String sql = null;
			
			if( isExist ) {
				sql = prop.getProperty("deleteHeart");
			} else {
				sql = prop.getProperty("insertHeart");
			}
			
			System.out.println(isExist);
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	private boolean isExistHeart(int boardNo, int memberNo) {
		boolean isExist = false;
		Connection conn = null;

		try {
			String sql = prop.getProperty("findHeart");
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			
			rs = pstmt.executeQuery();

			if( rs.next() ) {
				isExist = rs.getInt(1) == 1 ? true : false;
			}
		}catch( Exception e ) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		
		return isExist;
	}
	
	public int insertReport(Connection conn, int boardNo, int memberNo, int rtMemberNo, String reportReason) throws Exception {
		
		int result = 0;

		try {
			String sql = prop.getProperty("insertReport");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reportReason);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, rtMemberNo);
			pstmt.setInt(4, boardNo);		
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
