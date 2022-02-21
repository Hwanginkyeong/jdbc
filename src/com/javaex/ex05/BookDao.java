package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.ex02.AuthorVo;

public class BookDao {
	
	//필드
	
	//생성자
	public BookDao() {
		
	}
	
	//메소드 일반
	
	public void bookInsert(String title, String pubs, String pubDate, String authorName,
	String authorDesc) {
		// insert문

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공"); // 전화선은 만들어짐

			// 3. SQL문 준비 / 바인딩 / 실행 *****

			// 문자열 만들기 --> ? 주의
			String query = "";
			query += " insert into book ";  
			query += " values(seq_author_id.nextval, ?, ?, ?) ";
			query += " insert into author ";
			query += " values(seq_author_id.nextval, ?, ?) ";

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			//int bookId, String title, 
			//String pubs, String pubDate, 
			//String authorId, String authorName,
			//String authorDesc
			
			// 바인딩
			pstmt.setString(1, title);   
			pstmt.setString(2, pubs); 
			pstmt.setString(3, pubDate); 
			pstmt.setString(4, authorName); 
			pstmt.setString(5, authorDesc); 
			

			// 실행
			int count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println(count + "건이 저장되었습니다.");

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
	
	//책 리스트 가져오기 
	//작가 리스트 가져오기 
	public List<BookVo> bookSelect() {
			List<BookVo> bookList = new ArrayList<BookVo>();
		
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
				System.out.println("접속성공"); // 전화선은 만들어짐
	
				// 3. SQL문 준비 / 바인딩 / 실행 *****
	
				// 문자열 만들기 --> ? 주의
				String query = "";
				query += " select  book.book_id, ";  //앞뒤로 다 띄워버리는 게 편함 
				query += " 		   book.title, "; 
				query += " 		   book.pubs, ";
				query += " 		   book.pub_date, ";
				query += " 		   author.author_id, ";
				query += " 		   author.author_name, ";
				query += " 		   author.author_desc ";
				query += " from book book, author author ";
				query += " where book.author_id = author.author_id ";
				
	
				// 문자열 쿼리문으로 만들기
				pstmt = conn.prepareStatement(query);
	
				// 바인딩
				
				
				//실행 
				rs =  pstmt.executeQuery();
			
				// 4.결과처리
				while(rs.next()) {
					int bookId = rs.getInt("book_id");    
					String title = rs.getString("title");
					String pubs = rs.getString("pubs");
					String pubDate = rs.getString("pubdate");
					int authorId = rs.getInt("author_id");
					String authorName = rs.getString("author_name");
					String authorDesc = rs.getString("author_desc");
	
					BookVo vo= new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
					bookList.add(vo);
				}
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
						rs.close();
				}
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

		return authorList;
	
	}
	
	

}
