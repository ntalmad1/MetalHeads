package org.firstinspires.ftc.library.servo;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.utility.Control;

/**
 *
 */
public class ServoConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public String servoName;

    /**
     */
    public boolean isDualServo = false;

    /**
     */
    public String secondaryServoName;

    /**
     */
    public Servo.Direction direction;

    /**
     */
    public Control controllerInputMethod = Control.NONE;

    public Control controllerInputMethod2 = Control.NONE;

    /**
     */
    public boolean invertInput = false;

    /**
     */
    public double minPosition = 0;

    /**
     */
    public double maxPosition = 1;

    /**
     */
    public double zeroDegreePosition;

    /**
     */
    public double homePosition;

    /**
     */
    public double maxIncrement = 0.001;

    /**
     */
    public double degree = 0.0033;

    /**
     */
    public double gearRatio = 1;

    /**
     */
    public boolean debug = false;

    /**
     * Constructor
     *
     * @param robot
     */
    public ServoConfig (IsaacBot robot) {
        this.robot = robot;
    }
}
