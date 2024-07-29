package com.baby.monitor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stm32")
public class Stm32VO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stm32Number;

    private String stm32Ip;

    private int memberNumber;
}
