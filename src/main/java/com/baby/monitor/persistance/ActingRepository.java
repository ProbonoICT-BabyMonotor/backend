package com.baby.monitor.persistance;

import com.baby.monitor.domain.ActingVO;
import org.springframework.data.repository.CrudRepository;

public interface ActingRepository extends CrudRepository<ActingVO, Integer> {
    ActingVO findByMemberNumber(int memberNumber);
}
