package sensor;


import java.time.LocalDateTime;

public class SensorDTO {
    private int sensorSensitivity;
    private Boolean sensorON;
    private String data;
    private LocalDateTime dateGeneratedData;

    public LocalDateTime getDateGeneratedData() {
        return dateGeneratedData;
    }

    public void setDateGeneratedData(LocalDateTime dateGeneratedData) {
        this.dateGeneratedData = dateGeneratedData;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getSensorON() {
        return sensorON;
    }

    public void setSensorON(Boolean sensorON) {
        this.sensorON = sensorON;
    }

    public int getSensorSensitivity() {
        return sensorSensitivity;
    }

    public void setSensorSensitivity(int sensorSensitivity) {
        this.sensorSensitivity = sensorSensitivity;
    }
}
