package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAll {

	public static void main(String[] args) {
		
		// 책 데이터 가져오기 join 문 써서 책 작가 전부 가져오기 

		//책 데이터 가져오기 

		//insert문 
		
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
			query += " select  book.book_id, ";  //앞뒤로 다 띄워버리는 게 편함 
			query += " 		   book.title, "; 
			query += " 		   book.pubs, ";
			query += " 		   book.pub_date, ";
			query += " 		   author.author_id, ";
			query += " 		   author.author_name, ";
			query += " 		   author.author_desc ";
			query += " from book book, author author ";
			query += " where book.author_id = author.author_id ";
			
			
			System.out.println(query);
			
	
			//문자열 쿼리문으로 만들기 
			pstmt = conn.prepareStatement(query);
			
			//바인딩  --> 생략 ? 표 없음 
			
			//실행
			rs = pstmt.executeQuery(); //쿼리문 실행 
			
			
			// 4.결과처리
			while(rs.next()) {
				
				int bookId = rs.getInt(1);
				String title = rs.getString(2);   //오버로딩 공부하기 
				String pubs = rs.getString(3);
				String pub_date = rs.getString(4);
				int authorId = rs.getInt(5);
				String authorName = rs.getString(6);
				String authorDesc = rs.getString(7);
				
				System.out.println(bookId + ", "+ title + ", "+ pubs + ", "
									+ pub_date +", "+ authorId +", "+authorName+", "+ authorDesc);
			
			}
			
			//List 
			
			
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
