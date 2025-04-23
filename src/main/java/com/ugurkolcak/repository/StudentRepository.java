package com.ugurkolcak.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ugurkolcak.entities.Student;

@Repository

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT *FROM student.student where id=:id", nativeQuery = true)
    Optional<Student> findByUserId(Integer id);
}
