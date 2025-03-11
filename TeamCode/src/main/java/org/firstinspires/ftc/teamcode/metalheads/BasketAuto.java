package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoActionFactory;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutonomousData;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MotorToPositionRR;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.WaitForMotor;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.WaitMilliseconds;

/**
 *
 */
@Autonomous(name = "Basket", group = "Auto")
//@Disabled
public class BasketAuto extends AutoBot {

    private AutoActionFactory autoActionFactory;

    /**
     * Constructor
     *
     */
    public BasketAuto() {
        super();

        this.setTrajectoryFactory(new org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.AutoActionFactory(this));

        this.setConfig(new BasketConfig(this));
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
        this.setInitialPose(new Pose2d(-39, -61, Math.toRadians(90)));
    }

    /**
     *
     */
    @Override
    public void initBot() {
        super.initBot();

        AutonomousData.yawOffset = 90;

        this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
        this.bigArm.mainBoom.setTargetPosition(Constants.MAIN_BOOM_MAX_TICS);
        this.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.bigArm.mainBoom.setPower(1);
        this.sleep(3000);
        this.bigArm.mainBoom.setPower(0);
        this.telemetry.log().add("INIT DONE");

    }

    @Override
    public void go() {
        super.go();

        this.bigArm.mainBoom.setPower(0);


        TrajectoryActionBuilder mainTrajectory = this.getDrive().actionBuilder(this.initialPose)

                //region Preloaded
                .afterTime(0.3, new ParallelAction(
                        new MotorToPositionRR(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MAX_TICS, false),
                        new InstantAction(() -> {
                            this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.doubleServosPos.getPos());
                            this.littleArm.clawRotator.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.clawRotatorPos.getPos());
                            this.littleArm.middleServo.setPosition(0.263);
                        })
                ))

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-55, -55, Math.toRadians(45)), Math.toRadians(225),
                        null,
                        new ProfileAccelConstraint(-45, 60))

                .stopAndAdd(new SequentialAction(
                        new WaitForMotor(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MAX_TICS, 15),
                        new InstantAction(() -> this.littleArm.middleServo.setPosition(0)),
                        new WaitMilliseconds(120),
                        new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS)),
                        new WaitMilliseconds(120),
                        new InstantAction(() -> {
                            this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                            this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                            this.littleArm.clawRotator.setPosition(Constants.SAMPLE_PICK_READY.clawRotatorPos.getPos());
                        }),
                        new WaitMilliseconds(250),
                        new MotorToPositionRR(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS)
                ))

                .afterTime(0, new MotorToPositionRR(this.bigArm.mainBoom, Constants.MAIN_BOOM_MIN_TICS))
                //endregion

                //region First Sample
                //----------------------------------------------------------------------------------------------------
                .setTangent(Math.toRadians(45))
                .splineToLinearHeading(new Pose2d(-49.4, -37, Math.toRadians(92)), Math.toRadians(90),
                        new TranslationalVelConstraint(50),
                        new ProfileAccelConstraint(-25, 45))


                .afterTime(0,
                        new SequentialAction(
                            new InstantAction(() -> {
                                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_DOWN.doubleServosPos.getPos());
                                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_DOWN.middleServoPos.getPos());}),
                            new WaitMilliseconds(180),
                            new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)),
                            new WaitMilliseconds(130),
                            new InstantAction(() -> this.littleArm.doubleServos.setPosition(Constants.DOUBLE_SERVOS_INIT_POS)),
                            new WaitMilliseconds(50),
                            new InstantAction(() -> this.littleArm.middleServo.setPosition(0.263))
                        ))
                .waitSeconds(0.4)

                .afterTime(0, new SequentialAction(
                        new ParallelAction(
                                new SequentialAction(
                                        new WaitMilliseconds(220),
                                        new InstantAction(() -> this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.doubleServosPos.getPos()))),
                                new MotorToPositionRR(this.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS)),
                        new MotorToPositionRR(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MAX_TICS, false)
                ))

                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-56.5, -56.5, Math.toRadians(45)), Math.toRadians(-135))

                .stopAndAdd(
                        new SequentialAction(
                            new WaitForMotor(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MAX_TICS, 15),
                            new InstantAction(() -> this.littleArm.middleServo.setPosition(0)),
                            new WaitMilliseconds(120),
                            new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS)),
                            new WaitMilliseconds(120),
                            new InstantAction(() -> {
                                this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                                this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                                this.littleArm.clawRotator.setPosition(Constants.SAMPLE_PICK_READY.clawRotatorPos.getPos());
                            }),
                            new WaitMilliseconds(250),
                            new MotorToPositionRR(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS)
                        ))

                .afterTime(0, new MotorToPositionRR(this.bigArm.mainBoom, Constants.MAIN_BOOM_MIN_TICS))
                //endregion

                //region Second Sample
                //----------------------------------------------------------------------------------------------------
                .setTangent(Math.toRadians(100))
                .splineToLinearHeading(new Pose2d(-59, -37.5, Math.toRadians(92)), Math.toRadians(90),
                        new TranslationalVelConstraint(50),
                        new ProfileAccelConstraint(-20, 45))


                .afterTime(0,
                        new SequentialAction(
                                new InstantAction(() -> {
                                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_DOWN.doubleServosPos.getPos());
                                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_DOWN.middleServoPos.getPos());}),
                                new WaitMilliseconds(180),
                                new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)),
                                new WaitMilliseconds(130),
                                new InstantAction(() -> this.littleArm.doubleServos.setPosition(Constants.DOUBLE_SERVOS_INIT_POS)),
                                new WaitMilliseconds(50),
                                new InstantAction(() -> this.littleArm.middleServo.setPosition(0.263))
                        ))
                .waitSeconds(0.4)

                .afterTime(0, new SequentialAction(
                        new ParallelAction(
                                new SequentialAction(
                                        new WaitMilliseconds(220),
                                        new InstantAction(() -> this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.doubleServosPos.getPos()))
                                ),
                                new MotorToPositionRR(this.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS)
                        ),
                        new MotorToPositionRR(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MAX_TICS, false)
                ))

                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-56.2, -56.2, Math.toRadians(45)), Math.toRadians(-80))

                .stopAndAdd(new SequentialAction(
                        new WaitForMotor(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MAX_TICS, 15),
                        new InstantAction(() -> this.littleArm.middleServo.setPosition(0)),
                        new WaitMilliseconds(120),
                        new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS)),
                        new WaitMilliseconds(120),
                        new InstantAction(() -> {
                            this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                            this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                            this.littleArm.clawRotator.setPosition(Constants.SAMPLE_PICK_READY.clawRotatorPos.getPos());
                        }),
                        new WaitMilliseconds(250),
                        new MotorToPositionRR(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS)
                ))

                .afterTime(0, new MotorToPositionRR(this.bigArm.mainBoom, Constants.MAIN_BOOM_MIN_TICS))
                //endregion

                //region Third Sample
                //----------------------------------------------------------------------------------------------------
                .afterTime(0, new InstantAction(() -> this.littleArm.clawRotator.setPosition(.23)))
                .afterTime(0.7, new MotorToPositionRR(this.bigArm.viperSlide, 782, false))

                .setTangent(Math.toRadians(100))
                .splineToLinearHeading(new Pose2d(-58.2, -45.5, Math.toRadians(120)), Math.toRadians(90),
                        new TranslationalVelConstraint(50),
                        new ProfileAccelConstraint(-20, 45))


                .afterTime(0,
                        new SequentialAction(
                                new InstantAction(() -> {
                                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_DOWN.doubleServosPos.getPos());
                                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_DOWN.middleServoPos.getPos());}),
                                new WaitMilliseconds(180),
                                new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)),
                                new WaitMilliseconds(130),
                                new InstantAction(() -> this.littleArm.doubleServos.setPosition(Constants.DOUBLE_SERVOS_INIT_POS)),
                                new WaitMilliseconds(50),
                                new InstantAction(() -> this.littleArm.middleServo.setPosition(0.263))
                        ))
                .waitSeconds(0.7)

                .stopAndAdd(new MotorToPositionRR(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS, true))

                .afterTime(0, new SequentialAction(
                        new ParallelAction(
                                new SequentialAction(
                                        new WaitMilliseconds(220),
                                        new InstantAction(() -> {
                                            this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.doubleServosPos.getPos());
                                            this.littleArm.clawRotator.setPosition(Constants.SAMPLE_PICK_READY.clawRotatorPos.getPos());
                                        })
                                ),
                                new MotorToPositionRR(this.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS)
                        ),
                        new MotorToPositionRR(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MAX_TICS, false)
                ))

                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-56, -56, Math.toRadians(45)), Math.toRadians(-80))

                .stopAndAdd(new SequentialAction(
                        new WaitForMotor(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MAX_TICS, 15),
                        new InstantAction(() -> this.littleArm.middleServo.setPosition(0)),
                        new WaitMilliseconds(120),
                        new InstantAction(() -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS)),
                        new WaitMilliseconds(120),
                        new InstantAction(() -> {
                            this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                            this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                            this.littleArm.clawRotator.setPosition(Constants.SAMPLE_PICK_READY.clawRotatorPos.getPos());
                        }),
                        new WaitMilliseconds(250),
                        new MotorToPositionRR(this.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS)
                ))

                .afterTime(0, new MotorToPositionRR(this.bigArm.mainBoom, Constants.MAIN_BOOM_MIN_TICS))
                //endregion

                //region Level 1 Hang
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-27, -11, Math.toRadians(180)), Math.toRadians(0))
                .afterTime(0, new InstantAction(() -> {
                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
                    this.littleArm.doubleServos.setPosition(Constants.DOUBLE_SERVOS_INIT_POS);
                    this.littleArm.middleServo.setPosition(Constants.MIDDLE_SERVO_INIT_POS);
                    this.sweeperArm.baseServo.setPosition(0.59);
                    this.sweeperArm.middleServo.setPosition(0.44);
                    this.sweeperArm.endServo.setPosition(0.3);
                }))
                //endregion

        ;
        Actions.runBlocking(mainTrajectory.build());

    }

    /**
     *
     * @return
     */
    protected org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.AutoActionFactory getTrajectoryFactory () {
        return (org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.AutoActionFactory)super.getTrajectoryFactory();
    }



}
