package com.ugurkolcak.converter;

import com.ugurkolcak.dto.request.CreateStudentRequest;
import com.ugurkolcak.dto.response.StudentResponse;
import com.ugurkolcak.entities.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter {
    
    public Student toEntity(CreateStudentRequest dto) {
        return Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthOfDate(dto.getBirthOfDate())
                .build();
    }
    
    public StudentResponse toResponse(Student entity) {
        return StudentResponse.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
    
    public List<StudentResponse> toResponseList(List<Student> entities) {
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public void updateEntityFromDto(CreateStudentRequest dto, Student entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthOfDate(dto.getBirthOfDate());
    }
}