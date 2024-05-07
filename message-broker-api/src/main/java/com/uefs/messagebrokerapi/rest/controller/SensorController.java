package com.uefs.messagebrokerapi.rest.controller;

import com.uefs.messagebrokerapi.rest.dto.SensorDTO;
import com.uefs.messagebrokerapi.service.sensor.SensorServiceI;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensor")
@Api("Api Sensor")
public class SensorController {
    @Autowired
    private SensorServiceI sensorService;

    @PostMapping("/turnON")
    public void turnON(@RequestParam(name = "sensorType") String sensorType) {

        sensorService.turnON(sensorService.validateSensorTypeParameter(sensorType));
    }

    @PostMapping("/turnOFF")
    public void turnOFF(@RequestParam(name = "sensorType") String sensorType) {

        sensorService.turnOFF(sensorService.validateSensorTypeParameter(sensorType));
    }

    @PostMapping("/setSensitivity")
    public void setSensitivity(@RequestParam(name = "sensorType") String sensorType,
                               @RequestParam(name = "sensitivity") Integer sensitivity) {

        if (sensitivity <= 0 || sensitivity > 99) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, set a sensitivity between 1 and 99!");
        }

        sensorService.setSensitivity(sensorService.validateSensorTypeParameter(sensorType), sensitivity);
    }


    @GetMapping("/getSensorData")
    public SensorDTO getSensorData(@RequestParam(name = "sensorType") String sensorType) {

        return sensorService.getSensorData(sensorService.validateSensorTypeParameter(sensorType));
    }

    @GetMapping("/isON")
    public Boolean isON(@RequestParam(name = "sensorType") String sensorType) {

        return sensorService.isON(sensorService.validateSensorTypeParameter(sensorType));
    }


}
