package sensor.strategy.enums.context;

import sensor.Sensor;
import sensor.strategy.enums.CommandSensorStrategyI;

public class CommandContext {

    private final CommandSensorStrategyI strategy;

    public CommandContext(CommandSensorStrategyI strategy) {
        this.strategy = strategy;
    }

    public void doCommand(Sensor sensor) {
        this.strategy.doCommand(sensor);
    }
}
