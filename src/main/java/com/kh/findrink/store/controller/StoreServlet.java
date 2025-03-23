package com.kh.findrink.store.controller;

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

import com.kh.findrink.board.model.vo.BoardImage;
import com.kh.findrink.common.MyRenamePolicy;
import com.kh.findrink.store.model.controller.StoreService;
import com.kh.findrink.store.model.vo.Store;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/store/*")
public class StoreServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/store/").length());
		
		switch(command) {
		case "insertPage" : insertPage(req, resp); break;
		case "insert": insertStore(req, resp); break;
		case "delete": break;
		}
	}
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void insertPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/mypage/business/busmyupdatestore-pg.jsp").forward(req, resp);
	}
	
	private void insertStore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int maxSize = 1024 * 1024 * 100; 

			HttpSession session = req.getSession();
			String root = session.getServletContext().getRealPath("/"); 
			String folderPath = "/resources/images/store/";
			String filePath = root + folderPath;

			String encoding = "UTF-8"; 
			
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
			
			Enumeration<String> files = mpReq.getFileNames();
			
			String storeImage = null;
			
			while(files.hasMoreElements()) { 
				String name = files.nextElement(); 
				String rename = mpReq.getFilesystemName(name);
				
				if(rename != null) { 					
					storeImage = (folderPath + rename);
				} 
			}
			
			Store store = new Store();
			
			store.setStoreName(mpReq.getParameter("storeName"));
			store.setStoreTel(mpReq.getParameter("storeTel"));
			store.setStoreAddress(mpReq.getParameter("storeAddress"));
			store.setStoreIntro(mpReq.getParameter("storeIntro"));
			store.setStoreImage(storeImage);			
			store.setMemberNo(Integer.parseInt(mpReq.getParameter("memberNo")));
			
			int result = new StoreService().insertStore(store);
			
			String message = null;
			String path = null;
			
			if( result > 0 ) {
				req.setAttribute("store", store);
				
				message = "매장이 등록되었습니다.";
				
				List<Store> stores = new StoreService().selectStore(Integer.parseInt(mpReq.getParameter("memberNo")));
				
				req.setAttribute("stores", stores);
				
				path = "/WEB-INF/views/mypage/business/yesstorecheck-pg.jsp";
			} else {
				message = "매장 등록 실패";
				
				path = req.getHeader("referer");
			}
			session.setAttribute("message", message);
			
			req.getRequestDispatcher(path).forward(req, resp);
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
}
