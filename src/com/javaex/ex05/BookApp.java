package com.javaex.ex05;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		List<BookVo> list;
		BookDao bookDao = new BookDao();
		
		//책 등록 
		bookDao.bookInsert("우리들의 일그러진 영웅", "다림", "98/02/22", "김문열", "경북 영양");
		
		
		//출력
		//list = bookDao.bookselct();{}
			
		
		

	}

}
