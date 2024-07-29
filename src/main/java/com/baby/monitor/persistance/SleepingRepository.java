package com.baby.monitor.persistance;

import com.baby.monitor.domain.SleepingVO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SleepingRepository extends CrudRepository<SleepingVO, Integer> {
    ArrayList<SleepingVO> findAllByMemberNumber(int memberNumber);
}
