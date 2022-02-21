package com.javaex.ex01;

public class BookVo {
	
	//필드 
	private int bookId;
	private String title;
	private String pubs;
	private String pub_date;
	private int authorId;
	private String authorName;
	private String authorDesc;
	
	
	//생성자 
	public BookVo() {
		
	}
	
	public BookVo(int bookId, String title, String pubs, String pub_date, int authorId) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.authorId = authorId;
	}

	/*
	public BookVo(int bookId, String title, String pubs, String pub_date, int authorId, String authorName,
			String authorDesc) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	*/
	
	//메소드 g/s 

	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPubs() {
		return pubs;
	}


	public void setPubs(String pubs) {
		this.pubs = pubs;
	}


	public String getPub_date() {
		return pub_date;
	}


	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}


	public int getAuthorId() {
		return authorId;
	}


	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}


	public String getAuthorName() {
		return authorName;
	}


	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}


	public String getAuthorDesc() {
		return authorDesc;
	}


	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}

	
	//메소드 
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pub_date=" + pub_date
				+ ", authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	}
	
	
	
	
	
	
	
	


}
