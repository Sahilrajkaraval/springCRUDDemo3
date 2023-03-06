package com.nilesh.springCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.nilesh.springCRUD.model.Student;
import com.nilesh.springCRUD.services.StudentServices;

@Controller
public class AppController {

	@Autowired
	StudentServices service;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "studentId", "asc", model);
	}

	@RequestMapping(path = {"/","/search"})
	public String home(Student student, Model model, String keyword) {
		if(keyword!=null) {
			List<Student> list = service.getByKeyword(keyword);
			model.addAttribute("listStudent",list);
		}else {
			List<Student> list = service.listAll();
			model.addAttribute("listStudent", list);}
		return "index";
	}


	@RequestMapping("/new")
	public String newStudentPage(Model model) {
		Student student=new Student();
		model.addAttribute(student);
		return "new_student";
	}
	
	@RequestMapping(value = "/save", method =RequestMethod.POST)
	public String saveStudent(@ModelAttribute("student") Student student ) {
		service.save(student);
		return "redirect:/";
	}
	@RequestMapping("/edit/{studentId}")
	public ModelAndView showEditStudentPage(@PathVariable (name="studentId") Long studentId) {
		ModelAndView mav=new ModelAndView("edit_student");
		Student student=service.get(studentId);
		mav.addObject("student",student);
		return mav;
	}
	
	@RequestMapping("/delete/{studentId}")
	public String deleteStudentPage(@PathVariable (name="studentId") Long studentId) {
		service.delete(studentId);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir,
								Model model) {
		int pageSize = 5;

		Page<Student> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Student> listStudent = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listStudent", listStudent);
		return "index";
	}
	
}
