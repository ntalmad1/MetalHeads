package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoActionFactory;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToPosition;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToPosition;

/**
 *
 */
@Autonomous(name = "RightObsBot_Test", group = "Auto")
//@Disabled
public class RightObsBot_Test extends AutoBot {

    private AutoActionFactory autoActionFactory;

    /**
     * Constructor
     *
     */
    public RightObsBot_Test() {
        super();

        this.setTrajectoryFactory(new RightObsTrajectoryFactory(this));

        this.setConfig(new RightObsBotConfig(this));
        this.configureBot();
    }

    /**
     *
     */
    @Override
    protected void configureBot() {
        super.configureBot();

        this.autoActionFactory = new AutoActionFactory(this);

        // initialize roadrunner from last op pose
        this.setInitialPose(new Pose2d(48, -59.8, Math.toRadians(90)));
    }

    /**
     *
     */
    @Override
    public void initBot() {
        super.initBot();

        this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
        this.bigArm.mainBoom.setTargetPosition(525);
        this.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.bigArm.mainBoom.setPower(1);
        this.setArmPos(ArmPos.INIT);
        this.telemetry.log().add("INIT DONE");

    }

    @Override
    public void go() {
        super.go();

        int initialHangExtraTicks = 8;
        int specimenCycleExtraTicks = 38;


        TrajectoryActionBuilder mainTrajectory = this.getDrive().actionBuilder(new Pose2d(48, -59.8, Math.toRadians(90)))

                .afterTime(0, autoActionFactory.specimenPlaceHighReady())
                .setTangent(Math.toRadians(170))
                .splineToLinearHeading(new Pose2d(10, -39.5, Math.toRadians(100)), Math.toRadians(100),
                        new TranslationalVelConstraint(40),
                        new ProfileAccelConstraint(-50, 45))
                .afterTime(0.0, new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS)))
                .afterTime(0.15, autoActionFactory.initPos())
                .lineToY(-45)




        ;
        Actions.runBlocking(mainTrajectory.build());

    }

    /**
     *
     * @return
     */
    protected RightObsTrajectoryFactory getTrajectoryFactory () {
        return (RightObsTrajectoryFactory)super.getTrajectoryFactory();
    }



}
