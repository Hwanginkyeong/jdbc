package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsert2 {

	public static void main(String[] args) {

		//insert문 

		// 0. import java.sql.*;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		
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
			query += " insert into book ";  //앞뒤로 다 띄워버리는 게 편함 
			query += " values(seq_author_id.nextval, ?, ?, ?, ?) "; //앞뒤로 다 띄워버리는 게 편함 
			System.out.println(query);
			
			//문자열 쿼리문으로 만들기 
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, "우리들의 일그러진 영웅");   
			pstmt.setString(2, "다림"); 
			pstmt.setString(3, "1998-02-22"); 
			pstmt.setInt(4, 1); 
			
			
			/*
			insert into book
			values(1, '우리들의 일그러진 영웅', '다림', '1998-02-22', '1');

			insert into book
			values(2, '삼국지', '민음사', '2002-03-01', '1');

			insert into book
			values(3, '토지', '마로니에북스', '2012-08-15', '2');

			insert into book
			values(4, '유시민의 글쓰기 특강', '생각의 길', '2015-04-01', '3');

			insert into book
			values(5, '패션왕', '중앙북스(books)', '2012-02-22', '4');

			insert into book
			values(6, '순정만화', '재미주의', '2011-08-03', '5');

			insert into book
			values(7, '오직두사람', '문학동네', '2017-05-04', '6');

			insert into book
			values(8, '26년', '재미주의 ', '2012-02-04', '5');
			 */
			
			
			//실행
			int count = pstmt.executeUpdate(); //쿼리문 실행 
			
			
			// 4.결과처리
			System.out.println(count + "건이 저장되었습니다." );
			
		} catch (ClassNotFoundException e) { 
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) { 
			System.out.println("error:" + e);
		} finally {
		

		// 5. 자원정리
			try {
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
