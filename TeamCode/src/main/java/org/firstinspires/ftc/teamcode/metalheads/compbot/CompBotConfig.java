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

        // double servos
        this.littleArmConfig.baseServoConfig = new ServoComponentConfig(robot);
        this.littleArmConfig.baseServoConfig.servoName = "base";
        this.littleArmConfig.baseServoConfig.maxIncrement = Constants.BASE_SERVO_INCREMENT;
        this.littleArmConfig.baseServoConfig.minPosition = Constants.BASE_SERVO_MIN_POS;
        this.littleArmConfig.baseServoConfig.maxPosition = Constants.BASE_SERVO_MAX_POS;
        this.littleArmConfig.baseServoConfig.homePosition = Constants.BASE_SERVO_INIT_POS;
        this.littleArmConfig.baseServoConfig.zeroDegreePosition = 0.5;

        // middle servos
        this.littleArmConfig.middleServoConfig = new ServoComponentConfig(robot);
        this.littleArmConfig.middleServoConfig.servoName = "middle";
        this.littleArmConfig.middleServoConfig.homePosition = Constants.MIDDLE_SERVO_INIT_POS;
        this.littleArmConfig.middleServoConfig.zeroDegreePosition = 0.5;
        this.littleArmConfig.middleServoConfig.minPosition = Constants.MIDDLE_SERVO_MIN_POS;
        this.littleArmConfig.middleServoConfig.maxPosition = Constants.MIDDLE_SERVO_MAX_POS;
        this.littleArmConfig.middleServoConfig.maxIncrement = Constants.MIDDLE_SERVO_INCREMENT;

        // claw rotator
        this.littleArmConfig.clawRotatorConfig = new ServoComponentConfig(robot);
        this.littleArmConfig.clawRotatorConfig.servoName = "clawRotator";
        this.littleArmConfig.clawRotatorConfig.homePosition = Constants.CLAW_ROTATOR_90_DEG;
        this.littleArmConfig.clawRotatorConfig.zeroDegreePosition = Constants.CLAW_ROTATOR_0_DEG;
        this.littleArmConfig.clawRotatorConfig.minPosition = Constants.MIDDLE_SERVO_MIN_POS;
        this.littleArmConfig.clawRotatorConfig.maxPosition = Constants.CLAW_ROTATOR_MAX_POS;
        this.littleArmConfig.clawRotatorConfig.maxIncrement = Constants.CLAW_ROTATOR_INCREMENT;

        // claw pincher
        this.littleArmConfig.clawPincherConfig = new ServoComponentConfig(robot);
        this.littleArmConfig.clawPincherConfig.servoName = "claw";
        this.littleArmConfig.clawPincherConfig.homePosition = Constants.CLAW_PINCHER_CLOSE_POS;
        this.littleArmConfig.clawPincherConfig.zeroDegreePosition = 0.5;
        this.littleArmConfig.clawPincherConfig.minPosition = Constants.CLAW_PINCHER_OPEN_POS;
        this.littleArmConfig.clawPincherConfig.maxPosition = Constants.CLAW_PINCHER_CLOSE_POS;
        this.littleArmConfig.clawPincherConfig.lazyInit = false;
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
