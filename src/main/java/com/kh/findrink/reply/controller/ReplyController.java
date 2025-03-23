package com.kh.findrink.reply.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.findrink.common.Util;
import com.kh.findrink.member.model.controller.MemberService;
import com.kh.findrink.member.model.vo.Member;
import com.kh.findrink.reply.controller.ReplyController;
import com.kh.findrink.reply.model.service.ReplyService;
import com.kh.findrink.reply.model.vo.Reply;


@WebServlet("/reply/*") // reply로 시작하는 모든 요청을 받음
public class ReplyController extends HttpServlet {
	
	// /reply/selectReplyList
	// /reply/insert
	// /reply/update
	// /reply/delete
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET방식 요청 처리
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/reply/").length()  );
					
		ReplyService service = new ReplyService();

		try {
			// 댓글 목록 조회 요청인 경우
			if(command.equals("selectReplyList")) {
				
				// 파라미터를 얻어와 정수 형태로 파싱
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				// 댓글 목록 조회 서비스 호출 후 결과 반환 받기
				List<Reply> rList = service.selectReply(boardNo);
				
				// JSON 변환 + 응답
				new Gson().toJson(rList, resp.getWriter());

			}

			// 댓글 등록
			if(command.equals("insert")) {
				
				// 파라미터 얻어오기
				String replyContent = req.getParameter("replyContent");
				int memberNo = Integer.parseInt(req.getParameter("memberNo"));
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				
				// Reply 객체를 생성해서 파라미터 담기
				Reply reply = new Reply();
				reply.setReplyContent(replyContent);
				reply.setBoardNo(boardNo);
				reply.setMemberNo(memberNo);
				
				
				if( req.getParameter("parentReplyNo") != null ) {
					int parentNo = Integer.parseInt(req.getParameter("parentReplyNo"));
					reply.setParentReplyNo(parentNo);
				}
				// 댓글 등록(insert) 서비스 호출 후 결과 반환 받기
				
				int result = service.insertReply(reply);
				
				// 서비스 호출 결과를 그대로 응답 데이터로 내보내기
				resp.getWriter().print(result);
				
			}
			
			// 댓글 삭제
			if(command.equals("delete")) {
				
				int replyNo = Integer.parseInt( req.getParameter("replyNo") );
				
				int result = service.deleteReply(replyNo);
				
				resp.getWriter().print(result);
				
			}

			// 댓글 수정
			if(command.equals("update")) {
				
				int replyNo = Integer.parseInt( req.getParameter("replyNo") );
				String replyContent = req.getParameter("replyContent");
				
				int result = service.updateReply(replyNo, replyContent);
				
				resp.getWriter().print(result);
			}
			
			if( command.equals("selectReplyPagination") ) {
				int currPage = Integer.parseInt(req.getParameter("cp"));
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				Map<String, Object> replys = service.selectReplyWithPagination(currPage, boardNo);
				
				new Gson().toJson(replys, resp.getWriter());
			}
			
		} catch (Exception e) {
				e.printStackTrace();
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// POST방식 요청 처리
		doGet(req, resp); // POST로 전달된 요청을 doGet()으로 전달하여 수행
	}

}




// @WebServlet("/reply/*")
// public class ReplyController extends HttpServlet{

// 	@Override
// 	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 		String uri = req.getRequestURI();
// 		String contextPath = req.getContextPath();
// 		String command = uri.substring(  (contextPath + "/reply/").length()  );
		
// 		switch(command) {
// 		case "select": selectReplyList(req, resp); break;
// 		case "insert": insertReply(req, resp); break;
// 		case "delete": deleteReply(req, resp); break;	
// 		case "update": updateReply(req, resp); break;
		
// 		}
// 	}
	
// 	@Override
// 	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 		doGet(req, resp);
// 	}



// 	private void selectReplyList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

// 		try {
// 			ReplyService service = new ReplyService();
			
// 			if(command.equals("selectReplyList")) {
// 				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
// 				List<Reply> rList = service.selectReplyList(boardNo);
				
// 				new Gson().toJson(rList, resp.getWriter());
			
// 		}catch( Exception e ) {
// 			e.printStackTrace();
// 		}
// 	}

// 	private void insertReply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
// 		try {

// 			if(command.equals("insert")) {
				
// 				// 파라미터 얻어오기
// 				String replyContent = req.getParameter("replyContent");
// 				int memberNo = Integer.parseInt(req.getParameter("memberNo"));
// 				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
// 				// Reply 객체를 생성해서 파라미터 담기
// 				Reply reply = new Reply();
// 				reply.setReplyContent(replyContent);
// 				reply.setMemberNo(memberNo);
// 				reply.setBoardNo(boardNo);
				
// 				// 댓글 등록(insert) 서비스 호출 후 결과 반환 받기
// 				int result = service.insertReply(reply);
				
// 				// 서비스 호출 결과를 그대로 응답 데이터로 내보내기
// 				resp.getWriter().print(result);
				
// 			}
			
// 		}catch( Exception e ) {
// 			e.printStackTrace();
// 		}
// 	}
	
	
	
// 	private void deleteReply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
// 		try {
// 			ReplyService service = new ReplyService();
			
// 			int replyNo = Integer.parseInt(req.getParameter(""));
			
// 			int result = service.deleteReply(replyNo);
			
// 			HttpSession session = req.getSession();
			
// 			if( result > 0 ) {
// 				session.setAttribute("message", "");
// 			} else {
// 				session.setAttribute("message", "");
// 			}
			
// 			resp.sendRedirect("delete");
			
// 		}catch( Exception e ) {
// 			e.printStackTrace();
// 		}
// 	}

// 	private void updateReply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
// 		try {
			
// 		}catch( Exception e ) {
// 			e.printStackTrace();
// 		}
// 	}


	// @Override
	// protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// 	// GET방식 요청 처리
	// 	String uri = req.getRequestURI();
	// 	String contextPath = req.getContextPath();
	// 	String command = uri.substring(  (contextPath + "/reply/").length()  );
					
	// 	ReplyService service = new ReplyService();

	// 	try {
	// 		// 댓글 목록 조회 요청인 경우
	// 		if(command.equals("selectReplyList")) {
				
	// 			// 파라미터를 얻어와 정수 형태로 파싱
	// 			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
	// 			// 댓글 목록 조회 서비스 호출 후 결과 반환 받기
	// 			List<Reply> rList = service.selectReplyList(boardNo);
				
	// 			// JSON 변환 + 응답
	// 			new Gson().toJson(rList, resp.getWriter());

	// 		}

	// 	} catch (Exception e) {
	// 			e.printStackTrace();
	// 	}
	
	// }
	
	// @Override
	// protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// 	// POST방식 요청 처리
	// 	doGet(req, resp); // POST로 전달된 요청을 doGet()으로 전달하여 수행
	// }

	
