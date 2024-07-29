package com.baby.monitor.persistance;

import com.baby.monitor.domain.SleepingVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SleepingRepository extends CrudRepository<SleepingVO, Integer> {
    ArrayList<SleepingVO> findAllByMemberNumber(int memberNumber);

    SleepingVO findBySleepingNumber(int sleepingNumber);
}
