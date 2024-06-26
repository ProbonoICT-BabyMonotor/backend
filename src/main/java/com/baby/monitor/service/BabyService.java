package com.baby.monitor.service;

import com.baby.monitor.domain.BabyVO;
import com.baby.monitor.persistance.BabyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BabyService {

    private final BabyRepository babyJPA;

    public BabyVO signUpBaby(BabyVO baby){
        return babyJPA.save(baby);
    }
}
