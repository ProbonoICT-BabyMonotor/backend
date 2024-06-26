package com.baby.monitor.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InoculationDTO {
    private int inoculationNumber;
    private String inoculationName;
    private Boolean inoculationIstrue;
    private LocalDate inoculationDate;

    public InoculationDTO(int inoculationNumber, String inoculationName, Boolean inoculationIstrue, LocalDate inoculationDate){
        this.inoculationNumber = inoculationNumber;
        this.inoculationName = inoculationName;
        this.inoculationIstrue = inoculationIstrue;
        this.inoculationDate = inoculationDate;
    }
}
