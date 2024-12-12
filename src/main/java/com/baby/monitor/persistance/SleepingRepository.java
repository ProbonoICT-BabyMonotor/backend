package com.baby.monitor.persistance;

import com.baby.monitor.domain.SleepingVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SleepingRepository extends CrudRepository<SleepingVO, Integer> {
    ArrayList<SleepingVO> findAllByMemberNumber(int memberNumber);

    // 수면 번호 뒤에서부터, 회원번호로 뽑아오는데, 아직 자고 있는 아기가 있다면 (수면중인 아기는 SleepingEndTime이 null)
    SleepingVO findFirstBySleepingEndTimeIsNullAndMemberNumberOrderBySleepingNumberDesc(int memberNumber);


    SleepingVO findBySleepingNumber(int sleepingNumber);
}
