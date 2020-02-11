package jpa.mainrunner;

import java.util.List;
import java.util.Scanner;

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
		int option = Integer.parseInt(scan.nextLine());
		if(option == 1) {
			System.out.println("Enter Your Email:");
			String email = scan.nextLine();
			System.out.println("Enter Your Password:");
			String pass = scan.nextLine();
			if(studentService.validateStudent(email, pass)) {
				Student student = studentService.getStudentByEmail(email);
				List<Course> studentCourses = studentService.getStudentCourses(email);
				System.out.println("#\tCOURSE NAME\tINSTRUCTOR NAME");
				studentCourses.forEach(System.out::println);
				System.out.println("1.Register to Class\n" + 
						"2. Logout");
				option = Integer.parseInt(scan.nextLine());
				if(option == 1) {
					System.out.println("All Courses:\n" + 
							"ID\tCOURSE NAME\tINSTRUCTOR NAME");
					List<Course> courses = courseService.getAllCourses();
					courses.forEach(System.out::println);
					System.out.println("Which Course?");
					option = Integer.parseInt(scan.nextLine());
					for(Course course : courses) {
						if(option == course.getcId()) {
							for(Course sCourse : studentCourses) {
								if(option == sCourse.getcId()) {
									System.out.println("You are already registered in that course!");
								}
							}
							studentService.registerStudentToCourse(email, option);
							studentCourses = studentService.getStudentCourses(email);
							System.out.println("#\tCOURSE NAME\tINSTRUCTOR NAME");
							studentCourses.forEach(System.out::println);
							System.out.println("You have been registered. Goodbye!");
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
	}
	
}
