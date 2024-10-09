package com.baby.monitor.service;

import com.baby.monitor.domain.SensorsVO;
import com.baby.monitor.domain.SleepingVO;
import com.baby.monitor.persistance.SensorRepository;
import com.baby.monitor.persistance.SleepingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorJPA;
    private final SleepingRepository sleepingJPA;

    public Map<String, List<SensorsVO>> findBySensorDateAndMemberNumber(String sensorDateString, int memberNumber) {
        String[] dateParts = sensorDateString.split("_");

        int year = Integer.parseInt(dateParts[0]);  // "2021"
        int month = Integer.parseInt(dateParts[1]); // "01"
        int day = Integer.parseInt(dateParts[2]);   // "01"

        LocalDate sensorDate = LocalDate.of(year, month, day);

        LocalDateTime startOfDay = sensorDate.atStartOfDay();
        LocalDateTime endOfDay = sensorDate.atTime(LocalTime.MAX);

        return groupByHourAndSort(sensorJPA.findALLBySensorDateBetweenAndMemberNumber(startOfDay, endOfDay, memberNumber));

    }

    // 시간별로 그룹화하고, 시간순으로 정렬된 데이터를 반환
    public Map<String, List<SensorsVO>> groupByHourAndSort(List<SensorsVO> sensors) {
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH시");

        // 1. 각 sensorDate에서 시간을 추출하여 그룹화
        Map<String, List<SensorsVO>> groupedByHour = sensors.stream()
                .collect(Collectors.groupingBy(sensor -> {
                    LocalDateTime sensorDate = sensor.getSensorDate();
                    return sensorDate.format(hourFormatter);  // "HH시"로 그룹화
                }));

        // 2. 그룹화된 데이터를 시간순으로 정렬하여 LinkedHashMap으로 반환
        return groupedByHour.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())  // 키(시간) 기준으로 정렬
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));  // 순서를 유지하는 LinkedHashMap으로 변환
    }

    public Float checkBabyTemperature(int memberNumber){
        SleepingVO sleep1 = sleepingJPA.findFirstBySleepingEndTimeIsNullAndMemberNumberOrderBySleepingNumberDesc(memberNumber);

        // 기존에 취침 하고 있는 아기가 있다면
        if (sleep1 != null) {
            float temperature = sensorJPA.findFirstByMemberNumberOrderBySleepingNumberDesc(memberNumber).getSensorTemperature();
            return temperature;
        }
        // 없다면 새로 만들기
        else {
            return null;
        }
    }

}
