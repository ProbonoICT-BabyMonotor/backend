package com.baby.monitor.service;

import com.baby.monitor.persistance.Stm32Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Stm32Service {

    private final Stm32Repository stm32JPA;
    
    // 추후 암호화 고려
    public String findMyIp(int memberNumber){
        String myIp = stm32JPA.findByMemberNumber(memberNumber).getStm32Ip();
        if (myIp == null){
            throw new IllegalArgumentException("사용자의 IP 주소를 찾을 수 없습니다.");
        }
        return myIp;
    }



}
