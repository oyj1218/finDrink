package com.kh.findrink.store.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.findrink.store.model.vo.Store;
import static com.kh.findrink.common.JDBCTemplate.*;

public class StoreDAO {

	private Properties prop;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public StoreDAO() {
		try {
			prop = new Properties();
			String filePath = StoreDAO.class.getResource("/com/kh/findrink/sql/store-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	public int insertStore(Connection conn, Store store) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("insertStore");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, store.getStoreName());
			pstmt.setString(2, store.getStoreAddress());
			pstmt.setString(3, store.getStoreTel());
			pstmt.setString(4, store.getStoreIntro());
			pstmt.setString(5, store.getStoreImage());
			pstmt.setInt(6, store.getMemberNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Store> selectStore(Connection conn, int memberNo) throws Exception {
		List<Store> stores = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectStore");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				Store store = new Store();
				
				store.setStoreName(rs.getString("STORE_NM"));
				store.setStoreIntro(rs.getString("STORE_INT"));
				store.setStoreAddress(rs.getString("STORE_ADDRESS"));
				store.setStoreImage(rs.getString("STORE_IMG"));
				store.setStoreNo(rs.getInt("STORE_NO"));
				store.setStoreTel(rs.getString("STORE_TEL"));
				store.setMemberNo(rs.getInt("MEMBER_NO"));
				
				stores.add(store);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return stores;
	}
}
