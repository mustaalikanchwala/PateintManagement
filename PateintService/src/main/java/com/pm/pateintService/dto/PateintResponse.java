package com.pm.pateintService.dto;

import com.pm.pateintService.model.Pateint;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record PateintResponse(
        UUID id,
        String name,
        String email,
        String address,
        LocalDate dob,
        LocalDateTime registeredDate
) {
    public static List<PateintResponse> convertPateintToResponse(List<Pateint> patients) {
        return patients.stream()
                .map(patient -> new PateintResponse(
                        patient.getId(),
                        patient.getName(),
                        patient.getEmail(),
                        patient.getAddress(),
                        patient.getDob(),
                        patient.getRegisteredDate()
                ))
                .collect(Collectors.toList());  // Use .toList() for Java 16+
    }
    public static PateintResponse convertOnePateintToResponse(Pateint patients) {
        return PateintResponse.builder()
                .id(patients.getId())
                .name(patients.getName())
                .email(patients.getEmail())
                .address(patients.getAddress())
                .dob(patients.getDob())
                .registeredDate(patients.getRegisteredDate())
                .build();
    }
}
