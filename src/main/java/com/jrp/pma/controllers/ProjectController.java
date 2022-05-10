package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proSvc;
	
	@Autowired
	EmployeeService empSvc;

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		model.addAttribute("project",aProject);
		
		//to display the list of employees to new project screen
		List<Employee> employees = empSvc.getAll();
		model.addAttribute("allEmployees", employees);
		
		
		return "projects/new-project";
	}
	
	
	@PostMapping("/save")
	public String createProject(Model model,Project project,@RequestParam List<Long> employees) {
	    
		
		//to create the resource of type project in db.
		proSvc.save(project);
		
		
		//to update the employees with the Project_id
		/*
		 * Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
		 * for(Employee emp:chosenEmployees) { emp.settheProject(project);
		 * empRepo.save(emp); }
		 */
		
		return "redirect:/projects/new";
	
	}
	
	//displays the list of Projects
	@GetMapping
	public String displayProjects(Model model) {
		
		List<Project> projects = proSvc.getAll();
		model.addAttribute("projects",projects);
		return "projects/list-projects";
	}
	
}
