package com.sreeny.todo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sreeny.todo.model.Todo;

@Service("todoDao")
public class TodoDAOImpl implements TodoDAO  {

	protected static final Logger log = LoggerFactory.getLogger(TodoDAOImpl.class);

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public List<Todo> getAllTodsByUserId(String userId) {
		userId = "Sreeny";
		Session session = sessionFactory.getCurrentSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Todo> todoQuery = criteriaBuilder.createQuery(Todo.class);
		Root<Todo> root = todoQuery.from(Todo.class);

		todoQuery.where(criteriaBuilder.equal(root.get("userId"), userId));

		// String hqlQuery = "SELECT t FROM Todo t where userId = :userId";
		Query query = session.createQuery(todoQuery);
		// query.setParameter("userId", "Sreeny");
		log.info("Fetching Tods for the user {}", userId);
		return query.list();
	}

	@Override
	public Todo saveTodo(Todo todo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(todo);
		return todo;
	}
	
	@Override
	public Todo get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Todo todo = session.get(Todo.class, id);
		return todo;
	}
	 

}
