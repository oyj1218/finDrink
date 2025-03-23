package com.kh.findrink.member.controller;

import java.io.IOException;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.findrink.common.Util;
import com.kh.findrink.member.model.controller.MemberService;
import com.kh.findrink.member.model.vo.Member;

@WebServlet("/member/*")
public class MemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/member/").length());

		switch (command) {
		case "signUpPage": req.getRequestDispatcher("/WEB-INF/views/login-signup/signup-pg.jsp").forward(req, resp); break;
		case "signUp": signUp(req, resp); break;
		case "loginPage": req.getRequestDispatcher("/WEB-INF/views/login-signup/login-pg.jsp").forward(req, resp); break;
		case "login": login(req, resp); break;
		case "logout": logout(req, resp); break;
		case "idDupCheck": idDupCheck(req, resp); break;
		case "nicknameDupCheck" : nicknameDupCheck(req, resp); break;
		case "secession": secession(req, resp); break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String salt = Util.getSalt(req.getParameter("id"));
			String encrypt = Util.encrypt(req.getParameter("password") + salt);

			String id = req.getParameter("id");
			String password = new MemberService().selectPw(id);

			Member loginMember = null;
			
			HttpSession session = req.getSession();
			
			if (password.equals(encrypt)) {
				loginMember = new MemberService().login(id, password);
		
				session.setAttribute("loginMember", loginMember);
				
				Cookie c = new Cookie("saveId", id);
				
				if(req.getParameter("saveId") != null) {
					c.setMaxAge(60 * 60 * 24 * 30);
				} else {
					c.setMaxAge(0);
				}
				
				c.setPath( req.getContextPath() );
				
				resp.addCookie(c);
				
				session.setAttribute("message", loginMember.getMemberNickname()+ "님 환영합니다.");
				
				resp.sendRedirect(req.getContextPath());
			} else {
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
									
				req.getRequestDispatcher("/WEB-INF/views/login-signup/login-pg.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("id"); 
			String origin = req.getParameter("password");
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			String nickName = req.getParameter("nickname");
			String email = req.getParameter("email");
			String intro = req.getParameter("intro");
			String category = req.getParameter("member");

			String salt = Util.makeSalt();
			salt = Util.encrypt(salt);

			String encryptPw = Util.encrypt(origin + salt);

			Member newMem = new Member(); 
			newMem.setMemberId(id);
			newMem.setMemberPw(encryptPw);
			newMem.setMemberName(name);
			newMem.setMemberTel(phone);
			newMem.setMemberNickname(nickName);
			newMem.setMemberEmail(email);
			newMem.setMemberIntro(intro);
			newMem.setMemberSalt(salt);
			newMem.setMemberCategory(category);

			int result = new MemberService().signUp(newMem);

			String path = "";
			String message = null;

			if( result > 0 ) { 
				
				message = "회원가입에 성공했습니다.";
				path = req.getContextPath() + "/member/loginPage";
			} else { 
				message = "회원가입에 실패했습니다.";
				path = req.getContextPath() + "/member/signUpPage"; 
			}

			HttpSession session = req.getSession();
			
			session.setAttribute("message", message);
			
			resp.sendRedirect(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void idDupCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String memberId = req.getParameter("memberId");

			int result = new MemberService().idDupCheck(memberId);

			resp.getWriter().print(result);
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	private void nicknameDupCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			String memberNickname = req.getParameter("nickname");

			int result = new MemberService().nicknameDupCheck(memberNickname);

			resp.getWriter().print(result);
		}catch( Exception e ) {
			e.printStackTrace();
		}
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		session.invalidate();	

		resp.sendRedirect(req.getContextPath());
	}

	private void secession(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		Member loginMember = (Member)session.getAttribute("loginMember");

		String password = req.getParameter("password");
		String salt = Util.getSalt(loginMember.getMemberId());
		String encryptPw = Util.encrypt(password + salt);

		String message = null;
		try {			
			int result = new MemberService().secessionMember(loginMember.getMemberNo(), encryptPw);
			if( result > 0 ) {
				message = "회원탈퇴에 성공했습니다.";
				logout(req, resp);;
			} else {
				message = "회원탈퇴에 실패했습니다.";
				resp.sendRedirect("secession");
			}
			
			session.setAttribute("message", message);
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

}
