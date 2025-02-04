package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.HeadingPath;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PositionPath;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.QuinticSpline1d;
import com.acmerobotics.roadrunner.QuinticSpline2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoActionFactory;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToPosition;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToPosition;

/**
 *
 */
@Autonomous(name = "RightObsBot", group = "Auto")
//@Disabled
public class RightObsBot extends AutoBot {

    private AutoActionFactory autoActionFactory;

    /**
     * Constructor
     *
     */
    public RightObsBot() {
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

                .afterTime(0, new MainBoomToPosition(this.bigArm.mainBoom, Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos.getPos()))
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

                                })
                        )
                ))





                //extend sweeper
                .afterTime(0.8, autoActionFactory.sweeperOpen())


                //Retreat from Bar
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(24.6, -41.7, Math.toRadians(-120)), Math.toRadians(60))


                .waitSeconds(0.2)


                .turnTo(Math.toRadians(151))

                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(32, -37.9, Math.toRadians(-125)), Math.toRadians(55))

                .turnTo(Math.toRadians(145))

                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(40, -28.7, Math.toRadians(-160)), Math.toRadians(-32))


                //close sweeper
                .afterTime(0.7, autoActionFactory.sweeperClose())


                //Push Final Specimen
                .turnTo(Math.toRadians(146))


                // goto specimen pick ready
                .afterTime(0, autoActionFactory.specimenPickReady())

                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(48, -45, Math.toRadians(90)), Math.toRadians(-90))
                .lineToY(-59.8)
                .afterTime(0.0, new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)))
                .afterTime(0.18, autoActionFactory.specimenPlaceHighReady())




                //SPECIMEN #2
                .setTangent(Math.toRadians(170))
                .splineToLinearHeading(new Pose2d(10, -39.5, Math.toRadians(100)), Math.toRadians(100),
                        new TranslationalVelConstraint(40),
                        new ProfileAccelConstraint(-50, 45))
                .afterTime(0.0, new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS)))
                .afterTime(0.1, autoActionFactory.specimenPickReady())


                .setTangent(Math.toRadians(250))
                .splineToLinearHeading(new Pose2d(48, -59.5, Math.toRadians(90)), Math.toRadians(-90),
                        null,
                        new ProfileAccelConstraint(-20,55))
                .afterTime(0.08, new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)))
                .afterTime(0.18, autoActionFactory.specimenPlaceHighReady())


                //SPECIMEN #3
                .setTangent(Math.toRadians(170))
                .splineToLinearHeading(new Pose2d(10, -39.5, Math.toRadians(100)), Math.toRadians(100),
                        new TranslationalVelConstraint(40),
                        new ProfileAccelConstraint(-50, 45))
                .afterTime(0.0, new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS)))
                .afterTime(0.1, autoActionFactory.specimenPickReady())


                .setTangent(Math.toRadians(250))
                .splineToLinearHeading(new Pose2d(48, -59.5, Math.toRadians(90)), Math.toRadians(-90),
                        null,
                        new ProfileAccelConstraint(-20,55))
                .afterTime(0.08, new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)))
                .afterTime(0.18, autoActionFactory.specimenPlaceHighReady())



//                //Hang Specimen
//                .setTangent(Math.toRadians(140))
//                .splineToConstantHeading(new Vector2d(3,-60), Math.toRadians(180),
//                        new TranslationalVelConstraint(20))
//                .splineToConstantHeading(new Vector2d(3,-38), Math.toRadians(90),
//                        new TranslationalVelConstraint(17))
//
//
//
//
//                .afterTime(0, () -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS))
//
//                .afterTime(0.18, () -> this.littleArm.middleServo.setPosition((Constants.MIDDLE_SERVO_SPECIMEN_PLACED)))
//
//
//
//                .afterTime(0.5, new SequentialAction(
//
//                        new ViperSlideToZero(this.bigArm.viperSlide),
//
//                        new ParallelAction(
//                                new MainBoomToSpecimenPickReady(this.bigArm.mainBoom),
//
//                                new InstantAction(() -> {
//
//                                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
//                                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
//                                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
//
//                                })
//                        )
//                ))
//
//
//
//                //TODO:See if this changes anything
//                .waitSeconds(0.2)
//
//
//
//                //Retreat back to next Specimen
//                .setTangent(Math.toRadians(270))
//                .splineToConstantHeading(new Vector2d(24, -51), Math.toRadians(0))
//                .splineToConstantHeading(new Vector2d(42, -57.5), Math.toRadians(270),
//                        new TranslationalVelConstraint(18),
//                        new ProfileAccelConstraint(-60, 45)
//                )
//
//
//
//
//
////              Cycle Specimen (3rd total)
////              -----------------------------------------------------------------------------------------
//
//
//
//
//
//                .waitSeconds(0.25)
//
//                .afterTime(0.0, () -> {
//                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
//                })
//
//                .waitSeconds(0.15)
//
//
//                //Raise Up Boom, Then Extend ViperSLide
//                .afterTime(0, new SequentialAction(
//
//                        new MainBoomToSpecimenHighReady(this.bigArm.mainBoom),
//                        new WaitAction(0.3),
//
//                        new ParallelAction(
//
//                                new InstantAction(() -> {
//                                    this.telemetry.log().add("3.1 Left MainBoom: " + this.bigArm.mainBoom.getSecondaryMotor().getCurrentPosition());
//                                    this.telemetry.log().add("3.1 Right MainBoom: " + this.bigArm.mainBoom.getMotor().getCurrentPosition());
//                                    this.telemetry.update();
//                                }),
//
//                                new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide, specimenCycleExtraTicks),
//
//                                new InstantAction(() -> {
//
//                                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
//                                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
//                                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());
//
//                                }),
//
//                                new InstantAction(() -> {
//                                    this.telemetry.log().add("3.2 Left MainBoom: " + this.bigArm.mainBoom.getSecondaryMotor().getCurrentPosition());
//                                    this.telemetry.log().add("3.2 Right MainBoom: " + this.bigArm.mainBoom.getMotor().getCurrentPosition());
//                                    this.telemetry.update();
//                                })
//                        )
//                ))
//
//
//
//
//                //Hang Specimen
//                .setTangent(Math.toRadians(140))
//                .splineToConstantHeading(new Vector2d(15,-60), Math.toRadians(180),
//                        new TranslationalVelConstraint(20))
//                .splineToConstantHeading(new Vector2d(3,-38), Math.toRadians(90),
//                        new TranslationalVelConstraint(17))
//
//
//
//
//
//                .afterTime(0, () -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS))
//
//                .afterTime(0.18, () -> this.littleArm.middleServo.setPosition((Constants.MIDDLE_SERVO_SPECIMEN_PLACED)))
//
//
//
//                .afterTime(0.5, new SequentialAction(
//
//                        new ViperSlideToZero(this.bigArm.viperSlide),
//
//                        new ParallelAction(
//                                new MainBoomToZero(this.bigArm.mainBoom),
//
//                                new InstantAction(() -> {
//
//                                    this.littleArm.middleServo.setPosition(Constants.MIDDLE_SERVO_INIT_POS);
//                                    this.littleArm.doubleServos.setPosition(Constants.DOUBLE_SERVOS_INIT_POS);
//                                    this.littleArm.clawRotator.setPosition(Constants.CLAW_ROTATOR_INIT_POS);
//                                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
//
//                                })
//                        )
//                ))
//
//
//                //TODO:See if this changes anything
//                .waitSeconds(0.2)
//
//
////                //Retreat back to next Specimen
////                .setTangent(Math.toRadians(270))
////                .splineToConstantHeading(new Vector2d(24, -51), Math.toRadians(0))
////                .splineToConstantHeading(new Vector2d(40, -57.5), Math.toRadians(270),
////                        new TranslationalVelConstraint(18),
////                        new ProfileAccelConstraint(-60, 45)
////                )
//
//                //Retreat back to observation zone
//                .setTangent(Math.toRadians(270))
//                .splineToConstantHeading(new Vector2d(24, -51), Math.toRadians(0),
//                        new TranslationalVelConstraint(25))
//                .splineToConstantHeading(new Vector2d(44, -52), Math.toRadians(270),
//                        new TranslationalVelConstraint(20))
//
//                .waitSeconds(80)
//









//              //TODO: Cycle Specimen (4th total)
//              -----------------------------------------------------------------------------------------





//              //TODO: Cycle Specimen (5th total)
//              -----------------------------------------------------------------------------------------




















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
