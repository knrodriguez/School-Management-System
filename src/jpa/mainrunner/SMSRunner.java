package jpa.mainrunner;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner {

	public static void main (String[] args) {
		
		Scanner scan = new Scanner(System.in);
		StudentService studentService = new StudentService();
		CourseService courseService = new CourseService();
		System.out.println("Are you a(n)\n"
				+ "1. Student\n "
				+ "2. quit\n"
				+ "Please, enter 1 or 2.");
		Integer option = Integer.parseInt(scan.nextLine());
		try {
			if(option == 1) {
				System.out.println("Enter Your Email:");
				String email = scan.nextLine();
				System.out.println("Enter Your Password:");
				String pass = scan.nextLine();
				if(studentService.validateStudent(email, pass)) {
					Student student = studentService.getStudentByEmail(email);
					List<Course> studentCourses = studentService.getStudentCourses(email);
					printCourses(studentCourses);			
					System.out.println("\n1.Register to Class\n" + 
							"2. Logout");
					option = Integer.parseInt(scan.nextLine());
					if(option == 1) {
						System.out.println("All Courses:");
						List<Course> courses = courseService.getAllCourses();
						printCourses(courses);
						System.out.println("Which Course?");
						option = Integer.parseInt(scan.nextLine());
						boolean registered = false;
						for(Course course : courses) {
							if(option == course.getcId()) {
								for(Course course2 : studentCourses) {
									if(option == course2.getcId()) {
										System.out.println("You are already registered in that course!");
										registered = true;
										break;
									}
								}
								if(!registered) {
									studentService.registerStudentToCourse(email, option);
									studentCourses = studentService.getStudentCourses(email);
									printCourses(studentCourses);
									System.out.println("You have been registered. Goodbye!");
								}	
							}
						}
					} else {
						System.out.println("You have been signed out.");
					}
				} else {
					System.out.println("Invalid Login. Goodbye.");
				}
			} else if (option == 2) {
				System.out.println("Goodbye!");
			} else {
				System.out.println("Invalid Option. Goodbye.");
			}
		} catch(NullPointerException e) {
			System.out.println("Invalid Option. Goodbye.");
		}
	}
	
	public static void printCourses(List<Course> courses) {
		System.out.printf("%-5s %-15s %-30s %n","#","COURSE NAME","INSTRUCTOR NAME");
		for(Course c: courses) {
			System.out.printf("%-5d %-15s %-30s %n",c.getcId(),c.getcName(),c.getcInstructorName());
		}
		
	}
	
}
