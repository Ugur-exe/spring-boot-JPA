package com.ugurkolcak.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private List<String> addMapValue(List<String> list, String value) {
        list.add(value);
        return list;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) objectError).getField();
            if (errors.containsKey(fieldName)) {
                errors.put(fieldName, addMapValue(errors.get(fieldName), objectError.getDefaultMessage()));
            } else {
                errors.put(fieldName, addMapValue(new ArrayList<>(), objectError.getDefaultMessage()));
            }
        }
        return ResponseEntity.badRequest().body(createApiError(errors));
    }

    private <T> ApiError<T> createApiError(T errors) {
        ApiError<T> apiError = new ApiError<T>();
        apiError.setId(UUID.randomUUID().toString());
        apiError.setErrorTime(new java.sql.Date(System.currentTimeMillis()));
        apiError.setErrors(errors);
        return apiError;
    }
}
