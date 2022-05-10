package com.jrp.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {

	@Id
	@SequenceGenerator(name="employee_sq",sequenceName="employee_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="employee_sq")
	private long employeeId;
	private String firstName;
	private String lastName;
	private String email;
	
	/*
	 * @ManyToOne(cascade=
	 * {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST
	 * }, fetch =FetchType.LAZY)
	 * 
	 * @JoinColumn(name="projectId")
	 *  private Project theProject;
	 * 
	 * public Project getTheProject() 
	 * { return theProject; }
	 * 
	 * public void setTheProject(Project theProject)
	 *  { this.theProject = theProject;
	 * }
	 */
	
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},
				fetch = FetchType.LAZY)
	@JoinTable(name="project_employee",
	   joinColumns = @JoinColumn(name="employee_id"),
	   inverseJoinColumns=@JoinColumn(name="project_id"))
	@JsonIgnore
	private List<Project> projects;
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Employee() {
		
	}
		  
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	
	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
