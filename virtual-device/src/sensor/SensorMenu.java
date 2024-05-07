package sensor;

import sensor.enums.SensorType;
import sensor.factory.SensorFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SensorMenu extends Thread {
    private Scanner in;
    private String hostAddress;


    public SensorMenu() throws UnknownHostException {
        hostAddress = InetAddress.getLocalHost().getHostAddress();
        in = new Scanner(System.in);
    }

    @Override
    public void run() {

        while (true) {

            try {
                handleMenu();
            } catch (InputMismatchException e) {
                in.nextLine();
            }

        }
    }

    private void handleMenu() {
        showMenuOptions();

        int chosenOption = in.nextInt();
        switch (chosenOption) {
            case 0:
                clearTerminal();
                break;
            case 1, 2, 3, 4:
                showMenuOptionsSensor(chosenOption);
                break;

            default:
                System.out.println("Option not valid!");
        }


    }


    private void showMenuOptions() {
        System.out.println("-----------------------------------------" + "\n" +
                "WELCOME TO SEVER SENSOR: " + hostAddress + "\n" +
                "-----------------------------------------" + "\n" +
                "ENTER THE DESIRED OPTION: " + "\n" +
                "[1] TURN ON SENSOR" + "\n" +
                "[2] TURN OFF SENSOR" + "\n" +
                "[3] SET SENSOR SENSITIVITY" + "\n" +
                "[4] GET SENSOR DATA" + "\n" +
                "-----------------------------------------");
    }

    private void showMenuOptionsSensor(int chosenOption) {
        clearTerminal();

        System.out.println("-----------------------------------------" + "\n" +
                "SENSOR CHOOSE" + "\n" +
                "-----------------------------------------" + "\n" +
                "ENTER THE DESIRED SENSOR: " + "\n" +
                "[1] SOUND" + "\n" +
                "[2] ANOTHER" + "\n" +
                "[3] GO BACK <---" + "\n" +
                "-----------------------------------------");


        int chosenOptionSensor = in.nextInt();
        switch (chosenOptionSensor) {
            case 1:
                handleChosenMenuSensorOption(chosenOption, SensorType.SOUND);
                break;
            case 2:
                System.out.println("Turning ON Sensor Another!");
                break;
            case 3:
                clearTerminal();
                break;
            default:
                System.out.println("Option not valid!");
        }
    }

    private void showMenuOptionsSetSensitivitySensor(Sensor sensor) {
        clearTerminal();
        System.out.println("---------------------------------------------------" + "\n" +
                "SET SENSIVITY" + "\n" +
                "---------------------------------------------------" + "\n" +
                "ENTER THE DESIRED SENSIVITY BETWEEN 1 AND 99: " + "\n" +
                "---------------------------------------------------");

        int value = in.nextInt();

        if (value >= 0 && value <= 99) {
            sensor.setSensorSensitivity(value);
        } else {
            System.out.println("Invalid value!");
        }

    }

    private void handleChosenMenuSensorOption(int chosenOptionSensor, SensorType sensorType) {
        if (chosenOptionSensor == 1) {
            Sensor sensor = SensorFactory.getSensor(sensorType);
            sensor.turnOn();

        } else if (chosenOptionSensor == 2) {
            Sensor sensor = SensorFactory.getSensor(sensorType);
            sensor.turnOff();

        } else if (chosenOptionSensor == 3) {
            showMenuOptionsSetSensitivitySensor(SensorFactory.getSensor(sensorType));


        } else if (chosenOptionSensor == 4) {
            Sensor sensor = SensorFactory.getSensor(sensorType);

            System.out.println("Generate data sensor: " + sensor.getSensorType());
            System.out.printf("Generated data with value: %s with sensitivity: %s%n", sensor.getData(), sensor.getSensorSensitivity());

        }
    }


    private void clearTerminal() {
        for (int i = 0; i < 27; i++) {
            System.out.println("\n");
        }
    }


}
