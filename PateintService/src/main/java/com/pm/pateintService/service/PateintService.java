package com.pm.pateintService.service;

import com.pm.pateintService.dto.PateintRequest;
import com.pm.pateintService.dto.PateintResponse;
import com.pm.pateintService.model.Pateint;
import com.pm.pateintService.repository.PateintRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class PateintService {
    private final PateintRepository pateintRepository;

    public List<PateintResponse> getPateints(){
        List<Pateint> pateints = pateintRepository.findAll();
        return PateintResponse.convertPateintToResponse(pateints);
    }

    public PateintResponse createPateint(PateintRequest request){
        Pateint pateint = Pateint.toPateint(request);
        Pateint savedPateint = pateintRepository.save(pateint);
        return PateintResponse.convertOnePateintToResponse(savedPateint);
    }

}
