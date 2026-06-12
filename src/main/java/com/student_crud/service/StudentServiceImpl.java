package com.student_crud.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.student_crud.config.dto.StudentRequest;
import com.student_crud.config.dto.StudentResponse;
import com.student_crud.exception.DuplicateResourceException;
import com.student_crud.exception.ResourceNotFoundException;
import com.student_crud.model.Student;
import com.student_crud.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements all business logic for Student CRUD operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // ── CREATE ────────────────────────────────────────────────────────────────

    @Override
    public StudentResponse createStudent(StudentRequest request) {
        log.info("Creating student with email: {}", request.getEmail());

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Student", "email", request.getEmail());
        }

        Student student = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .course(request.getCourse())
                .year(request.getYear())
                .cgpa(request.getCgpa())
                .active(request.getActive() != null ? request.getActive() : true)
                .build();

        Student saved = studentRepository.save(student);
        log.info("Student created with id: {}", saved.getId());
        return mapToResponse(saved);
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    @Override
    public StudentResponse getStudentById(String id) {
        log.info("Fetching student with id: {}", id);
        return mapToResponse(findByIdOrThrow(id));
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponse> getStudentsByCourse(String course) {
        log.info("Fetching students by course: {}", course);
        return studentRepository.findByCourse(course)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponse> getStudentsByYear(Integer year) {
        log.info("Fetching students by year: {}", year);
        return studentRepository.findByYear(year)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponse> searchStudentsByName(String name) {
        log.info("Searching students by name: {}", name);
        return studentRepository.searchByName(name)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponse> getActiveStudents() {
        return studentRepository.findByActive(true)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    @Override
    public StudentResponse updateStudent(String id, StudentRequest request) {
        log.info("Updating student with id: {}", id);

        Student existing = findByIdOrThrow(id);

        // Allow same email for same student; block if another student owns it
        if (!existing.getEmail().equals(request.getEmail())
                && studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Student", "email", request.getEmail());
        }

        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setEmail(request.getEmail());
        existing.setPhone(request.getPhone());
        existing.setCourse(request.getCourse());
        existing.setYear(request.getYear());
        existing.setCgpa(request.getCgpa());
        if (request.getActive() != null) existing.setActive(request.getActive());

        Student updated = studentRepository.save(existing);
        log.info("Student updated: {}", updated.getId());
        return mapToResponse(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    @Override
    public void deleteStudent(String id) {
        log.info("Deleting student with id: {}", id);
        studentRepository.delete(findByIdOrThrow(id));
        log.info("Student deleted: {}", id);
    }

    // ── PRIVATE HELPERS ───────────────────────────────────────────────────────

    private Student findByIdOrThrow(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", "id", id));
    }

    private StudentResponse mapToResponse(Student s) {
        return StudentResponse.builder()
                .id(s.getId())
                .firstName(s.getFirstName())
                .lastName(s.getLastName())
                .email(s.getEmail())
                .phone(s.getPhone())
                .course(s.getCourse())
                .year(s.getYear())
                .cgpa(s.getCgpa())
                .active(s.getActive())
                .createdAt(s.getCreatedAt())
                .updatedAt(s.getUpdatedAt())
                .build();
    }
}