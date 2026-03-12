package com.pm.pateintService.dto;

import com.pm.pateintService.model.Pateint;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record PateintResponse(
        UUID id,
        String name,
        String email,
        String address,
        LocalDateTime dob,
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
}
