package com.baby.monitor.service;

import com.baby.monitor.DTO.ActingDTO;
import com.baby.monitor.DTO.SensorDTO;
import com.baby.monitor.DTO.SleepingDTO;
import com.baby.monitor.DTO.SleepingDetailDTO;
import com.baby.monitor.domain.ActingVO;
import com.baby.monitor.domain.SleepingSensorVO;
import com.baby.monitor.domain.SleepingVO;
import com.baby.monitor.persistance.ActingRepository;
import com.baby.monitor.persistance.SleepingRepository;
import com.baby.monitor.persistance.SleepingSensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SleepingService {
    private final SleepingRepository sleepingJPA;
    private final SleepingSensorRepository sleepingSeneorJPA;
    private final ActingRepository actingJPA;

    /**
     * 회원 번호로 취침 이력들 조회하기
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

    /* =============================== */

    /**
     * 취침 번호로 취침 상세 이력 조회하기
     * @param sleepingNumber
     * @return
     */
    public SleepingDetailDTO searchSleepingDetail(int sleepingNumber) throws Exception{
        SleepingVO sleepingVO = sleepingJPA.findBySleepingNumber(sleepingNumber);
        if (sleepingVO == null){
            throw new NullPointerException("취침 이력이 존재하지 않습니다.");
        }
        ArrayList<ActingVO> actingVOS = actingJPA.findAllBySleepingNumber(sleepingNumber);

        SleepingDetailDTO sleepingDetailDTO = new SleepingDetailDTO(sleepingVO);

        // Sensor Setting
        sleepingDetailDTO.setSleepingSensor(getSensorDTO(sleepingNumber));

        // Acting Setting
        ArrayList<ActingDTO> actingDTOS = new ArrayList<>();
        for (ActingVO a : actingVOS){
            ActingDTO actingDTO = new ActingDTO(a);
            actingDTOS.add(actingDTO);
        }
        sleepingDetailDTO.setSleepingAction(actingDTOS);

        return sleepingDetailDTO;
    }

    /**
     * 취침 번호로 센서의 평균, 최고, 최저 작성하기
     * @param sleepingNumber
     * @return
     */
    private SensorDTO getSensorDTO(int sleepingNumber){
        SleepingSensorVO s = sleepingSeneorJPA.findBySleepingNumber(sleepingNumber);
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setTemperature(String.format("최저 : %.1f도 | 평균 : %.1f도 | 최고 : %.1f도", s.getTemperatureMin(), s.getTemperatureAver(), s.getTemperatureMax()));
        sensorDTO.setHeartRate(String.format("최저 : %d회 | 평균 : %d회 | 최고 : %d회", s.getHeartRateMin(), s.getHeartRateAver(), s.getHeartRateMax()));
        sensorDTO.setWeight(String.format("%.1fkg", s.getWeight()));
        if (s.isBreath()){
            sensorDTO.setBreath("이상 없음");
        } else {
            sensorDTO.setBreath("이상 데이터 존재");
        }
        return sensorDTO;
    }
}
