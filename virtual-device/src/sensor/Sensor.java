package sensor;

import sensor.enums.SensorType;

public abstract class Sensor {
    private int id;
    private int sensorSensitivity;
    private final int DEFAULT_SENSOR_SENSITIVITY = 10;
    private boolean sensorON;
    private SensorType sensorType;


    protected Sensor(SensorType sensorType) {
        this.sensorType = sensorType;
        this.sensorSensitivity = this.DEFAULT_SENSOR_SENSITIVITY;
    }


    public void turnOn() {
        this.setSensorON(true);
        System.out.println("Turn on sensor: " + this.getSensorType());
    }

    public void turnOff() {
        this.setSensorON(false);
        System.out.println("Turn off sensor: " + this.getSensorType());
    }

    public abstract String getData();

    public boolean isSensorON() {
        return sensorON;
    }

    public void setSensorON(boolean sensorON) {
        this.sensorON = sensorON;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSensorSensitivity() {
        return sensorSensitivity;
    }

    public void setSensorSensitivity(int sensorSensitivity) {
        System.out.printf("Setting sensitivity to: %s for sensor: %s %n", sensorSensitivity, this.sensorType);
        this.sensorSensitivity = sensorSensitivity;
    }

    public int getDEFAULT_SENSOR_SENSITIVITY() {
        return DEFAULT_SENSOR_SENSITIVITY;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }
}
