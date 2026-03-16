package com.pm.pateintService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pm.pateintService.dto.validators.CreatePateintValidationGroup;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PateintRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100,message = "Name cannot exceed !00 Characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email is Not Valid")
        String email,

        @NotBlank(message = "Address is required")
        String address,

        @NotNull(groups = CreatePateintValidationGroup.class, message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dob,

        @NotNull(groups = CreatePateintValidationGroup.class, message = "Registered date is required")
        @PastOrPresent(message = "Registered date cannot be in the future")
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime registeredDate
) {

}
