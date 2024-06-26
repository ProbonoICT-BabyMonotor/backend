package com.baby.monitor.persistance;

import com.baby.monitor.domain.InoculationListVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InoculationListRepository extends CrudRepository<InoculationListVO, Integer> {

    public InoculationListVO findByInoculationNumber(int inoculationNumber);
}
