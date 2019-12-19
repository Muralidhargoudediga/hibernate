package com.mdg.hibernate.annotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Publisher extends Person {
	private String taxId;
	
	@OneToMany(mappedBy="publisher")
	private List<Author> authors = new ArrayList<Author>();

	public Publisher(String name, String email, String taxId) {
		super();
		this.taxId = taxId;
		this.name = name;
		this.emailId = email;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Publisher [taxId=" + taxId + ", authors=" + authors + ", name=" + name + ", emailId=" + emailId + "]";
	}

}
