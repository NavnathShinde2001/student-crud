package com.student_crud.service;



import java.util.List;

import com.student_crud.config.dto.StudentRequest;
import com.student_crud.config.dto.StudentResponse;

/**
 * Service interface – defines the business contract.
 * Controller depends only on this interface (not the impl).
 */
public interface StudentService {

    // CREATE
    StudentResponse createStudent(StudentRequest request);

    // READ
    StudentResponse getStudentById(String id);
    List<StudentResponse> getAllStudents();
    List<StudentResponse> getStudentsByCourse(String course);
    List<StudentResponse> getStudentsByYear(Integer year);
    List<StudentResponse> searchStudentsByName(String name);
    List<StudentResponse> getActiveStudents();

    // UPDATE
    StudentResponse updateStudent(String id, StudentRequest request);

    // DELETE
    void deleteStudent(String id);
}