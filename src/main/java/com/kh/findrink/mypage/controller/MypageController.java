package com.kh.findrink.mypage.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.findrink.board.model.service.BoardService;
import com.kh.findrink.common.MyRenamePolicy;
import com.kh.findrink.common.Util;
import com.kh.findrink.member.model.controller.MemberService;
import com.kh.findrink.member.model.vo.Member;
import com.kh.findrink.mypage.model.service.MypageService;
import com.kh.findrink.review.model.service.ReviewService;
import com.kh.findrink.review.model.vo.Review;
import com.kh.findrink.store.model.controller.StoreService;
import com.kh.findrink.store.model.vo.Store;
import com.oreilly.servlet.MultipartRequest;



@WebServlet("/mypage/*")
public class MypageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/mypage/").length(), uri.indexOf("?") == -1 ? uri.length() : uri.indexOf("?"));

		switch (command) {
		case "user": req.getRequestDispatcher("/WEB-INF/views/mypage/user/usermy-pg.jsp").forward(req, resp); break;
		case "userInfo": req.getRequestDispatcher("/WEB-INF/views/mypage/user/usermycheck-pg.jsp").forward(req, resp); break;
		case "userReviewUpdate": req.getRequestDispatcher("/WEB-INF/views/mypage/user/usermyeditreview-pg.jsp").forward(req, resp); break;
		case "userCommunityUpdate" : req.getRequestDispatcher("/WEB-INF/views/mypage/user/usercomupdate-pg.jsp").forward(req, resp); break;
		case "userCheckPw": req.getRequestDispatcher("/WEB-INF/views/mypage/user/userpwcheck-pg.jsp").forward(req, resp); break;
		case "userReview": userReview(req, resp); break;
		case "userCommunity" : userCommunity(req, resp); break;
		case "secessionPage": req.getRequestDispatcher("/WEB-INF/views/common/secession.jsp").forward(req, resp); break;
		case "business": req.getRequestDispatcher("/WEB-INF/views/mypage/business/busmy-pg.jsp").forward(req, resp); break;
		case "businessInfo": req.getRequestDispatcher("/WEB-INF/views/mypage/business/busmycheck-pg.jsp").forward(req, resp); break;
		case "updateStore" : req.getRequestDispatcher("/WEB-INF/views/mypage/business/busmyupdatestore-pg.jsp").forward(req, resp); break;
		case "passwordCheckPage": passwordCheckPage(req, resp); break;
		case "passwordCheck": passwordCheck(req, resp); break;
		case "memberInfoUpdate": memberInfoUpdate(req, resp); break;
		case "businessReview":businessReview(req, resp);break;
		case "businessStore" : businessStore(req, resp); break;
		case "deleteBoard" : deleteBoard(req, resp); break;
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void passwordCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		Member loginMember = (Member) session.getAttribute("loginMember");

		String password = req.getParameter("password");
		if (password != null) {
			String salt = Util.getSalt(loginMember.getMemberId());
			String encryptPw = Util.encrypt(password + salt);

			try {
				int result = new MypageService().passwordCheck(loginMember.getMemberId(), encryptPw);

				if (result > 0) { 
					session.setAttribute("message", "비밀번호 확인에 성공했습니다.");

					switch (loginMember.getMemberCategory()) {
					case "user":
						req.getRequestDispatcher("/WEB-INF/views/mypage/user/usermyupdate-pg.jsp").forward(req, resp);
						break;
					case "business":
						req.getRequestDispatcher("/WEB-INF/views/mypage/business/busmyupdate-pg.jsp").forward(req, resp);
						break;
					}
				} else {
					session.setAttribute("message", "비밀번호를 다시 입력해주세요.");

					resp.sendRedirect(req.getHeader("referer"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void memberInfoUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();


		try {
			int maxSize = 1024 * 1024 * 20;

			String root = session.getServletContext().getRealPath("/");

			String folderPath = "/resources/images/memberProfile/";

			String filePath = root + folderPath; 

			String encoding = "UTF-8";

			MultipartRequest mpReq 
			= new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());

			Enumeration<String> files = mpReq.getFileNames();
			
			String memberProfileImage = null;
			
			while(files.hasMoreElements()) { 
				String name = files.nextElement(); 
				String rename = mpReq.getFilesystemName(name);
				
				if(rename != null) { 					
					memberProfileImage = (folderPath + rename);
				} 
			}
			
			Member member = (Member)session.getAttribute("loginMember");

			// 비밀번호 얻어올 때 무조건 이 둘 같이 얻어와야 함
			String salt = Util.makeSalt();
			salt = Util.encrypt(salt);
			
			String encryptPw = Util.encrypt(mpReq.getParameter("password") + salt);	
			
			member.setMemberPw(encryptPw);
			member.setMemberTel(mpReq.getParameter("phone"));
			member.setMemberNickname(mpReq.getParameter("nickname"));
			member.setMemberEmail(mpReq.getParameter("email"));
			member.setMemberIntro(mpReq.getParameter("intro"));
			member.setMemberProfileImage(memberProfileImage);
			member.setMemberSalt(salt);

			int result = new MypageService().memberInfoUpdate(member);

			if( result > 0 ) {
				session.setAttribute("message", "회원정보가 수정되었습니다.");			
				switch(member.getMemberCategory()) {
				case "user": req.getRequestDispatcher("/WEB-INF/views/mypage/user/usermycheck-pg.jsp").forward(req, resp); break;
				case "business": req.getRequestDispatcher("/WEB-INF/views/mypage/business/busmycheck-pg.jsp").forward(req, resp); break;
				}

			}else {
				session.setAttribute("message", "회원정보 수정에 실패했습니다.");	
				switch(member.getMemberCategory()) {
				case "user":	req.getRequestDispatcher("/WEB-INF/views/mypage/user/usermyupdate-pg.jsp").forward(req, resp); break;
				case "business": req.getRequestDispatcher("/WEB-INF/views/mypage/business/busmyupdate-pg.jsp").forward(req, resp); break;
				}

			}

		}catch( Exception e ) {
			e.printStackTrace();
		}

	}
	
	private void businessStore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			
			List<Store> stores = new StoreService().selectStore(memberNo);
			
			if( stores.isEmpty() ) {								
				req.getRequestDispatcher("/WEB-INF/views/mypage/business/nostorecheck-pg.jsp").forward(req, resp);
			}else {
				req.setAttribute("stores", stores);
				
				req.getRequestDispatcher("/WEB-INF/views/mypage/business/yesstorecheck-pg.jsp").forward(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void businessReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		try {
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			int currPage = 1;
			if( req.getParameter("cp") != null ) {
				currPage = Integer.parseInt(req.getParameter("cp"));
			};

			Map<String, Object> map = new ReviewService().selectReviewWithStore(currPage, memberNo);
				
			req.setAttribute("map", map);
			
			req.getRequestDispatcher("/WEB-INF/views/mypage/business/busreviewcheck-pg.jsp").forward(req, resp); 
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	private void userReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int cp = Integer.parseInt(req.getParameter("cp"));
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			
			Map<String, Object> map = new ReviewService().selectReviewWithMember(cp, memberNo);
						
			req.setAttribute("map", map);
			
			req.getRequestDispatcher("/WEB-INF/views/mypage/user/myreviewcheck-pg.jsp").forward(req, resp); 
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	private void userCommunity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int cp = Integer.parseInt(req.getParameter("cp"));
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			
			Map<String, Object> map = new BoardService().selectBoardWithMember(cp, memberNo);
			
			req.setAttribute("map", map);
			
			req.getRequestDispatcher("/WEB-INF/views/mypage/user/usercomcheck-pg.jsp").forward(req, resp);
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	private void passwordCheckPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession();

		Member loginMember = (Member) session.getAttribute("loginMember");
		switch (loginMember.getMemberCategory()) {
		case "user":
			req.getRequestDispatcher("/WEB-INF/views/mypage/user/userpwcheck-pg.jsp").forward(req, resp);
			break;
		case "business":
			req.getRequestDispatcher("/WEB-INF/views/mypage/business/buspwcheck-pg.jsp").forward(req, resp);
			break;
		}
	}
	
	private void deleteBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			
			int result = new BoardService().deleteBoard(boardNo);
			
			HttpSession session = req.getSession();
			String path = null;
			String message = null;
			
			if(result > 0) { // 성공
				message = "게시글 삭제에 성공했습니다.";
				path = req.getHeader("referer");
				
			} else { // 실패
				message = "게시글 삭제에 실패했습니다.";
				path = req.getHeader("referer");
			}
			
			session.setAttribute("message", message);

			resp.sendRedirect(path);
			
		}catch( Exception e ) {
			e.printStackTrace();
		}
	}
}