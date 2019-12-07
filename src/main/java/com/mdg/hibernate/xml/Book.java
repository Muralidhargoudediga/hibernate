package com.mdg.hibernate.xml;

public class Book {

	private String isbn;
	private String name;
	private String author;
	
	Book(){}

	public Book(String isdn, String name, String author) {
		super();
		this.isbn = isdn;
		this.name = name;
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isdn) {
		this.isbn = isdn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", name=" + name + ", author=" + author + "]";
	}

}
