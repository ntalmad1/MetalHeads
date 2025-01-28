package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.RoadrunnerDriveTrain;
import org.firstinspires.ftc.teamcode.metalheads.components.BigArm;
import org.firstinspires.ftc.teamcode.metalheads.components.LittleArm;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

/**
 *
 */
public abstract class CompBot extends IsaacBot {

    /**
     *
     */
    public enum ArmPos {
        INIT_READY,
        INIT,
        SAMPLE_PICK_READY,
        SAMPLE_PICK_DOWN,
        SAMPLE_PICK_UP,
        SAMPLE_RETRACTED,
        SAMPLE_EXTEND_READY,
        SAMPLE_DROP_HIGH_READY,
        SAMPLE_DROPPING_HIGH,
        SAMPLE_DROPPED_HIGH,
        SPECIMEN_PICK_READY,
        SPECIMEN_PICK,
        SPECIMEN_PLACE_HIGH_READY,
        SPECIMEN_PLACE_HIGH,
        HANG_READY,
        HANG
    }

    /**
     */
    protected BigArm bigArm;

    /**
     */
    protected LittleArm littleArm;

    /**
     */
    protected ArmPos armPos = ArmPos.INIT_READY;

    /**
     */
    protected CompBotConfig config;

    /**
     */
    private ActionFactory actionFactory;

    /**
     */
    public RoadrunnerDriveTrain driveTrain;

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

        this.config.debugDriveTrain = true;
        this.config.debugBigArm = true;
        this.config.debugLittleArm = true;
        this.config.debugAll = false;

        if (this.config.useDriveTrain) {
            this.driveTrain = new RoadrunnerDriveTrain(this.config.driveTrainConfig);
        }

        if (this.config.useBigArm) {
            this.bigArm = new BigArm(this.config.bigArmConfig);
        }

        if (this.config.useLittleArm) {
            this.littleArm = new LittleArm(this.config.littleArmConfig);
        }

        this.actionFactory = new ActionFactory(this);
    }

    /**
     *
     */
    @Override
    public void initBot(){
        super.initBot();

        if (this.config.useDriveTrain) {
            this.driveTrain.init(this.initialPose);
        }

        if (this.config.useBigArm) {
            this.bigArm.init();
        }

        if (this.config.useLittleArm) {
            this.littleArm.init();
        }

        ControlsConfigurator controlsConfigurator = new ControlsConfigurator(this);
        controlsConfigurator.configureGamePad1();
        controlsConfigurator.configureGamePad2();
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

        if (this.config.useBigArm) {
            this.bigArm.run(this.config.debugBigArm || this.config.debugAll);
        }

        if (this.config.useLittleArm) {
            this.littleArm.run(this.config.debugLittleArm || this.config.debugAll);
        }

        if (this.config.debugAll
            || this.config.debugDriveTrain
            || this.config.debugLittleArm
            || this.config.debugBigArm) {
            telemetry.update();
        }
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
    public MecanumDrive getDrive() {
        return this.driveTrain.getDrive();
    }

    /**
     *
     * @param pos
     */
    protected void setArmPos(ArmPos pos) {
        this.armPos = pos;
    }
}