package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.Pose2d;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.RoadrunnerDriveTrain;
import org.firstinspires.ftc.teamcode.metalheads.components.LittleArm;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

/**
 *
 */
public abstract class CompBot extends IsaacBot {

    /**
     */
    protected LittleArm littleArm;

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
        this.config.debugLittleArm = true;
        this.config.debugAll = false;

        if (this.config.useDriveTrain) {
            this.driveTrain = new RoadrunnerDriveTrain(this.config.driveTrainConfig);
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

        if (this.config.useLittleArm) {
            this.littleArm.run(this.config.debugLittleArm || this.config.debugAll);
        }


        if (this.config.debugAll
            || this.config.debugDriveTrain
            || this.config.debugLittleArm) {
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
     * @param config
     */
    public void setConfig(CompBotConfig config) {
        this.config = config;
    }

    /**
     *
     * @return
     */
    public MecanumDrive getDrive() {
        return this.driveTrain.getDrive();
    }
}