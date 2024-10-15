package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.library.servo.ServoPos;
import org.firstinspires.ftc.teamcode.metalheads.components.Arm;
import org.firstinspires.ftc.teamcode.metalheads.components.Claw;
import org.firstinspires.ftc.teamcode.metalheads.components.DoubleHooks;
import org.firstinspires.ftc.teamcode.metalheads.components.FlapperBars;
import org.firstinspires.ftc.teamcode.metalheads.components.Intake;
import org.firstinspires.ftc.teamcode.metalheads.components.Winch;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

/**
 *
 */
public abstract class CompBot extends IsaacBot {

    /**
     *
     */
    public enum Mode {
        NONE,
        SAMPLE_MODE,
        SPECIMEN_MODE
    }

    /**
     *
     */
    public enum ArmPos {
        INIT
    }

    /**
     */
    protected Arm arm;

    /**
     */
    protected ArmPos armPos = ArmPos.INIT;

    /**
     */
    protected Mode mode = Mode.NONE;


    /**
     */
    protected CompBotConfig config;

    /**
     */
    private ActionFactory actionFactory;
    /**
     */
    private Claw claw;

    /**
     */
    private DoubleHooks doubleHooks;

    /**
     */
    private MecanumDriveTrain driveTrain;
    /**
     */
    private FlapperBars flapperBars;

    /**
     */
    private Intake intake;

    /**
     */
    private MecanumDrive roadrunner;


    /**
     */
    private Winch winch;


    /**
     */
    protected Pose2d initialPose;

    /**
     * Constructor
     *
     */
    public CompBot(){
        super();
    }


    /**
     * Constructor
     *
     * @param compBotConfig
     */
    public CompBot(CompBotConfig compBotConfig) {
        super();

        this.config = compBotConfig;
        this.configureBot();
    }

    /**
     *
     */
    protected void configureBot() {
        if (this.getConfig() == null) {
            this.config = new CompBotConfig(this);
        }

        this.config.debugDriveTrain = false;
        this.config.debugArm = true;
        this.config.debugClaw = true;
        this.config.debugDoubleHooks = true;
        this.config.debugFlapperBars = true;
        this.config.debugIntake = true;
        this.config.debugWinch = true;
        this.config.debugRoadrunner = true;

        this.config.debugAll = false;

        if (this.config.useDriveTrain) {
            this.driveTrain = new MecanumDriveTrain(this.config.driveTrainConfig);
        }

        if (this.config.useArm) {
            this.arm = new Arm(this.config.armConfig);
        }

        if (this.config.useClaw) {
            this.claw = new Claw(this.config.clawConfig);
        }

        if (this.config.useDoubleHooks) {
            this.doubleHooks = new DoubleHooks(this.config.doubleHooksConfig);
        }

        if (this.config.useFlapperBars) {
            this.flapperBars = new FlapperBars(this.config.flapperBarsConfig);
        }

        if (this.config.useIntake) {
            this.intake = new Intake(this.config.intakeConfig);
        }

        if (this.config.useWinch) {
            this.winch = new Winch(this.config.winchConfig);
        }

        this.actionFactory = new ActionFactory();
    }

    /**
     *
     */
    @Override
    public void initBot(){
        super.initBot();

        roadrunner = new MecanumDrive(hardwareMap, initialPose);

        if (this.config.useDriveTrain) {
            this.driveTrain.init();
        }

        if (this.config.useArm) {
            this.arm.init();
        }

        if (this.config.useClaw) {
            this.claw.init();
            this.claw.pincher.setPosition(config.clawConfig.pincherConfig.homePosition);
        }

        if (this.config.useDoubleHooks) {
            this.doubleHooks.init();
        }

        if (this.config.useFlapperBars) {
            this.flapperBars.init();
        }

        if (this.config.useIntake) {
            this.intake.init();
        }

        if (this.config.useWinch) {
            this.winch.init();
        }

        this.configureGamePad1();
        this.configureGamePad2();
    }

    /**
     *
     */
    private void configureGamePad1() {

        // Panic Button / Kill Switch
        this.addGp1_Back_PressHandler(event -> {
            this.terminateOpModeNow();
        });

        this.addGp1_Left_Bumper_DownHandler(event -> {
            flapperBars.move(-1);
        });

        this.addGp1_Left_Bumper_UpHandler(event -> {
            flapperBars.move(0);
        });

        this.addGp1_Right_Bumper_DownHandler(event -> {
            flapperBars.move(1);
        });

        this.addGp1_Right_Bumper_UpHandler(event -> {
            flapperBars.move(0);
        });

        this.addGp1_RightTrigger_DownHandler(event -> {
            winch.move(1);
        });

        this.addGp1_RightTrigger_UpHandler(event -> {
            winch.move(0);
        });

        this.addGp1_LeftTrigger_DownHandler(event -> {
            winch.move(-1);
        });

        this.addGp1_LeftTrigger_UpHandler(event -> {
            winch.move(0);
        });

    }

    /**
     *
     */
    private void configureGamePad2() {

        // Panic Button / Kill Switch
        this.addGp2_Back_PressHandler(event -> {
            this.terminateOpModeNow();
        });

//        this.addGp2_A_PressHandler(event -> {
//            runAction(actionFactory.moveArmToSamplePickReady());
//        });
//
//        this.addGp2_B_PressHandler(event -> {
//            runAction(actionFactory.moveArmToInitPos());
//        });


//        this.addGp2_Right_Bumper_PressHandler(event -> {
//            claw.pincher.setPosition(config.clawConfig.pincherConfig.minPosition);
//        });
//
//        this.addGp2_Left_Bumper_PressHandler(event -> {
//            claw.pincher.setPosition(config.clawConfig.pincherConfig.maxPosition);
//        });

    }

    /**
     *
     */
    @Override
    public void go (){

    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        if (this.config.useDriveTrain) {
            this.driveTrain.run(this.config.debugDriveTrain || this.config.debugAll);
        }

        if (this.config.useArm) {
            this.arm.run(this.config.debugArm || this.config.debugAll);
        }

        if (this.config.useClaw) {
            this.claw.run(this.config.debugClaw || this.config.debugAll);
        }

        if (this.config.useDoubleHooks) {
            this.doubleHooks.run(this.config.debugDoubleHooks || this.config.debugAll);
        }

        if (this.config.useFlapperBars) {
            this.flapperBars.run(this.config.debugFlapperBars || this.config.debugAll);
        }

        if (this.config.useIntake) {
            this.intake.run(this.config.debugIntake || this.config.debugAll);
        }

        if (this.config.useWinch) {
            this.winch.run(this.config.debugWinch || this.config.debugAll);
        }

        if (this.config.debugRoadrunner && this.roadrunner != null) {

            telemetry.addData("Roadrunner x", this.roadrunner.pose.position.x);
            telemetry.addData("Roadrunner y", this.roadrunner.pose.position.y);
            telemetry.addData("Roadrunner heading (deg)", Math.toDegrees(this.roadrunner.pose.heading.toDouble()));
        }

        if (this.config.debugAll
            || this.config.debugDriveTrain
            || this.config.debugArm
            || this.config.debugClaw
            || this.config.debugDoubleHooks
            || this.config.debugFlapperBars
            || this.config.debugIntake
            || this.config.debugWinch
            || this.config.debugRoadrunner) {
            telemetry.update();
        }

        this.terminateOpModeNow();
    }

    /**
     *
     */
    @Override
    public void onStop() {

    }

    /**
     *
     * @return
     */
    public MecanumDrive getRoadrunner() {
        return this.roadrunner;
    }

    /**
     *
     * @return
     */
    public CompBotConfig getConfig() {
        return this.config == null ? null : this.config;
    }

    /**
     *
     * @return
     */
    public ActionFactory getActionFactory() {
        return this.actionFactory;
    }

    /**
     *
     * @param pos
     */
    public void setInitialPose(Pose2d pos) {
        this.initialPose = pos;
    }

    /**
     *
     * @param config
     */
    public void setConfig(CompBotConfig config) {
        this.config = config;
    }

    /**
     *
     * @return
     */
    protected ArmPos getArmPos() {
        return this.armPos;
    }

    /**
     *
     * @return
     */
    protected Mode getMode() {
        return this.mode;
    }

    /**
     *
     * @param positions
     * @return
     */
    protected ParallelAction moveArmAction(PositionConstants positions) {

        return new ParallelAction(
                intake.hServo.gotoPositionAction(positions.hServoPos),
                intake.vServo.gotoPositionAction(positions.vServoPos),
                intake.pincher.gotoPositionAction(positions.intakePincherPos),
                arm.viperSlide.gotoVoltageAction(positions.vSlideVolts),
                arm.mainBoom.gotoPositionAction(positions.mainBoomPos),
                claw.clawRotator.gotoPositionAction(positions.clawRotatorPos),
                claw.pincher.gotoPositionAction(positions.clawPincherPos)
        );

    }

    /**
     *
     * @param pos
     */
    protected void setArmPos(ArmPos pos) {
        this.armPos = pos;
    }

    /**
     *
     * @param mode
     */
    protected void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * Internal Class
     */
    public class ActionFactory {

        public Action moveArmToInitPos() {
            return new SequentialAction(
                    CompBot.this.arm.mainBoom.gotoPositionAction(0, 0.5),
                    new ParallelAction(
                            CompBot.this.intake.hServo.gotoPositionAction(0.5011, 1),
                            CompBot.this.intake.vServo.gotoPositionAction(0.5, 1),
                            CompBot.this.claw.closeClawAction(),
                            CompBot.this.claw.clawRotator.gotoPositionAction(0, 1)
                    ),
                    new WaitAction(1000),
                    CompBot.this.arm.viperSlide.gotoVoltageAction(0.586)
            );
        }


        /**
         * @return
         */
        public Action moveArmToSamplePickReady() {
            return new ParallelAction(
                            intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.hServoPos),
                            intake.vServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.vServoPos),
                            arm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY.vSlideVolts),
                            new WaitAction(1000),
                            intake.openPincherAction(),
                            arm.mainBoom.gotoPositionAction(Constants.SAMPLE_PICK_READY.mainBoomPos.getPos(), 0.5));
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickLeftReady() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PICK_LEFT_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickRightReady() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PICK_RIGHT_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickReadyMax() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PICK_READY_MAX);
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickLeftReadyMax() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PICK_LEFT_READY_MAX);
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickRightReadyMax() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PICK_RIGHT_READY_MAX);
        }

        /**
         * @return
         */
        public Action moveArmToSampleCarry() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_CARRY);
        }

        /**
         * @return
         */
        public Action moveArmToSampeDropExtendReady() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_DROP_EXTEND_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSampleBasketLowReady() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_BASKET_LOW_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSampleBasketHighReady() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_BASKET_HIGH_READY);
        }

        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------

        /**
         * @return
         */
        public Action moveArmToSpecimenPickready() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PICK_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSpecimenPick() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PICK);
        }

        /**
         * @return
         */
        public Action moveArmToSpecimenPlaceHighReady() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PLACE_HIGH_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSpecimenPlaceHigh() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PLACE_HIGH);
        }

        /**
         * @return
         */
        public Action moveArmToSpecimentPlaceLowready() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PLACE_LOW_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSpecimenPlaceLow() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PLACE_LOW);
        }



/**
         * Hidden constructor to make class static
         */
        protected ActionFactory() {
        }
    }

    /**
     * Internal Class
     */
    public static abstract class PositionConstants {

        public ServoPos hServoPos;

        public ServoPos vServoPos;

        public ServoPos intakePincherPos;

        public Double vSlideVolts;

        public MotorPos mainBoomPos;

        public ServoPos clawRotatorPos;

        public ServoPos clawPincherPos;

        public PositionConstants () {

        }

        /**
         *
         */
        public abstract void setValues();
    }
}