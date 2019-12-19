package com.mdg.hibernate.annotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORS")
public class Author extends Person {
//	@Id
//	private String name;

	@ManyToMany(mappedBy = "authors")
	private List<Book> books = new ArrayList<Book>();

	@ManyToOne
	private Publisher publisher;

	public Author(String name, String email) {
		super();
		this.name = name;
		this.emailId = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {

		return "Author [books=" + books + ", publisher=" + publisher.getName() + ", name=" + name + ", emailId="
				+ emailId + "]";
	}

}
