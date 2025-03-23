package com.kh.findrink.store.model.controller;

import java.sql.Connection;
import java.util.List;

import com.kh.findrink.store.model.dao.StoreDAO;
import com.kh.findrink.store.model.vo.Store;

import static com.kh.findrink.common.JDBCTemplate.*;

public class StoreService {

	private StoreDAO dao = new StoreDAO();

	public int insertStore(Store store) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertStore(conn, store);
		
		if( result > 0 )	commit(conn);
		else				rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<Store> selectStore(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		List<Store> stores = dao.selectStore(conn, memberNo);
		
		close(conn);
		
		return stores;
	}
}
