package com.baby.monitor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inoculation_list")
public class InoculationListVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inoculationNumber;

    private String inoculationName;

    private int inoculationDate;

    private String inoculationPrieod;
}
