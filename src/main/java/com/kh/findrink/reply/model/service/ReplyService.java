package com.kh.findrink.reply.model.service;
import static com.kh.findrink.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.findrink.board.model.vo.Pagination;
import com.kh.findrink.common.Util;
import com.kh.findrink.reply.model.dao.ReplyDAO;
import com.kh.findrink.reply.model.vo.Reply;

public class ReplyService {

	private ReplyDAO dao = new ReplyDAO();

	/** 댓글 목록 조회 Service
	 * @param boardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReply(int boardNo) throws Exception {

		Connection conn = getConnection(); 

		// 댓글 목록 조회 SQL 수행 후 결과 반환 받기
		List<Reply> rList = dao.selectReplyList(conn, boardNo);
			
		close(conn);
		
		return rList;
	}
/** 댓글 작성 Service
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Reply reply) throws Exception {
		
		Connection conn = getConnection();

		// Cross Site Scripting(XSS, 크로스 사이트 스크립팅) 공격 
		reply.setReplyContent( Util.XSSHandling( reply.getReplyContent() ));
		reply.setReplyContent( Util.newLineHandling(reply.getReplyContent() ));

		
		int result = dao.insertReply(conn, reply);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}
    /** 댓글 삭제 Service
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(int replyNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.deleteReply(conn, replyNo);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 댓글 수정 Service
	 * @param replyNo
	 * @param replyContent
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(int replyNo, String replyContent) throws Exception{
		
		Connection conn = getConnection();
		
		// XSS 처리
		replyContent = Util.XSSHandling(replyContent);
		
		// 개행문자 처리
		replyContent = Util.newLineHandling(replyContent);
		
		int result = dao.updateReply(conn, replyNo, replyContent);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	public Map<String, Object> selectReplyWithPagination(int currPage, int boardNo) throws Exception{
		Connection conn = getConnection();
				
		int maxCount = dao.getReplyCount(conn, boardNo);
		
		Pagination pagination = new Pagination(currPage, maxCount);
		pagination.setLimit(3);
		pagination.setPageSize(10);
		
		List<Reply> replyList = dao.selectReplyPagination(conn, boardNo, pagination);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("reply", replyList);
		map.put("pagination", pagination);
		
		return map;
	}
}