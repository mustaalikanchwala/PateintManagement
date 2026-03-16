package com.pm.pateintService.repository;

import com.pm.pateintService.model.Pateint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PateintRepository extends JpaRepository<Pateint, UUID> {
    boolean existsByEmail(String email);


    boolean existsByEmailAndIdNot(String email , UUID id);
}
