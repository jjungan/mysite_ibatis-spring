package com.sds.icto.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.domain.BoardVo;

@Repository
public class BoardDao {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "webdb", "webdb");
		return conn;
	}
	
	public void insertBoard(BoardVo vo){
		try {
			conn = getConnection();
			String sql = "insert into Board values(board_no_seq.nextval,?,?,?,?,0,sysdate)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setLong(3, vo.getMemberNo());
			stmt.setString(4, vo.getMemberName());
			stmt.executeUpdate();
			
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteBoard(BoardVo vo){
		try {
			conn = getConnection();
			String sql = "delete from Board where no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, vo.getNo());
			stmt.executeUpdate();
			
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updateBoard(BoardVo vo){
		try {
			conn = getConnection();
			String sql = "update Board set title=?, content=? where no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setLong(3, vo.getNo());
			stmt.executeUpdate();
			
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updateBoardViewCnt(BoardVo vo){
		try {
			conn = getConnection();
			String sql = "update Board set view_cnt= view_cnt+1 where no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, vo.getNo());
			stmt.executeUpdate();
			
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public BoardVo selectBoardByNo(Long no){
		BoardVo vo = null;
		try {
			conn = getConnection();
			String sql = "select * from Board where no = ? order by no desc";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, no);
			rs = stmt.executeQuery();
			vo = new BoardVo();
			while(rs.next()){
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setMemberNo(rs.getLong(4));
				vo.setMemberName(rs.getString(5));
				vo.setViewCnt(rs.getLong(6));
				vo.setRegDate(rs.getDate(7));
			}
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
	public List<BoardVo> selectBoard(){
		List<BoardVo> list = null;
		try {
			conn = getConnection();
			String sql = "select * from Board order by no desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			list = new ArrayList<BoardVo>();
			while(rs.next()){
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setMemberNo(rs.getLong(4));
				vo.setMemberName(rs.getString(5));
				vo.setViewCnt(rs.getLong(6));
				vo.setRegDate(rs.getDate(7));
				list.add(vo);
			}
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		}  catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<BoardVo> selectBoardByKeyword(String word){
		List<BoardVo> list = null;
		try {
			conn = getConnection();
			String sql = "select * from Board where title like ? order by no desc";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			rs = stmt.executeQuery();
			list = new ArrayList<BoardVo>();
			while(rs.next()){
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setMemberNo(rs.getLong(4));
				vo.setMemberName(rs.getString(5));
				vo.setViewCnt(rs.getLong(6));
				vo.setRegDate(rs.getDate(7));
				list.add(vo);
			}
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
