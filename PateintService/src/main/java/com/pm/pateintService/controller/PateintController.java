package com.pm.pateintService.controller;

import com.pm.pateintService.dto.PateintRequest;
import com.pm.pateintService.dto.PateintResponse;
import com.pm.pateintService.dto.validators.CreatePateintValidationGroup;
import com.pm.pateintService.service.PateintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pateint")
@Tag(name = "Patient" , description = "API FOr Managing Patients")
public class PateintController {
    private final PateintService pateintService;

    @GetMapping("/all")
    @Operation(summary = "Get All Patients EndPoint")
    public ResponseEntity<List<PateintResponse>> getPateints(){
        return ResponseEntity.ok(pateintService.getPateints()) ;
    }

    @PostMapping("/create")
    @Operation(summary = "Add a new Patient")
    public ResponseEntity<PateintResponse> createPateint(@Validated({Default.class, CreatePateintValidationGroup.class}) @RequestBody PateintRequest request){
        return ResponseEntity.ok(pateintService.createPateint(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Existing Patient")
    public ResponseEntity<PateintResponse> updatePateint(@PathVariable UUID id,@Validated({Default.class}) @RequestBody PateintRequest request){
        return ResponseEntity.ok(pateintService.updatePateint(id,request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Existing Patient")
    public ResponseEntity<String> deletePateint(@PathVariable UUID id){
        pateintService.deletePatient(id);
        return ResponseEntity.ok("Patient Deleted SuccessFully");
    }

}
