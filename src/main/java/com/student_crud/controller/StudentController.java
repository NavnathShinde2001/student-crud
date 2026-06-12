package com.student_crud.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student_crud.config.dto.ApiResponse;
import com.student_crud.config.dto.StudentRequest;
import com.student_crud.config.dto.StudentResponse;
import com.student_crud.service.StudentService;

import java.util.List;

/**
 * REST Controller – exposes all Student CRUD endpoints.
 * Base URL: /api/v1/students
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // ── CREATE ────────────────────────────────────────────────────────────────

    /**
     * POST /api/v1/students
     * Creates a new student record.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> createStudent(
            @Valid @RequestBody StudentRequest request) {

        log.info("POST /api/v1/students");
        StudentResponse response = studentService.createStudent(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Student created successfully", response));
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    /**
     * GET /api/v1/students
     * Returns all students.
     * Optional query params:
     *   ?course=ComputerScience
     *   ?year=2
     *   ?search=John
     *   ?activeOnly=true
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents(
            @RequestParam(required = false) String course,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean activeOnly) {

        log.info("GET /api/v1/students");
        List<StudentResponse> students;

        if (course != null && !course.isBlank()) {
            students = studentService.getStudentsByCourse(course);
        } else if (year != null) {
            students = studentService.getStudentsByYear(year);
        } else if (search != null && !search.isBlank()) {
            students = studentService.searchStudentsByName(search);
        } else if (Boolean.TRUE.equals(activeOnly)) {
            students = studentService.getActiveStudents();
        } else {
            students = studentService.getAllStudents();
        }

        return ResponseEntity.ok(
                ApiResponse.success("Students fetched successfully", students));
    }

    /**
     * GET /api/v1/students/{id}
     * Returns a single student by MongoDB ObjectId.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentById(
            @PathVariable String id) {

        log.info("GET /api/v1/students/{}", id);
        return ResponseEntity.ok(
                ApiResponse.success("Student fetched successfully",
                        studentService.getStudentById(id)));
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    /**
     * PUT /api/v1/students/{id}
     * Fully replaces a student record.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(
            @PathVariable String id,
            @Valid @RequestBody StudentRequest request) {

        log.info("PUT /api/v1/students/{}", id);
        return ResponseEntity.ok(
                ApiResponse.success("Student updated successfully",
                        studentService.updateStudent(id, request)));
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    /**
     * DELETE /api/v1/students/{id}
     * Permanently removes a student record.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(
            @PathVariable String id) {

        log.info("DELETE /api/v1/students/{}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.ok(
                ApiResponse.success("Student deleted successfully", null));
    }
}