package com.baby.monitor.persistance;

import com.baby.monitor.domain.SleepingSensorVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepingSensorRepository extends CrudRepository<SleepingSensorVO, Integer> {

    // Sleeping과 SleepingSensor는 1:1관계
    SleepingSensorVO findBySleepingNumber(int sleepingNumber); 
}
