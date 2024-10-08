package org.firstinspires.ftc.teamcode.archive.library.winch;

import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.library.command.AbstractRepeatingCommand;

/**
 *
 */
public class ConstantPressureWinchCommand extends AbstractRepeatingCommand {

    /**
     */
    private Winch winch;

    /**
     */
    private TouchSensor sensor;

    /**
     */
    private int increment;

    /**
     */
    private double power;

    /**
     * Constructor
     *
     */
    public ConstantPressureWinchCommand (Winch winch, TouchSensor sensor, double power) {
        super ();

        this.winch = winch;
        this.sensor = sensor;
        this.power = power;
    }


    @Override
    public void init () {
        super.init();

        this.setInitialized(true);
    }


    @Override
    public void run() {

        if (this.sensor.isPressed()) {
            this.winch.stop();
        } else {
            this.winch.move(this.power);
        }
    }
}
