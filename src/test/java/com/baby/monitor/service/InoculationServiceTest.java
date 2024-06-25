package com.baby.monitor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InoculationServiceTest {

    private final InoculationService inoculationService;

    @Autowired
    public InoculationServiceTest(InoculationService inoculationService){
        this.inoculationService = inoculationService;
    }

    @Test
    void setUpInoculationList() {
        inoculationService.setUpInoculationList(6);
    }
}