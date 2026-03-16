package com.pm.pateintService.service;

import com.pm.pateintService.dto.PateintRequest;
import com.pm.pateintService.dto.PateintResponse;
import com.pm.pateintService.exception.EmailAlreadyExistsExcpetion;
import com.pm.pateintService.exception.PatientNotFoundException;
import com.pm.pateintService.model.Pateint;
import com.pm.pateintService.repository.PateintRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        if(pateintRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsExcpetion("Email is already Register: "+request.email());
        }
        Pateint pateint = Pateint.toPateint(request);
        Pateint savedPateint = pateintRepository.save(pateint);
        return PateintResponse.convertOnePateintToResponse(savedPateint);
    }

    public PateintResponse updatePateint(UUID id,PateintRequest request){
        Pateint pateint = pateintRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient Not found with ID: "+ id ));
        if( !request.email().equalsIgnoreCase(pateint.getEmail()) && pateintRepository.existsByEmailAndIdNot(request.email(),pateint.getId())){
            throw new EmailAlreadyExistsExcpetion("Email is already Register: "+request.email());
        }
        pateint.setName(request.name());
        pateint.setEmail(request.email());
        pateint.setAddress(request.address());

        return PateintResponse.convertOnePateintToResponse(pateintRepository.save(pateint));
    }

}
