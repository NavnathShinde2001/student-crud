package com.student_crud.repository;




import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.student_crud.model.Student;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository.
 * MongoRepository<Student, String> provides:
 *   save(), findById(), findAll(), deleteById(), count(), etc.
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    // Find by unique email
    Optional<Student> findByEmail(String email);

    // Check duplicate email
    boolean existsByEmail(String email);

    // Filter by course
    List<Student> findByCourse(String course);

    // Filter by year
    List<Student> findByYear(Integer year);

    // Filter by active status
    List<Student> findByActive(Boolean active);

    // Filter by course + year
    List<Student> findByCourseAndYear(String course, Integer year);

    // Case-insensitive name search
    @Query("{ $or: [ " +
           "{ 'firstName': { $regex: ?0, $options: 'i' } }, " +
           "{ 'lastName':  { $regex: ?0, $options: 'i' } } " +
           "] }")
    List<Student> searchByName(String name);
}