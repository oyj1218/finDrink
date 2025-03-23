package com.kh.findrink.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.findrink.board.model.vo.Board;
import com.kh.findrink.board.model.vo.BoardDetail;
import com.kh.findrink.board.model.vo.BoardImage;
import com.kh.findrink.board.model.vo.Pagination;

import static com.kh.findrink.common.JDBCTemplate.*;

public class BoardDAO {

	private Properties prop;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	public BoardDAO() {
		try {
			prop = new Properties();
			String filePath = BoardDAO.class.getResource("/com/kh/findrink/sql/board-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 게시판 이름 조회 DAO
	 * 
	 * @param conn
	 * @param type
	 * @return boardName
	 * @throws Exception
	 */
	/**
	 * SELECT BOARD_NM FROM BOARD_TYPE WHERE BOARD_CD = 3
	 */

	public String selectBoardName(Connection conn, int type) throws Exception {
		String boardName = null;

		try {
			String sql = prop.getProperty("selectBoardName");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardName = rs.getString(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return boardName;
	}

	/**
	 * 특정 게시판 전체 게시글 수 조회 DAO
	 * 
	 * @param conn
	 * @param type
	 * @return listCount
	 * @throws Exception
	 */
	/*
	 * SELECT COUNT(*) FROM BOARD WHERE BOARD_CD = 3 AND BOARD_ST = 'N'
	 */

	public int getListCount(Connection conn, int type) throws Exception {

		int listCount = 0;

		try {

			String sql = prop.getProperty("getListCount");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

//
	/**
	 * 특정 게시판에서 일정한 범위의 목록 조회 DAO
	 * 
	 * @param conn
	 * @param pagination
	 * @param type
	 * @return boardList
	 * @throws Exception
	 */
	/*
	 * SELECT * FROM( SELECT ROWNUM RNUM, A.* FROM( SELECT BOARD_CATEGORY, BOARD_NO,
	 * BOARD_TITLE, MEMBER_NICK, TO_CHAR(CREATE_DT, 'YYYY-MM-DD') AS CREATE_DT,
	 * TO_CHAR(UPDATE_DT, 'YYYY-MM-DD') AS UPDATE_DT, (SELECT COUNT(*) FROM HEART WHERE HEART.BOARD_NO =
	 * BOARD.BOARD_NO) AS HEART_COUNT FROM BOARD JOIN MEMBER USING(MEMBER_NO) JOIN
	 * BOARD_TYPE USING(BOARD_CD) WHERE BOARD_CD = 3 AND BOARD_ST = 'N' ORDER BY
	 * BOARD_NO DESC ) A ) WHERE RNUM BETWEEN ? AND ?
	 */
	public List<Board> selectBoardList(Connection conn, Pagination pagination, int type) throws Exception {

		List<Board> boardList = new ArrayList<Board>();

		try {

			String sql = prop.getProperty("selectBoardList");

			// BETWEEN 구문에 들어갈 범위 계산
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setBoardCategory(rs.getString("BOARD_CATEGORY"));
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setUpdateDate(rs.getString("UPDATE_DT"));
				board.setHeartCount(rs.getInt("HEART_COUNT"));

				boardList.add(board);

			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return boardList;
	}

// -------------------------------------------------

	/**
	 * 게시글 상세 조회 DAO
	 * 
	 * @param conn
	 * @param boardNo
	 * @return detail
	 * @throws Exception
	 */
	/*
	 * SELECT BOARD_PARENT, BOARD_TITLE, BOARD_CONTENT, TO_CHAR(CREATE_DT,
	 * 'YYYY-MM-DD') CREATE_DT, TO_CHAR(UPDATE_DT, 'YYYY-MM-DD') UPDATE_DT, (SELECT
	 * COUNT(*) FROM HEART WHERE BOARD_NO = BOARD.BOARD_NO) AS HEART_COUNT,
	 * MEMBER_NO FROM BOARD JOIN MEMBER USING(MEMBER_NO) JOIN BOARD_TYPE
	 * USING(BOARD_CD) WHERE BOARD_CD = 3 AND BOARD_NO = ? AND BOARD_ST = 'N'
	 */
	public BoardDetail selectBoardDetail(Connection conn, int boardNo) throws Exception {

		BoardDetail detail = null;

		try {
			String sql = prop.getProperty("selectBoardDetail");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				detail = new BoardDetail();

				detail.setBoardCategory(rs.getString("BOARD_CATEGORY"));
				detail.setBoardTitle(rs.getString("BOARD_TITLE"));
				detail.setBoardContent(rs.getString("BOARD_CONTENT"));
				detail.setCreateDate(rs.getString("CREATE_DT"));
				detail.setUpdateDate(rs.getString("UPDATE_DT"));
				detail.setHeartCount(rs.getInt("HEART_COUNT"));
				detail.setMemberNo(rs.getInt("MEMBER_NO"));
				detail.setBoardNo(rs.getInt("BOARD_NO"));
				
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return detail;
	}

	/**
	 * 게시글에 첨부된 이미지 리스트 조회 DAO
	 * 
	 * @param conn
	 * @param boardNo
	 * @return imageList
	 * @throws Exception
	 */
	/*
	 * SELECT * FROM BOARD_IMG WHERE BOARD_NO = ? ORDER BY IMG_LEVEL
	 */
	public List<BoardImage> selectImageList(Connection conn, int boardNo) throws Exception {

		List<BoardImage> imageList = new ArrayList<>();

		try {
			String sql = prop.getProperty("selectImageList");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				BoardImage image = new BoardImage();

				image.setImageNo(rs.getInt(1));
				image.setImageReName(rs.getString(2));
				image.setImageOriginal(rs.getString(3));
				image.setImageLevel(rs.getInt(4));
				image.setBoardNo(rs.getInt(5));

				imageList.add(image);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return imageList;
	}

// --------------------------------

	/**
	 * 다음 게시글 번호 조회 DAO
	 * 
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	/* SELECT SEQ_BOARD_NO.NEXTVAL FROM DUAL; */
	public int nextBoardNo(Connection conn) throws Exception {

		int boardNo = 0;

		try {
			String sql = prop.getProperty("nextBoardNo");

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				boardNo = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(stmt);
		}

		return boardNo;
	}

	/**
	 * 게시글 삽입 DAO
	 * 
	 * @param conn
	 * @param detail
	 * @param boardCode
	 * @return result
	 * @throws Exception
	 */
	/*
	 * INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL, ?, ?, DEFAULT, DEFAULT,
	 * DEFAULT, DEFAULT, DEFAULT, ?, ?, 3)
	 * 
	 */
	public int insertBoard(Connection conn, BoardDetail detail, int boardCode) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("insertBoard");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, detail.getBoardTitle());
			pstmt.setString(2, detail.getBoardContent());
			pstmt.setString(3, detail.getBoardCategory());
			pstmt.setInt(4, detail.getMemberNo());
			pstmt.setInt(5, boardCode);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 게시글 카테고리 삽입 DAO
	 * 
	 * @param conn
	 * @param detail
	 * @return result
	 * @throws Exception
	 *
	 *                   INSERT INTO BOARD_TYPE VALUES(3, ?, ?)
	 * 
	 */

	/**
	 * 게시글 이미지 삽입 DAO
	 * 
	 * @param conn
	 * @param image
	 * @return result
	 * @throws Exception
	 */
	/*
	 * INSERT INTO BOARD_IMG VALUES(SEQ_IMG_NO.NEXTVAL, ?, ?, ?, ?)
	 */
	public int insertBoardImage(Connection conn, BoardImage image) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("insertBoardImage");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, image.getImageReName());
			pstmt.setString(2, image.getImageOriginal());
			pstmt.setInt(3, image.getImageLevel());
			pstmt.setInt(4, image.getBoardNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

// ---------------------
	/**
	 * 게시글 수정 DAO
	 * 
	 * @param conn
	 * @param detail
	 * @return result
	 * @throws Exception
	 */
	/*
	 * UPDATE BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ?, UPDATE_DT = SYSDATE
	 * WHERE BOARD_NO = ?
	 */

	public int updateBoard(Connection conn, BoardDetail detail) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("updateBoard");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, detail.getBoardTitle());
			pstmt.setString(2, detail.getBoardContent());
			pstmt.setInt(3, detail.getBoardNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/*
	 * 게시글 카테고리 DAO
	 * 
	 * @param conn
	 * 
	 * @param detail
	 * 
	 * @return result
	 * 
	 * @throws Exception
	 *
	 * UPDATE BOARD_TYPE A SET BOARD_PARENT = ? WHERE EXISTS (SELECT BOARD_NO FROM
	 * BOARD B WHERE A.BOARD_CD = B.BOARD_CD)
	 */
	public int updateBoardParent(Connection conn, BoardDetail detail) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("insertBoardParent");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, detail.getBoardCategory());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 게시글 이미지 수정 DAO
	 * 
	 * @param conn
	 * @param img
	 * @return result
	 * @throws Exception
	 */
	/*
	 * UPDATE BOARD_IMG SET IMG_RENAME = ?, IMG_ORIGINAL = ? WHERE BOARD_NO = ? AND
	 * IMG_LEVEL = ?
	 */
	public int updateBoardImage(Connection conn, BoardImage img) throws Exception {

		int result = 0;

		try {

			String sql = prop.getProperty("updateBoardImage");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, img.getImageReName());
			pstmt.setString(2, img.getImageOriginal());
			pstmt.setInt(3, img.getBoardNo());
			pstmt.setInt(4, img.getImageLevel());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

// --------------------------------------

	/**
	 * 게시글 이미지 삭제 DAO
	 * 
	 * @param conn
	 * @param deleteList
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	/*
	 * DELETE FROM BOARD_IMG WHERE BOARD_NO = ? AND IMG_LEVEL IN (
	 */
	public int deleteBoardImage(Connection conn, String deleteList, int boardNo) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("deleteBoardImage") + deleteList + ")";
			// "DELETE FROM BOARD_IMG WHERE BOARD_NO = ? AND IMG_LEVEL IN( 0, 1... )

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/*
	 * DELETE FROM BOARD_TYPE WHERE BOARD_CD IN ( SELECT BOARD_CD FROM BOARD WHERE
	 * BOARD_NO = ?)
	 * 
	 */

	public int deleteBoardParent(Connection conn, String deleteList, int boardNo) throws Exception {
		int result = 0;
		try {

			String sql = prop.getProperty("deleteBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 게시글 삭제 DAO
	 * 
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	/*
	 * UPDATE BOARD SET BOARD_ST = 'Y' WHERE BOARD_NO = ?
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception {
		int result = 0;
		try {

			String sql = prop.getProperty("deleteBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertHeart(Connection conn, int boardNo, int memberNo) throws Exception {
		int result = 0;

		try {
			boolean isExist = isExistHeart(boardNo, memberNo);

			String sql = null;

			if (isExist) {
				sql = prop.getProperty("deleteHeart");
			} else {
				sql = prop.getProperty("insertHeart");
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);

			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}

		return result;
	}

	private boolean isExistHeart(int boardNo, int memberNo) throws Exception {
		boolean isExist = false;
		Connection conn = null;

		try {
			String sql = prop.getProperty("findHeart");

			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				isExist = rs.getInt(1) == 1 ? true : false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}

		return isExist;
	}

	public int getBoardNo(Connection conn) throws Exception {
		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("getBoardNo");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				boardNo = rs.getInt(1);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardNo;
	}

	/*
	 * 신고 삽입 DAO INSERT INTO REPORT VALUES(SEQ_REPORT_NO.NEXTVAL, DEFAULT, DEFAULT,
	 * ?, ?, ?);
	 */

	public int insertReport(Connection conn, int boardNo, int memberNo, int rtMemberNo, String reportReason) throws Exception {
		
		int result = 0;

		try {
			String sql = prop.getProperty("insertReport");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reportReason);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, rtMemberNo);
			pstmt.setInt(4, boardNo);		
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getListCountMember(Connection conn, int memberNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("getListCountMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				result = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public List<Board> selectBoardWithMember(Connection conn, Pagination pagination, int memberNo) throws Exception {
		List<Board> boardList = new ArrayList<Board>();

		try {

			String sql = prop.getProperty("selectBoardListMember");

			// BETWEEN 구문에 들어갈 범위 계산
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setBoardCategory(rs.getString("BOARD_CATEGORY"));
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setUpdateDate(rs.getString("UPDATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));

				boardList.add(board);

			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return boardList;
	}
}
