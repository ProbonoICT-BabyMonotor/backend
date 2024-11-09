package com.baby.monitor.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiveSensorDTO {
    private int memberNumber;
    private float bodyTemperature;
    private float temperature;
    private float humidity;
}
