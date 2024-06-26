package com.baby.monitor.persistance;

import com.baby.monitor.domain.InoculationMemberVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InoculationMemberRepository extends CrudRepository<InoculationMemberVO, Integer> {

    List<InoculationMemberVO> findAllByMemberNumber(int memberNumber);

    InoculationMemberVO findByMemberNumberAndInoculationNumber(int memberNumber, int inoculationNumber);
}
