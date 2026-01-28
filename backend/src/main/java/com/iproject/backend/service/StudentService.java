package com.iproject.backend.service;

import com.iproject.backend.exception.DuplicateEmailException;
import com.iproject.backend.exception.StudentNotFoundException;
import com.iproject.backend.model.Student;
import com.iproject.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student){
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new DuplicateEmailException("Student with this email already exists");
        }
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id){
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    public Student getStudentByEmail(String email){
        return studentRepository.findByEmail(email).orElseThrow(() -> new StudentNotFoundException("Student with " + email +  " not found"));
    }

    public List<Student> getStudentsByFirstName(String name) {
        List<Student> students = studentRepository.findActiveStudentByFirstName(name);
        if (students.isEmpty()) {
            throw new StudentNotFoundException("No students found");
        }
        return students;
    }
}
