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

    // Constructor Injection - StudentMapper yerine StudentConverter
    public StudentServiceImpl(StudentRepository studentRepository, StudentConverter studentConverter) {
        this.studentRepository = studentRepository;
        this.studentConverter = studentConverter;
    }

    @Override
    @Transactional // Yazma işlemi olduğu için
    public StudentResponse saveStudent(CreateStudentRequest dtoStudentIU) {
        // 1. DTO'yu Entity'ye çevir (StudentConverter kullanarak)
        Student student = studentConverter.toEntity(dtoStudentIU);
        // 2. Entity'yi kaydet
        Student dbStudent = studentRepository.save(student);
        // 3. Kaydedilmiş Entity'yi Response DTO'suna çevir ve döndür
        return studentConverter.toResponse(dbStudent);
    }

    @Override
    @Transactional(readOnly = true) // Sadece okuma işlemi, performansı artırabilir
    public List<StudentResponse> getAllStudents() {
        List<Student> dbStudents = studentRepository.findAll();
        // Converter ile tüm listeyi DTO listesine çevir
        return studentConverter.toResponseList(dbStudents);
    }

    @Override
    @Transactional(readOnly = true) // Sadece okuma işlemi
    public StudentResponse getStudentById(Integer id) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id)); // Bulamazsa exception fırlat
        // Bulunan entity'yi DTO'ya çevir
        return studentConverter.toResponse(dbStudent);
    }

    @Override
    @Transactional // Silme işlemi olduğu için
    public void deleteStudentById(Integer id) {
        // Önce var olup olmadığını kontrol etmek iyi bir pratik olabilir
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student", "id", id);
        }
        studentRepository.deleteById(id); // Varsa sil

        // Alternatif: findById kullanıp sonra silmek (bir sorgu fazla yapar ama
        // entity'ye erişim sağlar gerekirse)
        /*
         * Student studentToDelete = studentRepository.findById(id)
         * .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
         * studentRepository.delete(studentToDelete);
         */
    }

    @Override
    @Transactional
    public StudentResponse updateStudent(Integer id, CreateStudentRequest dtoStudentIU) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        // StudentConverter kullanarak güncelleme
        studentConverter.updateEntityFromDto(dtoStudentIU, dbStudent);

        Student updatedStudent = studentRepository.save(dbStudent);

        return studentConverter.toResponse(updatedStudent);
    }
}