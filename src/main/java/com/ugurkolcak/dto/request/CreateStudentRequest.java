package com.ugurkolcak.dto.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {
    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, max = 40, message = "First name must be between 2-40 characters")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    @Size(min = 2, max = 40, message = "Last name must be  between 2-40  characters")
    private String lastName;

    @DateTimeFormat(iso = ISO.DATE)

    private Date birthOfDate;
}
