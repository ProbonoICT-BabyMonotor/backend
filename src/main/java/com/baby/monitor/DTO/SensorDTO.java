package com.baby.monitor.DTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SensorDTO {
    private String temperature;
    private String heartRate;
    private String breath;
    private String weight;
}
