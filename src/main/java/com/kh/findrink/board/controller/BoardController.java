package com.kh.findrink.board.controller;

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
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/board/").length(),
				uri.indexOf('?') == -1 ? uri.length() : uri.indexOf('?'));

		System.out.println("BOARD START~");

		System.out.println(command);

		switch (command) {

		case "list":
			try {
				int type = Integer.parseInt(req.getParameter("type"));
				int cp = 1;
				if (req.getParameter("cp") != null) { // 쿼리스트링에 "cp"가 존재한다면
					cp = Integer.parseInt(req.getParameter("cp"));
				}

//				http://localhost:8082/findrink_1/board/list?type=3
				BoardService service = new BoardService();

				Map<String, Object> map = service.selectBoardList(type, cp);

				req.setAttribute("map", map);

				req.getRequestDispatcher("/WEB-INF/views/community/community-pg.jsp").forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

			
		case "detail":
			try {
				int boardNo = Integer.parseInt(req.getParameter("no"));

//				http://localhost:8082/findrink_1/board/detail?no=7&cp=1&type=3
				
				
				int cp = 1;
				if( req.getParameter("cp") != null ) {
					cp = Integer.parseInt(req.getParameter("cp"));
				}
				
				BoardDetail detail = new BoardService().selectBoardDetail(cp, boardNo);
				
				System.out.println(detail);
				
				req.setAttribute("detail", detail);

				req.getRequestDispatcher("/WEB-INF/views/community/comdetail-pg.jsp").forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "detailAjax" : 
			try {
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				int cp = 1;
				if( req.getParameter("cp") != null ) {
					cp = Integer.parseInt(req.getParameter("cp"));
				}
				
				BoardDetail detail = new BoardService().selectBoardDetail(cp, boardNo);
				
				new Gson().toJson(detail, resp.getWriter());
			} catch( Exception e ) {
				e.printStackTrace();
			}
			break;

//		case "write": 
//			try {
//				String mode = req.getParameter("mode");
//
//				if (mode.equals("update")) {
//					
//					int boardNo = Integer.parseInt( req.getParameter("no") );
//					BoardDetail detail = new BoardService().selectBoardDetail(boardNo);
//					detail.setBoardContent(detail.getBoardContent().replaceAll("<br>", "\n"));
//					
//					req.setAttribute("detail", detail);
//					
//				}
//				
//				req.getRequestDispatcher("/WEB-INF/views/community/comadd-pg.jsp").forward(req, resp);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			break;
//			
//		case "?": imgWriteBoard(req, resp);
//			break;
		case "delete": 
			try {
				int type = Integer.parseInt(req.getParameter("type"));
				int boardNo = Integer.parseInt(req.getParameter("no"));
				
				int result = new BoardService().deleteBoard(boardNo);
				
				HttpSession session = req.getSession();
				String path = null;
				String message = null;
				
				if(result > 0) { // 성공
					message = "게시글 삭제에 성공했습니다.";
					path = "list?type=" + type; 
					
					
				} else { // 실패
					message = "게시글 삭제에 실패했습니다.";
					path = req.getHeader("referer");
				
				}
				
				session.setAttribute("message", message);

				resp.sendRedirect(path);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;

		case "heart" : insertHeart(req, resp); break;
		case "report" : insertReport(req, resp); break;
		}
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private void insertHeart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		
		try {
			int result = new BoardService().insertHeart(boardNo, memberNo);
			
			resp.getWriter().print(result);
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
		
		try {
			int result = new BoardService().insertReport(boardNo, memberNo, rtMemberNo, reportReason);
			
			resp.getWriter().print(result);

		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

}


//	private void selectList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	private void selectBoardDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
//	private void writeBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}

//	private void imgWriteBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
//			int maxSize = 1024 * 1024 * 100;
//
//			HttpSession session = req.getSession();
//			String root = session.getServletContext().getRealPath("/");
//			String folderPath = "/resources/images/board/";
//			String filePath = root + folderPath;
//			String encoding = "UTF-8";
//			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
//
//			Enumeration<String> files = mpReq.getFileNames();
//
//			List<BoardImage> imageList = new ArrayList<BoardImage>();
//
//			while (files.hasMoreElements()) {
//				String name = files.nextElement();
//
//				String rename = mpReq.getFilesystemName(name); // 변경된 파일명
//				String original = mpReq.getOriginalFileName(name); // 원본 파일명
//
//				if (rename != null) {
//					BoardImage image = new BoardImage();
//
//					image.setImageOriginal(original); // 원본명 (다운로드 시 사용)
//					image.setImageReName(folderPath + rename); // 폴더 경로 + 변경명
//					image.setImageLevel(Integer.parseInt(name)); // 이미지 위치(0은 썸네일)
//
//					imageList.add(image);
//				}
//
//			}
//			String boardCategory = mpReq.getParameter("boardCategory");
//			String boardTitle = mpReq.getParameter("boardTitle");
//			String boardContent = mpReq.getParameter("boardContent");
//			int boardCode = Integer.parseInt(mpReq.getParameter("type"));
//
//			Member loginMember = (Member) session.getAttribute("loginMember");
//			int memberNo = loginMember.getMemberNo(); // 회원 번호
//
//			BoardDetail detail = new BoardDetail();
//
//			detail.setBoardTitle(boardTitle);
//			detail.setBoardContent(boardContent);
//			detail.setMemberNo(memberNo);
//
//			BoardService service = new BoardService();
//
//			String mode = mpReq.getParameter("mode"); // hidden
//
//			if (mode.equals("insert")) { // 삽입
//
//				// 게시글 삽입 서비스 호출 후 결과 반환 받기
//				// -> 반환된 게시글 번호를 이용해서 상세조회로 리다이렉트 예정
//				int boardNo = service.insertBoard(detail, imageList, boardCode);
//
//				String path = null;
//
//				if (boardNo > 0) { // 성공
//					session.setAttribute("message", "게시글이 등록되었습니다.");
//					path = "detail?no=" + boardNo + "&type=" + boardCode;
//
//				} else { // 실패
//					session.setAttribute("message", "게시글 등록 실패");
//
//					path = "write?mode=" + mode + "&type=" + boardCode;
//				}
//
//				resp.sendRedirect(path); // 리다이렉트
//			}
//
//			if (mode.equals("update")) { // 수정
//				int boardNo = Integer.parseInt(mpReq.getParameter("no"));
//
//				int cp = Integer.parseInt(mpReq.getParameter("cp"));
//				String deleteList = mpReq.getParameter("deleteList"); // 1,2,3
//
//				detail.setBoardNo(boardNo);
//				int result = service.updateBoard(detail, imageList, deleteList);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
