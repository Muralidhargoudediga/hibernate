package com.mdg.hibernate;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.mdg.hibernate.annotations.Author;
import com.mdg.hibernate.annotations.Book;
import com.mdg.hibernate.annotations.Publisher;
import com.mdg.hibernate.annotations.Repository;

/**
 * Hello world!
 *
 */
public class App {

	static int c = 1;

	public static void main(String[] args) {

		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		final Book book = new Book("93939398948 ", "Java 8");
		Arrays.stream("Raoul-Gabriel Urma,Mario Fusco,Alan Mycroft".split(","))
				.map(name -> new Author(name, name.replace(" ", "") + "@gmail.com")).forEach(author -> {

					Publisher publisher = new Publisher("Oriel" + c++, "oriel@gmail.com", "12313213324");
					author.setPublisher(publisher);
					publisher.getAuthors().add(author);
					author.getBooks().add(book);
					book.getAuthors().add(author);
					session.save(author);
					session.save(publisher);
				});
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();

		// Deprecated
		// List<Book> books = session.createCriteria(Book.class).list();

		// JPA Criteria Logic
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Book> cq = criteriaBuilder.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);

		cq.select(root);
		Query<Book> query = session.createQuery(cq);
		List<Book> books = query.getResultList();

		for (Book b : books) {
			System.out.println(b);
		}
		
		//List<Author> authors = Repository.getAuthors(session);
		
		List<Author> authors = Repository.getAuthorsByCriteria(session);
		
		for(Author author : authors) {
			System.out.println(author);
		}
		
		System.out.println(Repository.getNativeQuery(session));
		
		Repository.batchInsert(session);
	}
}
