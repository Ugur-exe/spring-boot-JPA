package com.ugurkolcak.services;

import java.util.List;

import com.ugurkolcak.dto.request.CreateStudentRequest;
import com.ugurkolcak.dto.response.StudentResponse;

public interface IStudentService {
    public StudentResponse saveStudent(CreateStudentRequest student);

    public List<StudentResponse> getAllStudents();

    public StudentResponse getStudentById(Integer id);

    public void deleteStudentById(Integer id);

    public StudentResponse updateStudent(Integer id, CreateStudentRequest student);

}
