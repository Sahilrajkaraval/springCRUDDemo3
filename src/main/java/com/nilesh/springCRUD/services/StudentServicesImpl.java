package com.nilesh.springCRUD.services;

import com.nilesh.springCRUD.model.Student;
import com.nilesh.springCRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
 @Service
public class StudentServicesImpl implements StudentServices{

    @Autowired
    private StudentRepository repo;

     @Override
     public List<Student> getByKeyword(String keyword) {
         return repo.findByKeyword(keyword);
     }

     @Override
    public List<Student> listAll() {
         List<Student> list = repo.findAll();
         return list;
    }

    @Override
    public void save(Student student) {
        repo.save(student);
    }

    @Override
    public Student get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repo.findAll(pageable);
    }


 }
