package jpa.entitymodels;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Course {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cId;
	
	@Basic
	@Column(name = "name", nullable = false, length = 50)
	private String cName;
	
	@Basic
	@Column(name = "instructor", nullable = false, length = 50)
	private String cInstructorName;
	

	public Course() {
		cId = 0;
		cName = "";
		cInstructorName = "";
	}

	public Course(int id, String name, String instructor) {
		this.cId = id;
		this.cName = name;
		this.cInstructorName = instructor;
	}
	
	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}
	
	@Override
	public String toString() {
		return (this.cId + "\t" + this.cName + "\t" 
				+ this.cInstructorName + "\n"); 
	}
	
}
