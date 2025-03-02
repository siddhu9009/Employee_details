package com.example.EMPOLYEE_PROJECT;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	 private final EmployeeService service;

	    public EmployeeController(EmployeeService service) {
	        this.service = service;
	    }

	    @GetMapping
	    public String listEmployees(Model model) {
	        model.addAttribute("employees", service.getAllEmployees());
	        return "employee-list";
	    }

	    @GetMapping("/new")
	    public String newEmployeeForm(Model model) {
	        model.addAttribute("employee", new Employee());
	        return "employee-form";
	    }

	    @PostMapping
	    public String saveEmployee(@ModelAttribute Employee employee) {
	        service.saveEmployee(employee);
	        return "redirect:/employees";
	    }

	    @GetMapping("/edit/{id}")
	    public String editEmployeeForm(@PathVariable Long id, Model model) {
	        model.addAttribute("employee", service.getEmployeeById(id));
	        return "employee-form";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteEmployee(@PathVariable Long id) {
	        service.deleteEmployee(id);
	        return "redirect:/employees";
	    }

}
