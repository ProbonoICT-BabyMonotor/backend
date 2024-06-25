package com.baby.monitor.persistance;

import com.baby.monitor.domain.BabyVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BabyRepository extends CrudRepository<BabyVO, Integer> {
    BabyVO findByMemberNumber(int memberNumber);
}
