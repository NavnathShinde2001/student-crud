package com.student_crud.exception;


/**
 * Thrown when a requested resource does not exist in the database.
 * Maps to HTTP 404.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName,
                                     String fieldName,
                                     String fieldValue) {
        super(String.format("%s not found with %s: '%s'",
                resourceName, fieldName, fieldValue));
    }
}