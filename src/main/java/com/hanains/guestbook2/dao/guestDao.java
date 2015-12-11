package com.hanains.guestbook2.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanains.guestbook2.vo.guestVo;


@Repository
public class guestDao {
	public List<guestVo> getlist(){
		
		List<guestVo> list = new ArrayList<guestVo>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","webdb","webdb");
			stmt = connection.createStatement();
			String sql = "select * from guestbook";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				guestVo vo = new guestVo();
				vo.setNo(rs.getLong(1));  
				vo.setName(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setMessage(rs.getString(4));
				vo.setReg_date(rs.getString(5));
				list.add(vo);
			}
			
		}catch(ClassNotFoundException e){
			System.out.println("드라이버로드 오류 :" +e);
		}catch(SQLException e){
			System.out.println("커넥션 오류:" +e);
		}finally{
			try{
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(connection!=null) connection.close();
			}catch(SQLException e){
				System.out.println("클로즈오류 :" +e);
			}
		}
		return list;
	}	
	
	
	public void insert(guestVo vo){
		Connection connection = null;
		PreparedStatement pstmt = null;		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","webdb","webdb");
			String sql = "insert into guestbook values(GUESTBOOK_SEQ.nextval,?,?,?,SYSDATE)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			pstmt.executeUpdate();
			
					}catch(ClassNotFoundException e){
			System.out.println("드라이버로드 오류 :" +e);
		}catch(SQLException e){
			System.out.println("커넥션 오류:" +e);
		}finally{
			try{				
				if(pstmt!=null) pstmt.close();
				if(connection!=null) connection.close();
			}catch(SQLException e){
				System.out.println("클로즈오류 :" +e);
			}
		}
	}
	
	public void delete(long delnum,String password){
		Connection connection = null;
		PreparedStatement pstmt = null;		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","webdb","webdb");
			String sql = "delete from guestbook where no =? and password=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, delnum);
			pstmt.setString(2, password);
			
			pstmt.executeUpdate();
			
					}catch(ClassNotFoundException e){
			System.out.println("드라이버로드 오류 :" +e);
		}catch(SQLException e){
			System.out.println("커넥션 오류:" +e);
		}finally{
			try{				
				if(pstmt!=null) pstmt.close();
				if(connection!=null) connection.close();
			}catch(SQLException e){
				System.out.println("클로즈오류 :" +e);
			}
		}
	}
}
