package com.iproject.backend.controller;

import com.iproject.backend.model.Student;
import com.iproject.backend.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable  Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping(params = "email")
    public Student getByEmail(@RequestParam String email) {
        return studentService.getStudentByEmail(email);
    }

    @GetMapping(params = "firstName")
    public List<Student> getByFirstName(@RequestParam String firstName) {
        return studentService.getStudentsByFirstName(firstName);
    }

}
