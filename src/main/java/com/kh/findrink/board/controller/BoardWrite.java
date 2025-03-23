
package com.kh.findrink.board.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.findrink.board.model.service.BoardService;
import com.kh.findrink.board.model.vo.BoardDetail;
import com.kh.findrink.board.model.vo.BoardImage;
import com.kh.findrink.common.MyRenamePolicy;
import com.kh.findrink.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/board/write")
public class BoardWrite extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String mode = req.getParameter("mode"); // insert / update 구분
			
			if(mode.equals("update")) {
				int cp = 1;
				if( req.getParameter("cp") != null ) {
					cp = Integer.parseInt(req.getParameter("cp"));
				}
				
				int boardNo = Integer.parseInt( req.getParameter("no") );
				
				BoardDetail detail = new BoardService().selectBoardDetail(cp, boardNo);
		
				detail.setBoardContent(detail.getBoardContent().replaceAll("<br>", "\n"));
				
				System.out.println(detail);
				
				req.setAttribute("detail", detail); // jsp에서 사용할 수 있도록 req에 값 세팅
			}
			
			String path = "/WEB-INF/views/community/comadd-pg.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int maxSize = 1024 * 1024 * 100; 

			HttpSession session = req.getSession();
			String root = session.getServletContext().getRealPath("/"); 
			String folderPath = "/resources/images/board/";
			String filePath = root + folderPath;

			String encoding = "UTF-8"; 
			
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
			
			Enumeration<String> files = mpReq.getFileNames();
			// file 타입 태그의 name 속성 0,1,2,3,4가 순서가 섞인 상태로 얻어와짐
			
			// * 업로드된 이미지의 정보를 모아둘 List 생성
			List<BoardImage> imageList = new ArrayList<BoardImage>();
			
			while(files.hasMoreElements()) { 
				String name = files.nextElement(); 
				
				String rename = mpReq.getFilesystemName(name); 	   	// 변경된 파일명
				String original = mpReq.getOriginalFileName(name); 	// 원본 파일명
				
				if(rename != null) { 
					BoardImage image = new BoardImage();
					
					image.setImageOriginal(original); // 원본명 (다운로드 시 사용)
					image.setImageReName(folderPath + rename); // 폴더 경로 + 변경명
					image.setImageLevel( Integer.parseInt(name) ); // 이미지 위치(0은 썸네일)
					
					imageList.add(image); // 리스트에 추가
					
				} // if 끝
				
			} // while 끝
			
			// * 이미지를 제외한 게시글 관련 정보 *
			String boardParent = mpReq.getParameter("boardCategory");
			String boardTitle = mpReq.getParameter("boardTitle");
			String boardContent = mpReq.getParameter("boardContent");
			int boardCode = Integer.parseInt(mpReq.getParameter("type"));
			
			Member loginMember = (Member)session.getAttribute("loginMember");
			int memberNo = loginMember.getMemberNo(); // 회원 번호
			
			// 게시글 관련 정보를 하나의 객체(BoardDetail)에 담기
			BoardDetail detail = new BoardDetail();
			
			detail.setBoardCategory(boardParent);
			detail.setBoardTitle(boardTitle);
			detail.setBoardContent(boardContent);
			detail.setMemberNo(memberNo);
			
			BoardService service = new BoardService();

			// 모드 (insert/update)에 따라 추가 파라미터 얻어오기 및 서비스 호출
			String mode = mpReq.getParameter("mode"); // hidden
			
			if(mode.equals("insert")) { // 삽입
				
				int result = service.insertBoard(detail, imageList, boardCode);
				
				String path = null;
				
				if( result > 0) { // 성공
					session.setAttribute("message", "게시글이 등록되었습니다.");
					path = "list?no=" + detail.getBoardNo() + "&type=" + boardCode;
					
				} else { // 실패
					session.setAttribute("message", "게시글 등록 실패");
					
					path = "write?mode=" + mode + "&type=" + boardCode;
				}
				
				resp.sendRedirect(path); // 리다이렉트
			}
			
			if(mode.equals("update")) { // 수정
				int boardNo = Integer.parseInt( mpReq.getParameter("no") );
				
				int cp = Integer.parseInt( mpReq.getParameter("cp") );
				
				String deleteList = mpReq.getParameter("deleteList");
				
				detail.setBoardNo(boardNo);
				
				String path = null;
				String message = null;
			
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
}