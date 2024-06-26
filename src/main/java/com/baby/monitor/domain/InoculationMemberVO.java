package com.baby.monitor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name = "inoculation_member")
public class InoculationMemberVO {
    private static final Logger logger = LoggerFactory.getLogger(InoculationMemberVO.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inoculationMemberNumber;

    private int inoculationNumber;

    private int memberNumber;

    private boolean inoculationIstrue;

    private LocalDate inoculationDate;

    public InoculationMemberVO(int inoculationNumber, int memberNumber, LocalDate BabyBirth, int date, String prieod){
        this.inoculationNumber = inoculationNumber;
        this.memberNumber = memberNumber;

        if (inoculationNumber == 1){
            this.inoculationIstrue = true;
        } else {
            this.inoculationIstrue = false;
        }

        this.inoculationDate = setFutureDate(BabyBirth, date, prieod);

    }

    public InoculationMemberVO() {

    }

    public void setInoculationDate(String inoculationDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.inoculationDate = LocalDate.parse(inoculationDate, formatter);
    }

    public LocalDate setFutureDate(LocalDate babyBirth, int Date, String prieod){

        if (prieod == "week"){
            babyBirth = babyBirth.plusWeeks(Date);
        } else {
            babyBirth = babyBirth.plusMonths(Date);
        }
        logger.info(babyBirth.toString());
        logger.info(String.valueOf(Date));
        logger.info(prieod);
        return babyBirth;
    }

}
