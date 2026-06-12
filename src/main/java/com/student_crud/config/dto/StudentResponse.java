package com.student_crud.config.dto;


import lombok.*;
import java.time.LocalDateTime;

/**
 * Output DTO – returned in every API response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String course;
    private Integer year;
    private Double cgpa;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
