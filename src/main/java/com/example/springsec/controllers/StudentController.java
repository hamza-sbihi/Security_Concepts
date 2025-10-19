package com.example.springsec.controllers;

import com.example.springsec.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    List<Student> students = new ArrayList<>(List.of(
            new Student(1,"hamza","sbihi"),
            new Student(2,"hayfae","essalhi")
    ));
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(students);
    }

    @GetMapping("/csrf")
    public ResponseEntity<CsrfToken> getCsrfToken(HttpServletRequest request){
        return ResponseEntity.ok((CsrfToken) request.getAttribute("_csrf"));
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student s){
        students.add(s);
        return ResponseEntity.ok(s);
    }
}
