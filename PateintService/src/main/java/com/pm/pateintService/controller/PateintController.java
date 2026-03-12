package com.pm.pateintService.controller;

import com.pm.pateintService.dto.PateintResponse;
import com.pm.pateintService.service.PateintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
