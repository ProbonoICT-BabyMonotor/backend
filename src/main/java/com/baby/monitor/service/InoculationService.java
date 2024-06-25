package com.baby.monitor.service;

import com.baby.monitor.domain.BabyVO;
import com.baby.monitor.domain.InoculationListVO;
import com.baby.monitor.domain.InoculationMemberVO;
import com.baby.monitor.persistance.BabyRepository;
import com.baby.monitor.persistance.InoculationListRepository;
import com.baby.monitor.persistance.InoculationMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InoculationService {

    private final InoculationListRepository inoculationJPA;
    private final InoculationMemberRepository inoculationMemberJPA;
    private final BabyRepository babyJPA;

    /** 접종 해야 할 List를 출력 
     * @return key-value의 형태 (Key : 접종 일자 / Value : list(접종 이름))
     */
    public HashMap<String, List> findInoculationList(){
        List<InoculationListVO> inoculationListVO = (List<InoculationListVO>) inoculationJPA.findAll();
        HashMap<String, List> inoculationList = new HashMap<>();

        for (InoculationListVO i : inoculationListVO){
            String toInoculationDate = i.getInoculationDate()+i.getInoculationPrieod(); // 접종 해야 하는 날 (1month)

            if (inoculationList.get(toInoculationDate) == null){ // 기존의 값이 존재하면
                List<String> tempList = new ArrayList<>();
                tempList.add(i.getInoculationName());

                inoculationList.put(toInoculationDate, tempList);
            } else {
                List<String> tempList = new ArrayList<>();
                tempList = inoculationList.get(toInoculationDate);
                tempList.add(i.getInoculationName());
                inoculationList.put(toInoculationDate, tempList);
            }
        }
        return inoculationList;
    }

    /**
     * MemberNumber로 접종이력들 확인하기.
     * @param memberNumber
     * @return
     */
    public List<InoculationMemberVO> findInoculationMember(int memberNumber){
        return (List<InoculationMemberVO>) inoculationMemberJPA.findAll();

    }

    /**
     * 접종 이력들 초기 세팅하기.
     */
    public List<InoculationMemberVO> setUpInoculationList(int memberNumber){
        // 아기 생일 알아내기
        BabyVO baby = babyJPA.findByMemberNumber(memberNumber);
        LocalDate babyBirth = baby.getBabyBirth();

        List<InoculationListVO> inoculations = (List<InoculationListVO>) inoculationJPA.findAll();
        List<InoculationMemberVO> inoculationMembers = new ArrayList<>();
        for (InoculationListVO i : inoculations){
            InoculationMemberVO tempMemberList = new InoculationMemberVO(i.getInoculationNumber(), memberNumber, babyBirth, i.getInoculationDate(), i.getInoculationPrieod());
            inoculationMembers.add(tempMemberList);
        }

        inoculationMemberJPA.saveAll(inoculationMembers);

        return inoculationMembers;
    }
}
