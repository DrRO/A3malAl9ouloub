package com.pdf.kouloub.externals;

public class Book {
	
	private int id;
	private String cover;
	private String pdfFile;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(int id, String cover, String pdfFile){
		this.id = id;
		this.cover = cover;
		this.pdfFile = pdfFile;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getPdfFile() {
		return pdfFile;
	}
	public void setPdfFile(String pdfFile) {
		this.pdfFile = pdfFile;
	}
	
	

}
