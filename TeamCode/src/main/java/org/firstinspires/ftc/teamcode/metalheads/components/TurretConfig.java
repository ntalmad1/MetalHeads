package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.dcmotor.DcMotorComponentConfig;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;

/**
 *
 */
public class TurretConfig {
    /**
     */
    public IsaacBot robot;

    /**
     */
    public EncodedMotorConfig launcherConfig;

    /**
     */
    public EncodedMotorConfig rotatorConfig;


    /**
     * Constructor
     *
     * @param robot
     */
    public TurretConfig(IsaacBot robot) {
        this.robot = robot;

        this.launcherConfig = new EncodedMotorConfig(robot);
        this.rotatorConfig = new EncodedMotorConfig(robot);
    }
}
