package jpa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO{

	private static StudentService studentService = new StudentService();
	
	@Override
	public List<Student> getAllStudents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMS");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT s FROM Student s");
		List<Student> studentList = query.getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return studentList;
	}

	@Override
	public Student getStudentByEmail(String sEmail) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMS");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT s FROM Student s WHERE s.sEmail = :sEmail");
		query.setParameter("sEmail", sEmail);
		Student student = null;
		student = (Student) query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return student;
	}

	@Override
	public boolean validateStudent(String sEmail, String sPassword) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMS");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT s FROM Student s WHERE s.sEmail = :sEmail AND s.sPass = :sPassword");
		query.setParameter("sEmail", sEmail).setParameter("sPassword", sPassword);
		Student student = null;
		try{
			student = (Student) query.getSingleResult();
		} catch(NoResultException e) {}
		em.getTransaction().commit();
		em.close();
		emf.close();
		if(student == null) return false;
		else return true;
	}

	@Override
	public void registerStudentToCourse(String sEmail, int cId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMS");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Student student = studentService.getStudentByEmail(sEmail);
		List<Course> sCourses = student.getsCourses();		
		for(Course c: sCourses) {
			if(c.getcId() == cId) {
				System.out.println("Student is already registered to course " + cId);
				return;
			}
		}		
		Course newCourse = em.find(Course.class, cId);
		sCourses.add(newCourse);	
		student.setsCourses(sCourses);
		Query query = em.createQuery("DELETE FROM Student s WHERE s.sEmail = :sEmail");
		query.setParameter("sEmail", sEmail);
		int numDeleted = query.executeUpdate();
		if(numDeleted == 1) {
			em.persist(student);
			em.getTransaction().commit();
		}
		em.close();
		emf.close();
	}

	@Override
	public List<Course> getStudentCourses(String sEmail) {
		Student student = studentService.getStudentByEmail(sEmail);
		List<Course> sCourses = student.getsCourses();
		return sCourses;
	}

	
}
