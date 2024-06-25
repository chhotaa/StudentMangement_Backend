package com.example.studentmangement.service;

import com.example.studentmangement.exception.StudentAlreadyExistsException;
import com.example.studentmangement.exception.StudentNotFoundException;
import com.example.studentmangement.model.Student;
import com.example.studentmangement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements StudentServiceImpl{

    private final StudentRepository studentRepository;
    @Override
    public Student addStrudent(Student student) {

        if(studentAlreadyExists(student.getEmail())){
             throw new StudentAlreadyExistsException(student.getEmail() + " Already Exists");
        }

        return studentRepository.save(student);
    }
    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    @Override
    public Student updateStudent(Student student, Long id) {


        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(() -> new  StudentNotFoundException("Sorry, this Student is not found"));
    }
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Sorry, No student found with the id :" +id));
    }
    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Student not found");
        }

        studentRepository.deleteById(id);

    }
    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
