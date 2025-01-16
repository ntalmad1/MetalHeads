package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoActionFactory;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToSampleDrop;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToSampleGrab;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToZero;

/**
 *
 */
@Autonomous(name = "RightObsBot_NEW", group = "Auto")
//@Disabled
public class RightObsBot_New extends AutoBot {

    private AutoActionFactory autoActionFactory;

    /**
     * Constructor
     *
     */
    public RightObsBot_New() {
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
        this.setInitialPose(new Pose2d(8, -61, Math.toRadians(90)));
    }

    /**
     *
     */
    @Override
    public void initBot() {
        super.initBot();
        this.telemetry.log().add("INIT DONE");

    }

    @Override
    public void go() {
        super.go();

        int initialHangExtraTicks = 8;
        int specimenCycleExtraTicks = 38;


        TrajectoryActionBuilder mainTrajectory = this.getDrive().actionBuilder(this.initialPose)


                .lineToY( -37)

                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(18,-50), 0)

                .splineToSplineHeading(new Pose2d(29.75, -33.77, Math.toRadians(34.3)), Math.toRadians(90))
                //.splineToLinearHeading(new Pose2d(29.75, -33.77, Math.toRadians(34.3)), Math.toRadians(90))



                .stopAndAdd(() -> {
                    this.littleArm.clawPincher.setPosition(Constants.SAMPLE_PICK_READY.clawPincherPos.getPos());
                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(0.84);
                })
                .stopAndAdd(new ViperSlideToSampleGrab(this.bigArm.viperSlide, 1))
                .afterTime(0.1, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_DOWN.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_DOWN.middleServoPos.getPos());
                })
                .waitSeconds(0.2)
                .stopAndAdd(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS))
                .waitSeconds(0.15)
                .afterTime(0.12, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_UP.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_UP.middleServoPos.getPos());
                })
                .afterTime(0.1, new ViperSlideToSampleDrop(this.bigArm.viperSlide, 1))




                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(33, -33.5, Math.toRadians(-55.7)), Math.toRadians(-90),
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-3, 15)
                )



                .waitSeconds(0.27)
                .afterTime(0, () -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS))
                .afterTime(0.15, new ViperSlideToSampleGrab(this.bigArm.viperSlide, 2))
                .waitSeconds(0.2)

                .stopAndAdd(() -> {
                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(0.9);
                })



                .setTangent(0)
                .splineToLinearHeading(new Pose2d(35, -37.5, Math.toRadians(37.8)), Math.toRadians(0),
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-3, 15)
                )



                .waitSeconds(0.1)
                .afterTime(0.1, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_DOWN.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_DOWN.middleServoPos.getPos());
                })
                .waitSeconds(0.2)
                .stopAndAdd(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS))
                .waitSeconds(0.15)
                .afterTime(0, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_UP.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_UP.middleServoPos.getPos());
                })
                .afterTime(0.1, new ViperSlideToSampleDrop(this.bigArm.viperSlide, 2))





                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(36.5, -34, Math.toRadians(-57)), Math.toRadians(-90),
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-3, 15)
                )





                .waitSeconds(0.2)
                .afterTime(0, () -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS))
                .afterTime(0, new ViperSlideToSampleGrab(this.bigArm.viperSlide, 3, -80))
                .waitSeconds(0.2)

                .stopAndAdd(() -> {
                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(0.9);
                })



                .setTangent(0)
                .splineToLinearHeading(new Pose2d(37.3, -32, Math.toRadians(22.3)), Math.toRadians(0),
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-3, 15)
                )
                .stopAndAdd(new ViperSlideToSampleGrab(this.bigArm.viperSlide, 3))




                .waitSeconds(0.1)
                .afterTime(0.1, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_DOWN.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_DOWN.middleServoPos.getPos());
                })
                .waitSeconds(0.2)
                .stopAndAdd(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS))
                .waitSeconds(0.15)
                .afterTime(0, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_UP.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_UP.middleServoPos.getPos());
                })
                .afterTime(0.1, new ViperSlideToZero(this.bigArm.viperSlide))





                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(45.25, -40, Math.toRadians(90)), Math.toRadians(-90))

                .lineToY( -57.25)



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
