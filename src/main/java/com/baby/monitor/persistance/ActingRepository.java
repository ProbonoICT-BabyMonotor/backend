package com.baby.monitor.persistance;

import com.baby.monitor.domain.ActingVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ActingRepository extends CrudRepository<ActingVO, Integer> {
    // 제일 마지막 값을 가져오기
    ActingVO findFirstByMemberNumberOrderByActingNumberDesc(int memberNumber);
    ArrayList<ActingVO> findAllBySleepingNumber(int sleepingNumber);


}
