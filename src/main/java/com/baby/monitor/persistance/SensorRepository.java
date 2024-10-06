package com.baby.monitor.persistance;

import com.baby.monitor.domain.SensorsVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorRepository  extends CrudRepository<SensorsVO, Integer> {
    List<SensorsVO> findALLBySensorDateBetweenAndMemberNumber(LocalDateTime startOfDay, LocalDateTime endOfDay,  int memberNumber);
}
