package com.baby.monitor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "baby") // 테이블 이름을 명시해줄 수 있습니다.
public class BabyVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int babyNumber;
    private int memberNumber;
    private String babyName;
    private LocalDate babyBirth;
    private Float babyWeight;

}
