package com.javaex.ex05;

public class BookVo {
	
	//필드
	private String title;
	private String pubs;
	private String pubDate;
	private String authorName;
	private String authorDesc;
	
	
	public BookVo(int bookId, String title, String pubs, String pubDate, int authorId, String authorName,
			String authorDesc) {
	
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
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


	public String getPubDate() {
		return pubDate;
	}


	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
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





	@Override
	public String toString() {
		return "BookVo [title=" + title + ", pubs=" + pubs + ", pubDate=" + pubDate + ", authorName=" + authorName
				+ ", authorDesc=" + authorDesc + "]";
	}



	
	
	
	
	
	

}
