package com.student_crud.exception;



/**
 * Thrown when a unique constraint would be violated.
 * Maps to HTTP 409 Conflict.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String resourceName,
                                      String fieldName,
                                      String fieldValue) {
        super(String.format("%s already exists with %s: '%s'",
                resourceName, fieldName, fieldValue));
    }
}