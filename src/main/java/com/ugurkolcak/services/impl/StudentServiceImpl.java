package com.ugurkolcak.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ugurkolcak.converter.StudentConverter;
import com.ugurkolcak.dto.request.CreateStudentRequest;
import com.ugurkolcak.dto.response.StudentResponse;
import com.ugurkolcak.entities.Student;
import com.ugurkolcak.exception.ResourceNotFoundException;
import com.ugurkolcak.repository.StudentRepository;
import com.ugurkolcak.services.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;

    public StudentServiceImpl(StudentRepository studentRepository, StudentConverter studentConverter) {
        this.studentRepository = studentRepository;
        this.studentConverter = studentConverter;
    }

    @Override
    @Transactional
    public StudentResponse saveStudent(CreateStudentRequest dtoStudentIU) {

        Student student = studentConverter.toEntity(dtoStudentIU);

        Student dbStudent = studentRepository.save(student);

        return studentConverter.toResponse(dbStudent);
    }

    @Override
    @Transactional(readOnly = true) // Sadece okuma işlemi
    public List<StudentResponse> getAllStudents() {
        List<Student> dbStudents = studentRepository.findAll();
        // Converter ile tüm listeyi DTO listesine çevir
        return studentConverter.toResponseList(dbStudents);
    }

    @Override
    @Transactional(readOnly = true) // Sadece okuma işlemi
    public StudentResponse getStudentById(Integer id) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id)); 
        // Bulunan entity'yi DTO'ya çevir
        return studentConverter.toResponse(dbStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(Integer id) {

        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student", "id", id);
        }
        studentRepository.deleteById(id);

    }

    @Override
    @Transactional
    public StudentResponse updateStudent(Integer id, CreateStudentRequest dtoStudentIU) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        studentConverter.updateEntityFromDto(dtoStudentIU, dbStudent);

        Student updatedStudent = studentRepository.save(dbStudent);

        return studentConverter.toResponse(updatedStudent);
    }
}