package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.TurnConstraints;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToPosition;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToPosition;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.WaitMilliseconds;

/**
 *
 */
@Autonomous(name = "Specimens", group = "Auto")
//@Disabled
public class SpecimensAuto extends AutoBot {

    private org.firstinspires.ftc.teamcode.metalheads.compbot.AutoActionFactory autoActionFactory;

    /**
     * Constructor
     *
     */
    public SpecimensAuto() {
        super();

        this.setTrajectoryFactory(new AutoActionFactory(this));

        this.setConfig(new SpecimenConfig(this));
        this.configureBot();
    }

    /**
     *
     */
    @Override
    protected void configureBot() {
        super.configureBot();

        this.autoActionFactory = new org.firstinspires.ftc.teamcode.metalheads.compbot.AutoActionFactory(this);

        // initialize roadrunner from last op pose
        this.setInitialPose(new Pose2d(8, -61, Math.toRadians(90)));
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


        TrajectoryActionBuilder mainTrajectory = this.getDrive().actionBuilder(this.initialPose)

//              Arm -> Specimen High Ready
//              ----------------------------------------------------------------------------------------------

                .afterTime(0, new MainBoomToPosition(this.bigArm.mainBoom, (Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos.getPos() - 15)))
                .afterTime(0.3, new ViperSlideToPosition(this.bigArm.viperSlide, Constants.SPECIMEN_PLACE_HIGH_READY.vSlidePos.getPos()))

//                .afterTime(0, new SequentialAction(
//                        new MainBoomToPosition(this.bigArm.mainBoom, Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos.getPos(), false),
//                        new ViperSlideToPosition(this.bigArm.viperSlide, Constants.SPECIMEN_PLACE_HIGH_READY.vSlidePos.getPos())
//                ))

                //SPECIMEN PLACE HIGH READY (Servos)
                .afterTime(0.2, () -> {

                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());

                })


//              Hang Specimen
//              ----------------------------------------------------------------------------------------------


                //Go To bar
                .lineToY(-37,
                        new TranslationalVelConstraint(50),
                        new ProfileAccelConstraint(-60, 20))


                //Open Claw to release Specimen
                .afterTime(0, () -> {
                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS);
                    this.littleArm.middleServo.setPosition(Constants.MIDDLE_SERVO_SPECIMEN_PLACED);
                })
                //Arm -> init Position
                .afterTime(0.4, new SequentialAction(
                        new ViperSlideToPosition(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS, true, 200),
                        new ParallelAction(
                                new MainBoomToPosition(this.bigArm.mainBoom, Constants.MAIN_BOOM_MIN_TICS),
                                new InstantAction(() -> {
                                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                                    this.littleArm.clawPincher.setPosition(Constants.SAMPLE_PICK_READY.clawPincherPos.getPos());
                                    this.sweeperArm.specimenBrace.setPosition(1);

                                })
                        )
                ))

                .afterTime(0.8, new InstantAction(() -> this.littleArm.doubleServos.setPosition(Constants.DOUBLE_SERVOS_INIT_POS)))

                // -------------------------------------------------------------
                .waitSeconds(0.65)
                // -------------------------------------------------------------





                //extend sweeper
                .afterTime(0.79, autoActionFactory.sweeperOpen())


                //Retreat From Bar
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(24.6, -41.7, Math.toRadians(-120)), Math.toRadians(60))


                .waitSeconds(0.25)

                .afterTime(0.8, new InstantAction(() -> this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos())))


                .turnTo(Math.toRadians(158),
                        new TurnConstraints(9,-4, 8))
                .turnTo(Math.toRadians(-125),
                        new TurnConstraints(9,-8, 8))


                .setTangent(Math.toRadians(10))
                .splineToLinearHeading(new Pose2d(32, -37.9, Math.toRadians(-125)), Math.toRadians(55))


                .turnTo(Math.toRadians(155),
                        new TurnConstraints(9,-4, 8))
                .turnTo(Math.toRadians(-133),
                        new TurnConstraints(9,-8, 8))


                .setTangent(Math.toRadians(120))
                //.splineToLinearHeading(new Pose2d(42, -36.5, Math.toRadians(-160)), Math.toRadians(-32))
                .splineToLinearHeading(new Pose2d(40.2, -35.5, Math.toRadians(-133)), Math.toRadians(-32))


                //close sweeper
                .afterTime(0.7, autoActionFactory.sweeperClose())


                //Push Final Specimen
                .turnTo(Math.toRadians(165),
                        new TurnConstraints(9,-4, 8))


                // goto specimen pick ready
                .afterTime(0, autoActionFactory.specimenPickReady())

                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(48.5, -60, Math.toRadians(86)), Math.toRadians(-90),
                        null,
                        new ProfileAccelConstraint(-25, 60))
                .afterTime(0.05, new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)))
                .waitSeconds(0.12)
                .afterTime(0.05, autoActionFactory.specimenPlaceHighReady())




                //SPECIMEN #2
                .setTangent(Math.toRadians(170))
                .splineToLinearHeading(new Pose2d(10, -39.5, Math.toRadians(100)), Math.toRadians(100),
                        new TranslationalVelConstraint(40),
                        new ProfileAccelConstraint(-50, 30))
                .afterTime(0.08, new SequentialAction(
                        new InstantAction(() -> {
                            this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS);
                            this.littleArm.middleServo.setPosition(Constants.MIDDLE_SERVO_SPECIMEN_PLACED);
                        }),
                        new WaitMilliseconds(400),
                        new ViperSlideToPosition(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS),
                        new ParallelAction(
                                new MainBoomToPosition(this.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS),
                                new InstantAction(() -> {
                                        this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                                        this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                                        this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                                })
                        )
                ))
                .waitSeconds(0.450)


                .setTangent(Math.toRadians(250))
                .splineToLinearHeading(new Pose2d(49.7, -59.6, Math.toRadians(90)), Math.toRadians(-90),
                        null,
                        new ProfileAccelConstraint(-20,60))
                .afterTime(0.05, new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)))
                .waitSeconds(0.12)
                .afterTime(0.05, autoActionFactory.specimenPlaceHighReady())





                //SPECIMEN #3
                .setTangent(Math.toRadians(170))
                .splineToLinearHeading(new Pose2d(10, -39.5, Math.toRadians(100)), Math.toRadians(100),
                        new TranslationalVelConstraint(40),
                        new ProfileAccelConstraint(-50, 30))
                .afterTime(0.08, new SequentialAction(
                        new InstantAction(() -> {
                            this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS);
                            this.littleArm.middleServo.setPosition(Constants.MIDDLE_SERVO_SPECIMEN_PLACED);
                        }),
                        new WaitMilliseconds(400),
                        new ViperSlideToPosition(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS),
                        new ParallelAction(
                                new MainBoomToPosition(this.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS),
                                new InstantAction(() -> {
                                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                                })
                        )
                ))
                .waitSeconds(0.450)


                .setTangent(Math.toRadians(250))
                .splineToLinearHeading(new Pose2d(49.8, -59.6, Math.toRadians(90)), Math.toRadians(-90),
                        null,
                        new ProfileAccelConstraint(-20,60))
                .afterTime(0.05, new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)))
                .waitSeconds(0.12)
                .afterTime(0.05, autoActionFactory.specimenPlaceHighReady())





                //SPECIMEN #4
                .setTangent(Math.toRadians(170))
                .splineToLinearHeading(new Pose2d(10, -39.5, Math.toRadians(100)), Math.toRadians(100),
                        new TranslationalVelConstraint(40),
                        new ProfileAccelConstraint(-50, 30))
                .afterTime(0.08, new SequentialAction(
                        new InstantAction(() ->
                        {
                            this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS);
                            this.littleArm.middleServo.setPosition(Constants.MIDDLE_SERVO_SPECIMEN_PLACED);
                        }),
                        new WaitMilliseconds(400),
                        new ViperSlideToPosition(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS),
                        new ParallelAction(
                                new MainBoomToPosition(this.bigArm.mainBoom, Constants.MAIN_BOOM_MIN_TICS),
                                new InstantAction(() -> {
                                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
                                    this.littleArm.clawRotator.setPosition(Constants.CLAW_ROTATOR_INIT_POS);
                                    this.littleArm.middleServo.setPosition(Constants.MIDDLE_SERVO_INIT_POS);
                                    this.littleArm.doubleServos.setPosition(Constants.DOUBLE_SERVOS_INIT_POS);
                                })
                        )
                ))
                .waitSeconds(0.450)


                .setTangent(Math.toRadians(250))
                .splineToLinearHeading(new Pose2d(49.4, -58, Math.toRadians(90)), Math.toRadians(-90),
                        null,
                        new ProfileAccelConstraint(-80,60))







        ;
        Actions.runBlocking(mainTrajectory.build());

    }

    /**
     *
     * @return
     */
    protected AutoActionFactory getTrajectoryFactory () {
        return (AutoActionFactory)super.getTrajectoryFactory();
    }



}
