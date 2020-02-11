package jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class CourseService implements CourseDAO{

	@Override
	public List<Course> getAllCourses() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMS");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT c FROM Course c");
		List<Course> courseList = query.getResultList();
		return courseList;
	}
	
}
