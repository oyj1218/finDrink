package com.kh.findrink.review.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.findrink.board.model.service.BoardService;
import com.kh.findrink.board.model.vo.BoardDetail;
import com.kh.findrink.board.model.vo.BoardImage;
import com.kh.findrink.common.MyRenamePolicy;
import com.kh.findrink.member.model.vo.Member;
import com.kh.findrink.review.model.service.ReviewService;
import com.kh.findrink.review.model.vo.ReviewDetail;
import com.kh.findrink.review.model.vo.Reviewer;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/review/*")
public class ReviewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/review/").length(), uri.indexOf('?') == -1 ? uri.length() : uri.indexOf('?'));
				
		switch(command) {
		case "list": selectList(req, resp); break;
		case "detail": selectReviewDetail(req, resp); break;
		case "selectMember": selectMember(req, resp); break;
		case "insertPage" : insertPage(req, resp); break;
		case "insertReview": insertReview(req, resp); break;
		case "deleteList" : deleteReviewList(req, resp); break;
		case "deleteDetail" : deleteDetail(req, resp); break;
		case "heart" : insertHeart(req, resp); break;
		case "refreshReviews" : refreshReviews(req, resp); break;
		case "report" : insertReport(req, resp); break;
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private void selectList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int currPage = Integer.parseInt(req.getParameter("cp"));
			
			Map<String, Object> items = new ReviewService().selectAllReview(currPage);
			
			req.setAttribute("items", items);
			
			req.getRequestDispatcher("/WEB-INF/views/review/reviewall-pg.jsp").forward(req, resp);
			
		}catch( Exception e ) {
			e.printStackTrace();
		}
	}

	
	private void selectReviewDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			int reviewNo = Integer.parseInt(req.getParameter("reviewNo"));
			
			int cp = 1;
			if( req.getParameter("cp") != null ) {
				cp = Integer.parseInt(req.getParameter("cp"));
			}
			ReviewDetail reviewDetail = new ReviewService().selectReviewDetail(cp, reviewNo);
						
			req.setAttribute("detail", reviewDetail);
			
			req.getRequestDispatcher("/WEB-INF/views/review/reviewdetail-pg.jsp").forward(req, resp);
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	private void selectMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {			
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			
			Reviewer reviewer = new ReviewService().selectReviewer(memberNo);
			if( reviewer != null ) {
				req.setAttribute("reviewer", reviewer);
				
				req.getRequestDispatcher("/WEB-INF/views/review/reviewuser-pg.jsp").forward(req, resp);
			} else {
				resp.sendRedirect(req.getHeader("referer"));
			}
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	

	private void insertPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String mode = req.getParameter("mode");
		
		if( mode.equals("update") ) {
			try {
				int cp = Integer.parseInt(req.getParameter("cp"));
				int boardNo = Integer.parseInt(req.getParameter("no"));
				
				ReviewDetail detail = new ReviewService().selectReviewDetail(cp, boardNo);
				
				req.setAttribute("review", detail);
			} catch( Exception e ) {
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher("/WEB-INF/views/review/reviewadd-pg.jsp").forward(req, resp);
	}

	private void insertReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		try {
			int maxSize = 1024 * 1024 * 100; 

			HttpSession session = req.getSession();
			String root = session.getServletContext().getRealPath("/"); 
			String folderPath = "/resources/images/board/";
			String filePath = root + folderPath;

			String encoding = "UTF-8"; 
			
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
			
			Enumeration<String> files = mpReq.getFileNames();

			List<BoardImage> imageList = new ArrayList<BoardImage>();
			
			while(files.hasMoreElements()) { 
				String name = files.nextElement(); 
				
				String rename = mpReq.getFilesystemName(name);
				String original = mpReq.getOriginalFileName(name);
				
				if(rename != null) { 
					BoardImage image = new BoardImage();
					
					image.setImageOriginal(original);
					image.setImageReName(folderPath + rename);
					image.setImageLevel( Integer.parseInt(name) );
					
					imageList.add(image); 
				} 
			} 
			
			String boardParent = mpReq.getParameter("boardCategory");
			String boardTitle = mpReq.getParameter("boardTitle");
			String boardContent = mpReq.getParameter("boardContent");
			int boardCode = Integer.parseInt(mpReq.getParameter("type"));
			int cp = 1;
			if( req.getParameter("cp") != null ) {
				cp = Integer.parseInt(req.getParameter("cp"));
			}
						
			Member loginMember = (Member)session.getAttribute("loginMember");
			int memberNo = loginMember.getMemberNo();
			
			BoardDetail detail = new BoardDetail();
			
			detail.setBoardCategory(boardParent);
			detail.setBoardTitle(boardTitle);
			detail.setBoardContent(boardContent);
			detail.setMemberNo(memberNo);
			
			BoardService service = new BoardService();

			String mode = mpReq.getParameter("mode");
			
			if(mode.equals("insert")) { // 삽입
				int result = service.insertBoard(detail, imageList, boardCode);
				
				String path = null;
				
				if( result > 0) { // 성공
					session.setAttribute("message", "게시글이 등록되었습니다.");
					path = "list?&type=" + boardCode + "&cp=" + cp;
					
				} else { // 실패
					session.setAttribute("message", "게시글 등록 실패");
					
					path = "write?mode=" + mode + "&type=" + boardCode;
				}
				
				resp.sendRedirect(path); // 리다이렉트
			}
			
			if(mode.equals("update")) { // 수정
				int boardNo = Integer.parseInt( mpReq.getParameter("no") );
				
				cp = Integer.parseInt( mpReq.getParameter("cp") );
				
				String deleteList = mpReq.getParameter("deleteList");
				
				detail.setBoardNo(boardNo);
				
				String message = null;
				String path = null;
				
				int result = service.updateBoard(detail, imageList, deleteList);
				if( result > 0 ) {
					message = "게시글이 수정되었습니다.";
					path = "list?no=" + boardNo + "&type=" + boardCode + "&cp=" + cp;
				} else {
					message = "게시글 수정 실패";
					path = req.getHeader("referer");
				}
				
				session.setAttribute("message", message);
				
				resp.sendRedirect(path);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

	private void deleteReviewList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			int reviewNo = Integer.parseInt(req.getParameter("reviewNo"));
			
			int result = new ReviewService().deleteReview(reviewNo);
			
			resp.getWriter().print(result);
			
		}catch( Exception e ) {
			e.printStackTrace();
		}	
	}
	
	private void deleteDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int reviewNo = Integer.parseInt(req.getParameter("reviewNo"));
			
			int result = new ReviewService().deleteReview(reviewNo);
			
			resp.getWriter().print(result);
		}catch( Exception e ) {
			e.printStackTrace();
		}	
	}

	private void insertHeart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		
		try {
			int result = new ReviewService().insertHeart(boardNo, memberNo);
			
			resp.getWriter().print(result);
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	private void refreshReviews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int currPage = Integer.parseInt(req.getParameter("currPage"));
			
			Map<String, Object> map = new ReviewService().selectAllReview(currPage);
			
			new Gson().toJson(map, resp.getWriter());
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	private void insertReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("hi~");
		
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		int rtMemberNo = Integer.parseInt(req.getParameter("rtMemberNo"));
		String reportReason = req.getParameter("reportReason");
		
	    System.out.println("boardNo");
	    System.out.println("memberNo");
	    System.out.println("rtMemberNo");
	    System.out.println("reportReason");
		
		try {
			int result = new ReviewService().insertReport(boardNo, memberNo, rtMemberNo, reportReason);
			
			resp.getWriter().print(result);

		} catch( Exception e ) {
			e.printStackTrace();
		}
	}


}
