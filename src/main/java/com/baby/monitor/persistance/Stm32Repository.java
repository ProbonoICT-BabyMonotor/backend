package com.baby.monitor.persistance;

import com.baby.monitor.domain.Stm32VO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Stm32Repository extends CrudRepository<Stm32VO, Integer> {
    Stm32VO findByMemberNumber(int memberNumber);
}
