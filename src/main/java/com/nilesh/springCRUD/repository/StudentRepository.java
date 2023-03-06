package com.nilesh.springCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nilesh.springCRUD.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select * from STUDENT p where p.STUDENT_NAME like %:keyword%", nativeQuery = true)
    List<Student> findByKeyword(@Param("keyword") String keyword);

    List<Student> findAll();
}
