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

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		final Book book = new Book("93939398948 ", "Java 8");
		Arrays.stream("Raoul-Gabriel Urma,Mario Fusco,Alan Mycroft".split(",")).map(name -> new Author(name))
				.forEach(author -> {
					author.getBooks().add(book);
					book.getAuthors().add(author);
					session.save(author);
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
	}
}
