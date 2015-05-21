package com.sds.icto.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.domain.MemberVo;
import com.sds.icto.mysite.exception.MemberDaoException;

@Repository
public class MemberDao {
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection conn = DriverManager.getConnection(dbURL, "webdb", "webdb");
		return conn;
	}
	
	public void update(MemberVo vo){
		try {
			Connection conn = getConnection();
			String sql =  "update member set name=?, password=?, gender=? where email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getGender());
			stmt.setString(4, vo.getEmail());
			stmt.executeUpdate();
			if (stmt != null)stmt.close(); 
			if (conn != null)conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	public void insert(MemberVo vo){
		try {
			Connection conn = getConnection();
			String sql =  " insert into member values(member_no_seq.nextval, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getEmail());
			stmt.setString(3, vo.getPassword());
			stmt.setString(4, vo.getGender());
			stmt.executeUpdate();
			if (stmt != null)stmt.close(); 
			if (conn != null)conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			throw new MemberDaoException(e.getMessage());
		} 
	}
	public MemberVo getMember(MemberVo vo){
		MemberVo member = null;
		try {
			Connection conn = getConnection();
			String sql =  "  select * from member where email = ? and password = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getEmail());
			stmt.setString(2, vo.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				member = new MemberVo(); 
				member.setNo(rs.getLong(1));
				member.setName(rs.getString(2));
				member.setEmail(rs.getString(3));
				//member.setPassword(rs.getString(4)); => 보안때문에
				member.setGender(rs.getString(5));
			}
			if (rs != null)stmt.close(); 
			if (stmt != null)stmt.close(); 
			if (conn != null)conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			throw new MemberDaoException(e.getMessage());
		} 
		return member;
	}
}
