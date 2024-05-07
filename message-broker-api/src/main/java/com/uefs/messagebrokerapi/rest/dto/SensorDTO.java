package com.uefs.messagebrokerapi.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SensorDTO {
//    @NotNull(message = "{field.sensor-type.mandatory}")
//    private SensorType sensorType;
//
//    @NotNull(message = "{field.message-sensor-type.mandatory}")
//    private MessageSensorType messageSensorType;

    private Double data;

    private LocalDateTime dateGeneratedData;

    private Double sensitivity;

    private Boolean isON;

}
