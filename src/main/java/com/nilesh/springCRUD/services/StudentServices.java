package com.nilesh.springCRUD.services;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.nilesh.springCRUD.model.Student;
import com.nilesh.springCRUD.repository.StudentRepository;

@Service
public interface StudentServices {

	public List<Student> getByKeyword(String keyword);
	List<Student> listAll();

	void save(Student student);

	Student get(Long id);

	void delete(Long id);

	Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);



}
