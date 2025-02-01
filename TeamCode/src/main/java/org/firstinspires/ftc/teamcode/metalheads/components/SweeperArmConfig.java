package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;

/**
 *
 */
public class SweeperArmConfig {
    /**
     */
    public IsaacBot robot;

    /**
     */
    public ServoComponentConfig baseServoConfig;

    /**
     */
    public ServoComponentConfig middleServoConfig;

    /**
     */
    public ServoComponentConfig endServoConfig;

    /**
     * Constructor
     *
     * @param robot
     */
    public SweeperArmConfig(IsaacBot robot) {
        this.robot = robot;

        this.baseServoConfig = new ServoComponentConfig(robot);
        this.middleServoConfig = new ServoComponentConfig(robot);
        this.endServoConfig = new ServoComponentConfig(robot);
    }
}
