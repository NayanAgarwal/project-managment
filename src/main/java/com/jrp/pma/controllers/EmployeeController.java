package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empSvc;
	
	
	@GetMapping("/new")
	public String displayEmployee(Model model)
	{
		Employee anEmployee = new Employee();
		model.addAttribute("employee",anEmployee);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String saveEmployee(Model model,Employee employee)
	{
		//to create the resource of type employee in db.
		empSvc.save(employee);
		return "redirect:/employees/new";
	}
	
	@GetMapping
	public String listEmployees(Model model) {
		//to display the list of employees in the home page
		List<Employee> employees = empSvc.getAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}

}
