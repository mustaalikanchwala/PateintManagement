package com.pm.pateintService.controller;

import com.pm.pateintService.dto.PateintRequest;
import com.pm.pateintService.dto.PateintResponse;
import com.pm.pateintService.service.PateintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PateintResponse> createPateint(@Valid @RequestBody PateintRequest request){
        return ResponseEntity.ok(pateintService.createPateint(request));
    }

}
