package com.kh.findrink.board.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kh.findrink.common.JDBCTemplate.*;

import com.kh.findrink.board.model.dao.BoardDAO;
import com.kh.findrink.board.model.vo.Board;
import com.kh.findrink.board.model.vo.BoardDetail;
import com.kh.findrink.board.model.vo.BoardImage;
import com.kh.findrink.board.model.vo.Pagination;
import com.kh.findrink.common.Util;
import com.kh.findrink.reply.model.service.ReplyService;

public class BoardService {

	private BoardDAO dao = new BoardDAO();

	/** 게시글 목록 조회 Service
	 * @param type
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardList(int type, int cp) throws Exception {
		
		Connection conn = getConnection();
		
		// 게시판 이름 조회
		String boardName = dao.selectBoardName(conn, type);
		
		// 게시판 수
		int listCount = dao.getListCount(conn, type);
		
		// 게시판 목록
		Pagination pagination = new Pagination(cp, listCount);
		pagination.setLimit(5);
		pagination.setPageSize(10);
		
		List<Board> boardList = dao.selectBoardList(conn, pagination, type);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);

		return map; // Map 객체 반환
	}


// ------------------------

	/** 게시글 상세 조회 Service
	 * @param boardNo
	 * @return detail
	 * @throws Exception
	 */
	public BoardDetail selectBoardDetail(int currPage, int boardNo) throws Exception {
		
		Connection conn = getConnection();
		
		// 게시판 상세
		BoardDetail detail = dao.selectBoardDetail(conn, boardNo);
		
		if(detail != null) { 
			
			// 만약 상세보기가 있다면 게시판 이미지도 불러오기
			List<BoardImage> imageList = dao.selectImageList(conn, boardNo);
			
			detail.setImageList(imageList);
			
//			// 만약 상세보기가 있다면 댓글도 불러오기
			Map<String, Object> replys = new ReplyService().selectReplyWithPagination(currPage, boardNo);
			detail.setReplys(replys);
			
		}
		
		close(conn);
		return detail;
	}

// ------------------------
	
	/** 게시글 등록 Service
	 * @param detail
	 * @param imageList
	 * @param boardCode
	 * @return boardNo
	 * @throws Exception
	 */
	public int insertBoard(BoardDetail detail, List<BoardImage> imageList, int boardCode) throws Exception {
		
		Connection conn = getConnection();
	
		detail.setBoardTitle( Util.XSSHandling(detail.getBoardTitle()) );
		detail.setBoardContent( Util.XSSHandling(detail.getBoardContent()) );
		detail.setBoardContent( Util.newLineHandling(detail.getBoardContent()) );
		

		// 게시글 등록
		int result = dao.insertBoard(conn, detail, boardCode);
		
		int boardNo = dao.getBoardNo(conn);
		
		detail.setBoardNo(boardNo);
		
		if(result > 0) { 
			for(BoardImage image : imageList) { 	
				image.setBoardNo(boardNo);
				// 게시글 등록할 때 이미지도 등록할 수 있음
				result = dao.insertBoardImage(conn, image);
				
				if(result == 0) { 
					break;
					
				}
			} // for문 끝
		} // if문 끝
		
		// 트랜잭션
		if(result > 0)	commit(conn); 
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	// ---------------------------------------
	
	
	/** 게시글 수정 Service
	 * @param detail
	 * @param imageList
	 * @param deleteList
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(BoardDetail detail, List<BoardImage> imageList, String deleteList) throws Exception {
		
		Connection conn = getConnection();
		
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardContent(Util.XSSHandling(detail.getBoardContent()));
		
		detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));
		
		// 수정하기
		int result = dao.updateBoard(conn, detail);
		
		if(result > 0) { // 게시글 수정 성공 시
			
			for(BoardImage img : imageList) {
				
				img.setBoardNo(detail.getBoardNo());
				result = dao.updateBoardImage(conn, img);
				
				if(result == 0) {
					result = dao.insertBoardImage(conn, img);
				}
			} 
			
			
			if(!deleteList.equals("")) { 
				result = dao.deleteBoardImage(conn, deleteList, detail.getBoardNo());
			}
			
		} // 게시글 수정 성공 시 if 끝

		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}
	
// ---------------------------------------	
	
	
	/** 게시글 삭제 Service
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */

	public int deleteBoard(int boardNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.deleteBoard(conn, boardNo);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
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


	public Map<String, Object> selectBoardWithMember(int cp, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int maxCount = dao.getListCountMember(conn, memberNo);
				
		Pagination pagination = new Pagination(cp, maxCount);
		pagination.setLimit(5);
		pagination.setPageSize(10);
		
		List<Board> boards = dao.selectBoardWithMember(conn, pagination, memberNo);
		

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boards", boards);
		map.put("pagination", pagination);
		
		close(conn);

		return map; // Map 객체 반환
	}
		
}
