package com.kh.findrink.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath+"/admin/").length());
		
		switch(command) {
		case "commentManage": req.getRequestDispatcher("/WEB-INF/views/admin/comment-mn.jsp").forward(req, resp); break;
		case "communityManage": req.getRequestDispatcher("/WEB-INF/views/admin/community-mn.jsp").forward(req, resp); break;
		case "memberManage": req.getRequestDispatcher("/WEB-INF/views/admin/member-mn.jsp").forward(req, resp); break;
		case "reviewManage": req.getRequestDispatcher("/WEB-INF/views/admin/review-mn.jsp").forward(req, resp); break;
		case "storeManage": req.getRequestDispatcher("/WEB-INF/views/admin/store-mn.jsp").forward(req, resp); break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
