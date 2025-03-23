package com.kh.findrink.review.model.service;

import static com.kh.findrink.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.findrink.board.model.service.BoardService;
import com.kh.findrink.board.model.vo.Board;
import com.kh.findrink.board.model.vo.BoardImage;
import com.kh.findrink.board.model.vo.Pagination;
import com.kh.findrink.member.model.dao.MemberDAO;
import com.kh.findrink.member.model.vo.Member;
import com.kh.findrink.reply.model.service.ReplyService;
import com.kh.findrink.reply.model.vo.Reply;
import com.kh.findrink.review.model.dao.ReviewDAO;
import com.kh.findrink.review.model.vo.Review;
import com.kh.findrink.review.model.vo.ReviewDetail;
import com.kh.findrink.review.model.vo.Reviewer;

public class ReviewService {

	private ReviewDAO dao = new ReviewDAO();
	
	public Map<String, Object> selectAllReview(int currPage) throws Exception {
		Connection conn = getConnection();
				
		int maxCount = dao.getReviewCount(conn);
		
		Pagination pagination = new Pagination(currPage, maxCount); 
		pagination.setLimit(8);
		pagination.setPageSize(10);
		
		List<Review> reviews = dao.selectReviews(conn, pagination);
				
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("reviews", reviews);
		map.put("pagination", pagination);
				
		return map;
	}

	public Reviewer selectReviewer(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		Reviewer reviewer = null;
		Member member = dao.selectMember(conn, memberNo);

		if( member != null ) {
			reviewer = new Reviewer();
			reviewer.setMember(member);
			
			List<Review> reviewList = dao.selectReviewAll(conn, memberNo);
			if( reviewList != null ) {
				reviewer.setReviews(reviewList);
			}
		}
		close(conn);
		
		return reviewer;
	}

	public Map<String, Object> selectReviewWithStore(int currPage, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int storeNo = dao.selectStoreNo(conn, memberNo);
				
		int maxCount = dao.getReviewCountStore(conn, storeNo);
		
		Pagination pagination = new Pagination(currPage, maxCount); 
		pagination.setLimit(2);
		pagination.setPageSize(10);
		
		List<Review> reviewList = dao.selectReviewByStoreNo(conn, storeNo, pagination);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("reviews", reviewList);
		map.put("pagination", pagination);
		
		close(conn);
		
		return map;
	}

	public ReviewDetail selectReviewDetail(int currPage, int reviewNo) throws Exception {
		Connection conn = getConnection();
		
		ReviewDetail detail = dao.selectReviewDetail(conn, reviewNo);
		
		if( detail != null ) {
			List<BoardImage> boardImage = dao.selectImage(conn, reviewNo);
			detail.setImages(boardImage);
			
			System.out.println(boardImage);
			
			Map<String, Object> replys = new ReplyService().selectReplyWithPagination(currPage, reviewNo);
			detail.setReplys(replys);
		}
		
		close(conn);
		
		return detail;
	}

	public int deleteReview(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteReview(conn, boardNo);
		
		if( result > 0 )	commit(conn);
		else				rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int insertHeart(int boardNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertHeart(conn, boardNo, memberNo);
		
		if( result > 0 )	commit(conn);
		else				rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int insertReport(int boardNo, int memberNo, int rtMemberNo, String reportReason) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertReport(conn, boardNo, memberNo, rtMemberNo, reportReason);
		
		if( result > 0 )	commit(conn);
		else				rollback(conn);
		
		close(conn);
		
		return result;
	}

	public Map<String, Object> selectReviewWithMember(int currPage, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int maxCount = dao.getReviewCountMember(conn, memberNo);
		
		Pagination pagination = new Pagination(currPage, maxCount); 
		pagination.setLimit(3);
		pagination.setPageSize(10);
		
		List<Review> reviews = dao.selectReviewWithMember(conn, pagination, memberNo);
				
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("reviews", reviews);
		map.put("pagination", pagination);
				
		return map;
	}

	
		
}
