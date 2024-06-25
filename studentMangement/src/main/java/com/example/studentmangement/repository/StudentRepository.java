package com.example.studentmangement.repository;

import com.example.studentmangement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
   Optional<Student> findByEmail(String email);
}
