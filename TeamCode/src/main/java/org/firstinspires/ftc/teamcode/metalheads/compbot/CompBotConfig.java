package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.RoadrunnerDriveTrainConfig;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotorConfig;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.LittleArmConfig;

/**
 *
 */
public class CompBotConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public LittleArmConfig littleArmConfig;

    /**
     */
    public RoadrunnerDriveTrainConfig driveTrainConfig;

    /**
     */
    public boolean useDriveTrain = true;

    /**
     */
    public boolean useLittleArm = true;

    /**
     */
    public boolean debugAll = false;

    /**
     */
    public boolean debugDriveTrain = false;

    /**
     */
    public boolean debugLittleArm = false;

    /**
     * Constructor
     *
     * @param robot
     */
    public CompBotConfig(IsaacBot robot) {
        this.robot = robot;

        // driveTrain
        this.configureDriveTrain(robot);

        // little arm
        this.configureLittleArm(robot);
    }

    /**
     *
     * @param robot
     */
    private void configureLittleArm(IsaacBot robot) {
        this.littleArmConfig = new LittleArmConfig(robot);

        // double servo
        this.littleArmConfig.baseServoConfig = new ServoComponentConfig(robot);
        this.littleArmConfig.baseServoConfig.servoName = "base";
        this.littleArmConfig.baseServoConfig.maxIncrement = Constants.BASE_SERVO_INCREMENT;
        this.littleArmConfig.baseServoConfig.minPosition = 0;
        this.littleArmConfig.baseServoConfig.maxPosition = 0.74;
        this.littleArmConfig.baseServoConfig.homePosition = 0;
        this.littleArmConfig.baseServoConfig.zeroDegreePosition = 0.5;

        // claw rotator
        this.littleArmConfig.clawRotatorConfig = new ServoComponentConfig(robot);
        this.littleArmConfig.clawRotatorConfig.servoName = "end";
        this.littleArmConfig.clawRotatorConfig.homePosition = 0;
        this.littleArmConfig.clawRotatorConfig.minPosition = 0;
        this.littleArmConfig.clawRotatorConfig.maxPosition = 0.797;
        this.littleArmConfig.clawRotatorConfig.maxIncrement = Constants.CLAW_ROTATOR_INCREMENT;

        // claw pincher
        this.littleArmConfig.clawPincherConfig = new ServoComponentConfig(robot);
        this.littleArmConfig.clawPincherConfig.servoName = "claw";
        this.littleArmConfig.clawPincherConfig.homePosition = 0.4;
        this.littleArmConfig.clawPincherConfig.minPosition = 0.15;
        this.littleArmConfig.clawPincherConfig.maxPosition = 0.4;
    }

    /**
     *
     * @param robot
     */
    private void configureDriveTrain(IsaacBot robot) {
        this.driveTrainConfig = new RoadrunnerDriveTrainConfig(robot);
        this.driveTrainConfig.yawOffset = 0;
        this.driveTrainConfig.imuName = "imuExternal";
    }

}
