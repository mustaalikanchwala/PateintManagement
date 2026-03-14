package com.pm.pateintService.model;

import com.pm.pateintService.dto.PateintRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pateint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String address;

    @NotNull
    private LocalDate dob;

    @NotNull
    private LocalDateTime registeredDate;

    public static Pateint toPateint(PateintRequest request){
        return Pateint.builder()
                .name(request.name())
                .email(request.email())
                .address(request.address())
                .dob(request.dob())
                .registeredDate(request.registeredDate())
                .build();
    }
}
