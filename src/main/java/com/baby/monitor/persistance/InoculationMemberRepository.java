package com.baby.monitor.persistance;

import com.baby.monitor.domain.InoculationMemberVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InoculationMemberRepository extends CrudRepository<InoculationMemberVO, Integer> {
    InoculationMemberVO findByMemberNumberAndInoculationNumber(int memberNumber, int inoculationNumber);
}
