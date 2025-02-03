package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.RoadrunnerDriveTrainConfig;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotorConfig;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;
import org.firstinspires.ftc.teamcode.metalheads.components.BigArmConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.LittleArmConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.SweeperArmConfig;

/**
 *
 */
public class CompBotConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public BigArmConfig bigArmConfig;

    /**
     */
    public LittleArmConfig littleArmConfig;

    /**
     */
    public SweeperArmConfig sweeperArmConfig;

    /**
     */
    public RoadrunnerDriveTrainConfig driveTrainConfig;

    /**
     */
    public boolean useDriveTrain = true;

    /**
     */
    public boolean useBigArm = true;

    /**
     */
    public boolean useLittleArm = true;

    /**
     */
    public boolean useSweeperArm = true;

    /**
     */
    public boolean debugAll = false;

    /**
     */
    public boolean debugDriveTrain = false;

    /**
     */
    public boolean debugBigArm = false;

    /**
     */
    public boolean debugLittleArm = false;

    public boolean debugSweeperArm = false;

    /**
     * Constructor
     *
     * @param robot
     */
    public CompBotConfig(IsaacBot robot) {
        this.robot = robot;

        // driveTrain
        this.configureDriveTrain(robot);

        // big arm
        this.configureBigArm(robot);

        // little arm
        this.configureLittleArm(robot);

        // sweeper arm
        this.configureSweeperArm(robot);
    }

    /**
     *
     * @param robot
     */
    private void configureBigArm(IsaacBot robot) {
        this.bigArmConfig = new BigArmConfig(robot);

        this.bigArmConfig.viperSlidesTouchSensorName = "viperSlideStopper";

        // main boom
        this.bigArmConfig.mainBoomConfig = new EncodedMotorConfig(robot);
        this.bigArmConfig.mainBoomConfig.motorName = "rightWorm";
        this.bigArmConfig.mainBoomConfig.brakeOn = false;
        this.bigArmConfig.mainBoomConfig.isDualMotor = true;
        this.bigArmConfig.mainBoomConfig.minTics = Constants.MAIN_BOOM_MIN_TICS;
        this.bigArmConfig.mainBoomConfig.maxTics = Constants.MAIN_BOOM_MAX_TICS;
        this.bigArmConfig.mainBoomConfig.scale = Constants.MAIN_BOOM_SCALE;
        this.bigArmConfig.mainBoomConfig.secondaryMotorName = "leftWorm";

        // viper slides
        this.bigArmConfig.viperSlideConfig = new PotentiometerMotorConfig(robot);
        this.bigArmConfig.viperSlideConfig.minTics = Constants.VIPER_SLIDES_MIN_TICS;
        this.bigArmConfig.viperSlideConfig.maxTics = Constants.VIPER_SLIDES_MAX_TICS;
        //this.bigArmConfig.viperSlideConfig.minVolts = Constants.VIPER_SLIDES_VOLTS_MIN;
        //this.bigArmConfig.viperSlideConfig.maxVolts = Constants.VIPER_SLIDES_VOLTS_MAX;
        this.bigArmConfig.viperSlideConfig.motorName = "rightSlide";
        this.bigArmConfig.viperSlideConfig.brakeOn = true;
        this.bigArmConfig.viperSlideConfig.initialMotorDirection = DcMotorSimple.Direction.REVERSE;
        this.bigArmConfig.viperSlideConfig.scale = Constants.VIPER_SLIDES_SCALE;
        this.bigArmConfig.viperSlideConfig.isDualMotor = true;
        this.bigArmConfig.viperSlideConfig.secondaryMotorName = "leftSlide";
        this.bigArmConfig.viperSlideConfig.secondaryInitialMotorDirection = DcMotorSimple.Direction.FORWARD;

       // this.bigArmConfig.viperSlideConfig.potentiometerConfig = new PotentiometerConfig(robot);
       // this.bigArmConfig.viperSlideConfig.potentiometerConfig.potentiometerName = "pot";
    }

    /**
     *
     * @param robot
     */
    private void configureLittleArm(IsaacBot robot) {
        this.littleArmConfig = new LittleArmConfig(robot);

        // double servos
        this.littleArmConfig.doubleServosConfig = new ServoComponentConfig(robot);
        this.littleArmConfig.doubleServosConfig.servoName = "leftBoom";
        this.littleArmConfig.doubleServosConfig.maxIncrement = Constants.DOUBLE_SERVOS_INCREMENT;
        this.littleArmConfig.doubleServosConfig.minPosition = Constants.DOUBLE_SERVOS_MIN_POS;
        this.littleArmConfig.doubleServosConfig.maxPosition = Constants.DOUBLE_SERVOS_MAX_POS;
        this.littleArmConfig.doubleServosConfig.homePosition = Constants.DOUBLE_SERVOS_INIT_POS;
        this.littleArmConfig.doubleServosConfig.direction = Servo.Direction.REVERSE;
        this.littleArmConfig.doubleServosConfig.zeroDegreePosition = 0.5;
        this.littleArmConfig.doubleServosConfig.isDualServo = true;
        this.littleArmConfig.doubleServosConfig.secondaryServoName = "rightBoom";

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
        this.littleArmConfig.clawRotatorConfig.homePosition = Constants.CLAW_ROTATOR_180_DEG;
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


    private void configureSweeperArm(IsaacBot robot) {
        this.sweeperArmConfig = new SweeperArmConfig(robot);

        // Base servo
        this.sweeperArmConfig.baseServoConfig = new ServoComponentConfig(robot);
        this.sweeperArmConfig.baseServoConfig.servoName = "sweeperBase";
        this.sweeperArmConfig.baseServoConfig.maxIncrement = 1;
        this.sweeperArmConfig.baseServoConfig.minPosition = Constants.SWEEPER_BASE_SERVO_CLOSED_POS;
        this.sweeperArmConfig.baseServoConfig.maxPosition = Constants.SWEEPER_BASE_SERVO_OPEN_POS;
        this.sweeperArmConfig.baseServoConfig.homePosition = Constants.SWEEPER_BASE_SERVO_CLOSED_POS;
        this.sweeperArmConfig.baseServoConfig.zeroDegreePosition = 0.5;

        // Middle Servo
        this.sweeperArmConfig.middleServoConfig = new ServoComponentConfig(robot);
        this.sweeperArmConfig.middleServoConfig.servoName = "sweeperMiddle";
        this.sweeperArmConfig.middleServoConfig.homePosition = Constants.SWEEPER_MIDDLE_SERVO_CLOSED_POS;
        this.sweeperArmConfig.middleServoConfig.zeroDegreePosition = 0.5;
        this.sweeperArmConfig.middleServoConfig.minPosition = Constants.SWEEPER_MIDDLE_SERVO_CLOSED_POS;
        this.sweeperArmConfig.middleServoConfig.maxPosition = Constants.SWEEPER_MIDDLE_SERVO_OPEN_POS;

        // End Servo
        this.sweeperArmConfig.endServoConfig = new ServoComponentConfig(robot);
        this.sweeperArmConfig.endServoConfig.servoName = "sweeperEnd";
        this.sweeperArmConfig.endServoConfig.homePosition = Constants.SWEEPER_END_SERVO_CLOSED_POS;
        this.sweeperArmConfig.endServoConfig.zeroDegreePosition = 0.5;
        this.sweeperArmConfig.endServoConfig.minPosition = Constants.SWEEPER_END_SERVO_CLOSED_POS;
        this.sweeperArmConfig.endServoConfig.maxPosition = Constants.SWEEPER_END_SERVO_OPEN_POS;
        this.sweeperArmConfig.endServoConfig.maxIncrement = 1;
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
