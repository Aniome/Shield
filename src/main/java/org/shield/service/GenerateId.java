package org.shield.service;

import org.springframework.data.jpa.repository.JpaRepository;

public class GenerateId <T>{
    public Long getId(JpaRepository<T, Long> repository){
        Long i;
        for (i = 0L; i < Long.MAX_VALUE; i++) {
            if (!repository.existsById(i)){
                break;
            }
        }
        return i;
    }
}
