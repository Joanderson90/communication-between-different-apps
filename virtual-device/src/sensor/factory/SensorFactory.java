package sensor.factory;

import sensor.Sensor;
import sensor.SensorSound;
import sensor.enums.SensorType;

import java.util.HashMap;
import java.util.Map;

public final class SensorFactory {
    private static volatile Map<SensorType, Sensor> instanceMap = new HashMap<>();

    private SensorFactory() {
    }

    public static Sensor getSensor(SensorType sensorType) {

        switch (sensorType) {
            case SOUND -> {

                Sensor result = instanceMap.get(sensorType);
                if (result != null) {
                    return result;
                }

                synchronized (SensorSound.class) {
                    if (instanceMap.get(sensorType) == null) {
                        instanceMap.put(SensorType.SOUND, new SensorSound());
                    }

                    return instanceMap.get(sensorType);
                }

            }

            default -> {
                return new SensorSound();
            }
        }
    }
}
