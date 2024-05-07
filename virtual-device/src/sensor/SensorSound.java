package sensor;

import sensor.enums.SensorType;

import java.util.Random;

public class SensorSound extends Sensor {
    public SensorSound() {
        super(SensorType.SOUND);
    }


    @Override
    public String getData() {
        return this.generateData();
    }


    public String generateData() {
        Double dataGenerated = this.isSensorON() ? new Random().nextFloat(this.getSensorSensitivity()) + 1.0 : 0.0;
        dataGenerated = Math.floor(dataGenerated * 100) / 100;

//        System.out.println("Generate data sensor: " + this.getSensorType());
//        System.out.printf("Generated data with value: %s with sensitivity: %s%n", dataGenerated, this.getSensorSensitivity());

        return String.valueOf(dataGenerated);
    }
}
