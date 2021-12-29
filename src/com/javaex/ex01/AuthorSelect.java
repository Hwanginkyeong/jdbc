package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		//작가 데이터 가져오기 
		
		// 0. import java.sql.*;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");  //전화선은 만들어짐 
			
			// 3. SQL문 준비 / 바인딩 / 실행 *****
			
			//문자열 만들기 --> ? 주의 
			String query = ""; 
			//query = query +"문자열" 
			query += " select   author_id, ";  //앞뒤로 다 띄워버리는 게 편함 
			query += " 			author_name, "; 
			query += " 			author_desc ";
			query += " from author ";
			//System.out.println(query);
			
	
			//문자열 쿼리문으로 만들기 
			pstmt = conn.prepareStatement(query);
			
			//바인딩  --> 생략 ? 표 없음 
			
			//실행
			rs = pstmt.executeQuery(); //쿼리문 실행 
			
			
			// 4.결과처리
			while(rs.next()) {
				/*
				int authorID = rs.getInt("author_id");  //컬럼명의 별명을 지으면 여기에도 똑같이 넣어줘야 함 
				String authorName = rs.getString("author_name");  //변수이름 authorName //컬럼명 author_name
				String authorDesc = rs.getString("author_desc"); 
				*/
				
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);   //오버로딩 공부하기 
				String authorDesc = rs.getString(3);
				
				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);   //add 통해서 알아서 늘어남 
				 
				
				//System.out.println(authorId + ", "+ authorName + ", "+authorDesc);
			
			}
			
			//출력 
			for(int i=0; i<authorList.size(); i++) {
				AuthorVo authorVo = authorList.get(i);
				System.out.println(authorVo.getAuthorId()+", "+ authorVo.getAuthorName()+", "+ authorVo.getAuthorDesc());
			}
			
			//첫번째 작가 이름만 다시 출력 
			AuthorVo authorVo = authorList.get(0);
			System.out.println(authorVo.getAuthorName());
			
			
		} catch (ClassNotFoundException e) { 
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) { 
			System.out.println("error:" + e);
		} finally {
		

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close(); }
				if (pstmt != null) {
				pstmt.close(); 
				}
				if (conn != null) {
				conn.close(); 
				}
			} catch (SQLException e) {
				System.out.println("error:" + e); 
			}
			
		}
		
		
		
		
		
		

	}

}
