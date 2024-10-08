package com.baby.monitor.persistance;

import com.baby.monitor.domain.ActingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
public class ActingJPATest {
    private final ActingRepository actingJPA;

    @Test
    public void actingTest(){
        ActingVO acting = actingJPA.findFirstByMemberNumberOrderByActingNumberDesc(9);
        log.info(String.valueOf(acting.getActingNumber()));
    }
}
