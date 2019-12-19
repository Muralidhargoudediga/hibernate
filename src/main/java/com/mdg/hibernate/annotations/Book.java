package com.mdg.hibernate.annotations;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BOOKS")
public class Book {

	@Id
	// @GeneratedValue(strategy = GenerationType.TABLE)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String isbn;
	private String name;

	@ManyToMany
	private List<Author> authors = new ArrayList<Author>();
	// @Temporal(TemporalType.TIME)
	private Date publichsedDate;

	// Book(){}

	public Book(String isdn, String name) {
		super();
		this.isbn = isdn;
		this.name = name;
		this.publichsedDate = new Date();
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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Date getPublichsedDate() {
		return publichsedDate;
	}

	public void setPublichsedDate(Date publichsedDate) {
		this.publichsedDate = publichsedDate;
	}

	@Override
	public String toString() {
		String authors = this.authors.stream().map(Author::getName).collect(Collectors.joining(","));
		return MessageFormat.format("{0} by {1} (ISBN : {2}), published {3}", this.name, authors, this.isbn,
				this.publichsedDate);
	}

}
