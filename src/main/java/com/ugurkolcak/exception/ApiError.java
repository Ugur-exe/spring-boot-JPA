package com.ugurkolcak.exception;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError<T> {
    private String id;
    private Date errorTime;
    private T errors;
}
