package com.baby.monitor.service;

import com.baby.monitor.DTO.SleepingDTO;
import com.baby.monitor.domain.SleepingVO;
import com.baby.monitor.persistance.SleepingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SleepingService {
    private final SleepingRepository sleepingJPA;


    /**
     * 회원 번호로 취침 이력 조회하기
     * @param memberNumber
     * @return
     */
    public ArrayList<SleepingDTO> searchSleepingList(int memberNumber) throws Exception{
        ArrayList<SleepingVO> sleepingVOS = sleepingJPA.findAllByMemberNumber(memberNumber);
        if (sleepingVOS.isEmpty()){
            throw new NullPointerException("취침 이력이 없습니다.");
        } else {
            return changeSleepingVOToDTO(sleepingVOS);
        }
    }

    /**
     * VO를 DTO로 변경
     * @param sleepingVOS
     * @return
     */
    private ArrayList<SleepingDTO> changeSleepingVOToDTO(ArrayList<SleepingVO> sleepingVOS){
        ArrayList<SleepingDTO> sleepingDTOS = new ArrayList<>();
        for(SleepingVO sleeping : sleepingVOS){
            sleepingDTOS.add(new SleepingDTO(sleeping.getSleepingNumber(), sleeping.getSleepingTime(), sleeping.getSleepingEndTime()));
        }
        Collections.reverse(sleepingDTOS); // 최근 순으로 출력 함
        return sleepingDTOS;
    }
}
