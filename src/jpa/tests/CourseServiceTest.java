package jpa.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import jpa.entitymodels.Course;
import jpa.service.CourseService;

@RunWith(Parameterized.class)
class CourseServiceTest {

	List<Course> expectedCourseList;
	CourseService courseService;

	@BeforeEach
	void setupTest() {
		expectedCourseList = new ArrayList<>();
		courseService = new CourseService();
	}

	@Parameterized.Parameters
	public static List<Course> params() {
		List<Course> courseList = new ArrayList<>();
		courseList.add(new Course(1, "English", "Anderea Scamaden"));
		courseList.add(new Course(2, "Mathematics", "Eustace Niemetz"));
		courseList.add(new Course(3, "Anatomy", "Reynolds Pastor"));
		courseList.add(new Course(4, "Organic Chemistry", "Odessa Belcher"));
		courseList.add(new Course(5, "Physics", "Dani Swallow"));
		courseList.add(new Course(6, "Digital Logic", "Glenden Reilingen"));
		courseList.add(new Course(7, "Object Oriented Programming","Giselle Ardy"));
		courseList.add(new Course(8, "Data Structures", "Carolan Stoller"));
		courseList.add(new Course(9, "Politics", "Carmita De Maine"));
		courseList.add(new Course(10, "Art", "Kingsly Doxsey"));
		return courseList;
	}

	@Test
	void test_getAllCourses() {
		List<Course> actualCourseList = courseService.getAllCourses();
		expectedCourseList = (List<Course>) CourseServiceTest.params();
		for(Course cA : actualCourseList) {
			for(Course cE: expectedCourseList) {
				if(cA.getcId() == cE.getcId()) {
					assertTrue(cA.equals(cE));
				}
			}
		}
	}	
}
