package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Student {
	
	@Id
	@Column(name = "email", nullable = false, length = 50)
	private String sEmail;
	
	@Basic
	@Column(name = "name", nullable = false, length = 50)
	private String sName;
	
	@Basic
	@Column(name = "password", nullable = false, length = 50)
	private String sPass;

	@OneToMany(targetEntity = Course.class)
	private List sCourses;

	public Student() {
		sEmail = "";
		sName = "";
		sPass = "";
		sCourses = new ArrayList<>();
	}
	
	public Student(String email, String name, String pass, List courses) {
		this.sEmail = email;
		this.sName = name;
		this.sPass = pass;
		this.sCourses = courses;
	}
	
	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public List<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}
	
}
