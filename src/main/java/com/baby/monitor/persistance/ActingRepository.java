package com.baby.monitor.persistance;

import com.baby.monitor.domain.ActingVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ActingRepository extends CrudRepository<ActingVO, Integer> {
    ActingVO findByMemberNumber(int memberNumber);
    ArrayList<ActingVO> findAllBySleepingNumber(int sleepingNumber);
}
