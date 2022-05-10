package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
public class HomeController {
	
	@Autowired
	ProjectService proSvc;
	
	@Autowired
	EmployeeService empSvc;
	
	@GetMapping("/")
	public String displayHome(Model model)
	{
		//to display the list of the projects in the home page
		List<Project> projects=proSvc.getAll();
		model.addAttribute("projects", projects);
		
		//to display the list of employees in the home page
		/*
		 * List<Employee> employees = empRepo.findAll(); model.addAttribute("employees",
		 * employees);
		 */
		
		//to display the result from the custom Query
		List<EmployeeProject> employyeProject = empSvc.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employyeProject);
		
		return "main/home";
	}

}
