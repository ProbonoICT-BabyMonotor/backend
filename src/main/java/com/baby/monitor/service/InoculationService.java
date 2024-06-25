package com.baby.monitor.service;

import com.baby.monitor.DTO.InoculationDTO;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class InoculationService {

    private final InoculationListRepository inoculationJPA;
    private final InoculationMemberRepository inoculationMemberJPA;
    private final BabyRepository babyJPA;

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

    public List<InoculationDTO> changeTODTO(List<InoculationMemberVO> ListInoculation){
        // API 전송용 DTO로 변경
        List<InoculationDTO> listInoculationDTO = new ArrayList<>();
        int count = 1;
        for (InoculationMemberVO i : ListInoculation){
            String name = inoculationJPA.findByInoculationNumber(i.getInoculationNumber()).getInoculationName();
            InoculationDTO tempDTO = new InoculationDTO(count, name, i.isInoculationIstrue(), i.getInoculationDate());
            listInoculationDTO.add(tempDTO);
            count += 1;
        }
        return listInoculationDTO;
    }
}
