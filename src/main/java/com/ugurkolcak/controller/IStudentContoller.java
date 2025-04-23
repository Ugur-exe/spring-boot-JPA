package com.ugurkolcak.controller;

import java.util.List;

import com.ugurkolcak.dto.request.CreateStudentRequest;
import com.ugurkolcak.dto.response.StudentResponse;

public interface IStudentContoller {

    public StudentResponse saveStudent(CreateStudentRequest dtoStudentIU);

    public List<StudentResponse> getAllStudents();

    public StudentResponse getStudentById(Integer id);

    public void deleteStudentById(Integer id);

    public StudentResponse updateStudent(Integer id, CreateStudentRequest student);
}
