package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelect {

	public static void main(String[] args) {
		
		
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		//책 데이터 가져오기 
		
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
			query += " select  book_id, ";  //앞뒤로 다 띄워버리는 게 편함 
			query += " 		   title, "; 
			query += " 		   pubs, ";
			query += " 		   pub_date, ";
			query += " 		   author_id ";
			query += " from book ";
			
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
				
				BookVo vo = new BookVo(bookId, title, pubs, pub_date, authorId);
				bookList.add(vo); 
				
				//System.out.println(bookId + ", "+ title + ", "+ pubs + ", "
						//+ pub_date +", "+ authorId);
			
			}
			
			//출력 
			for(int i=0; i<bookList.size(); i++) {
				BookVo bookVo = bookList.get(i);
				System.out.println(bookVo.getBookId()+", "+ bookVo.getTitle()+", "+ bookVo.getPubs()+", "+
									bookVo.getPub_date()+", "+bookVo.getAuthorId());
			}
			
			//첫번째 발행일 다시 출력 
			BookVo bookVo = bookList.get(0);
			System.out.println(bookVo.getPub_date());
			
			
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
