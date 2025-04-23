package com.ugurkolcak.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugurkolcak.controller.IStudentContoller;
import com.ugurkolcak.dto.request.CreateStudentRequest;
import com.ugurkolcak.dto.response.StudentResponse;

import com.ugurkolcak.services.IStudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/rest/api/student")
public class StudentControllerImpl implements IStudentContoller {

    private final IStudentService studentService;

    public StudentControllerImpl(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/save")
    @Override
    public StudentResponse saveStudent(@RequestBody @Valid CreateStudentRequest dtoStudentIU) {
        return studentService.saveStudent(dtoStudentIU);
    }

    @GetMapping(path = "/all-student")
    @Override
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    @Override
    public StudentResponse getStudentById(@PathVariable(name = "id") Integer id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping(path = "/{id}")
    @Override
    public void deleteStudentById(@PathVariable(name = "id") Integer id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping("/{id}")
    @Override
    public StudentResponse updateStudent(@PathVariable(name = "id") Integer id,
            @RequestBody CreateStudentRequest student) {

        return studentService.updateStudent(id, student);
    }
}
