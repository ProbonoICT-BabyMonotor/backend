package com.baby.monitor.persistance;

import com.baby.monitor.domain.MemberVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<MemberVO, Integer> {
    MemberVO findByMemberId(String memberId);
}
