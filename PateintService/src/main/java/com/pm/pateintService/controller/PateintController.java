package com.pm.pateintService.controller;

import com.pm.pateintService.dto.PateintRequest;
import com.pm.pateintService.dto.PateintResponse;
import com.pm.pateintService.dto.validators.CreatePateintValidationGroup;
import com.pm.pateintService.service.PateintService;
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
public class PateintController {
    private final PateintService pateintService;

    @GetMapping("/all")
    public ResponseEntity<List<PateintResponse>> getPateints(){
        return ResponseEntity.ok(pateintService.getPateints()) ;
    }

    @PostMapping("/create")
    public ResponseEntity<PateintResponse> createPateint(@Validated({Default.class, CreatePateintValidationGroup.class}) @RequestBody PateintRequest request){
        return ResponseEntity.ok(pateintService.createPateint(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PateintResponse> updatePateint(@PathVariable UUID id,@Validated({Default.class}) @RequestBody PateintRequest request){
        return ResponseEntity.ok(pateintService.updatePateint(id,request));
    }

}
