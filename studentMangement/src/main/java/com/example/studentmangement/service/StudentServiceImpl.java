package com.example.studentmangement.service;

import com.example.studentmangement.model.Student;

import java.util.List;

public interface StudentServiceImpl {

    Student addStrudent(Student student);

    List<Student> getStudents();

    Student updateStudent(Student student, Long id);

    Student getStudentById(Long id);

    void deleteStudent(Long id);
}
