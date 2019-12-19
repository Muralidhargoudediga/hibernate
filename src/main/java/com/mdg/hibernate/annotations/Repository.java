package com.mdg.hibernate.annotations;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class Repository {
	public static List<Author> getAuthors(Session session) {
		return session.createQuery("from Author").list();
	}

	public static List<Author> getAuthorsByCriteria(Session session) {
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Author> cq = criteriaBuilder.createQuery(Author.class);
		Root<Author> root = cq.from(Author.class);
		Expression<String> p = criteriaBuilder.parameter(String.class);
		cq.select(root).where(criteriaBuilder.like(root.get("name"), "M%"));
		TypedQuery<Author> query = session.createQuery(cq);
		// query.setParameter("p", "M%");
		List<Author> authors = query.getResultList();

		return authors;
	}

	public static int getNativeQuery(Session session) {
		return ((BigInteger) session.createSQLQuery("select count(*) from AUTHORS").uniqueResult()).intValue();
	}

	public static void batchInsert(Session session) {
		double start = System.currentTimeMillis();
		session.beginTransaction();
		for (int i = 0; i < 10; i++) {
			Book b = new Book("987987987698798", "Hibernate");
			session.save(b);
			if(i % 10 == 0) {
				session.flush();
				session.clear();
			}
		}

		session.getTransaction().commit();
		session.close();

		System.out.println("Time taken : " + (System.currentTimeMillis() - start) / 1000);
	}
}
