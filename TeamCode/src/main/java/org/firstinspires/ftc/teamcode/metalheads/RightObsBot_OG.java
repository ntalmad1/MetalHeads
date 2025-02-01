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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoActionFactory;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToSpecimenHighReady;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToSpecimenPickReady;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToZero;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToSpecimenHighReady;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToZero;

/**
 *
 */
@Autonomous(name = "RightObsBot_OG", group = "Auto")
@Disabled
public class RightObsBot_OG extends AutoBot {

    private AutoActionFactory autoActionFactory;

    /**
     * Constructor
     *
     */
    public RightObsBot_OG() {
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

                //SPECIMEN PLACE HIGH READY (Main Boom + Viper Slide)
                // BEGINING
                //
                .afterTime(0, new InstantAction(()->{
                    int targetPosition = Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos.getPos();

                    this.bigArm.mainBoom.getMotor().setTargetPosition(targetPosition);
                    this.bigArm.mainBoom.getSecondaryMotor().setTargetPosition(targetPosition);

                    this.bigArm.mainBoom.getMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    this.bigArm.mainBoom.getSecondaryMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    this.bigArm.mainBoom.getMotor().setPower(1);
                    this.bigArm.mainBoom.getSecondaryMotor().setPower(1);

                }))
                .afterTime(0.3, new SequentialAction(
                        new InstantAction(() -> {
                            this.telemetry.log().add("1.1 Left MainBoom: " + this.bigArm.mainBoom.getSecondaryMotor().getCurrentPosition());
                            this.telemetry.log().add("1.1 Right MainBoom: " + this.bigArm.mainBoom.getMotor().getCurrentPosition());
                            this.telemetry.update();
                        }),

                        new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide),

                        new InstantAction(() -> {
                            this.telemetry.log().add("1.2 Left MainBoom: " + this.bigArm.mainBoom.getSecondaryMotor().getCurrentPosition());
                            this.telemetry.log().add("1.2 Right MainBoom: " + this.bigArm.mainBoom.getMotor().getCurrentPosition());
                            this.telemetry.update();
                        })
                ))

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
                //Viper Slide -> 0
                .afterTime(0.4, new SequentialAction(
                        new ViperSlideToZero(this.bigArm.viperSlide),
                        new ParallelAction(
                                new MainBoomToZero(this.bigArm.mainBoom),
                                new InstantAction(() -> {
                                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                                    this.littleArm.clawPincher.setPosition(Constants.SAMPLE_PICK_READY.clawPincherPos.getPos());

                                })
                        )
                ))


                //Retreat from Bar
                .splineToConstantHeading(new Vector2d(12,-50), Math.toRadians(0),
                        new TranslationalVelConstraint(40),
                        new ProfileAccelConstraint(-60, 20)
                )





//              Samples
//              ---------------------------------------------------------------------------------------------------



                /*
                 * First Sample
                 */
                .splineToConstantHeading(new Vector2d(36, -46), Math.toRadians(90)) //Go towards sample


                //set power 0 in case of voltage draw
                .afterTime(0, () -> {
                    this.bigArm.mainBoom.setPower(0);
                    this.bigArm.viperSlide.setPower(0);
                })



                .splineToConstantHeading(new Vector2d(36, -18.6), Math.toRadians(90))


                .splineToConstantHeading(new Vector2d(44, -11), Math.toRadians(0),
                        new TranslationalVelConstraint(30))

                .splineToConstantHeading(new Vector2d(48.6, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(25),
                        new ProfileAccelConstraint(-25, 60))

                .lineToYConstantHeading(-48)
                .splineToConstantHeading(new Vector2d(47,-52), Math.toRadians(90),
                        null,
                        new ProfileAccelConstraint(-60, 18)
                )


                /*
                 * second sample
                 */
                .lineToYConstantHeading(-22)

                .lineToYConstantHeading(-18.6,
                        new TranslationalVelConstraint(25)
                )

                .splineToConstantHeading(new Vector2d(53, -10), Math.toRadians(0),
                        new TranslationalVelConstraint(25)
                )

                .splineToConstantHeading(new Vector2d(57, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(20)
                )

                .lineToYConstantHeading(-45)
                .splineToConstantHeading(new Vector2d(55,-49), Math.toRadians(90),
                        null,
                        new ProfileAccelConstraint(-60,18)
                )


                /*
                 * Third sample
                 */
                .lineToYConstantHeading(-22,
                        null,
                        new ProfileAccelConstraint(-60,30)
                )

                .lineToYConstantHeading(-18.6,
                        new TranslationalVelConstraint(28)
                )

                .splineToConstantHeading(new Vector2d(59, -10), Math.toRadians(0),
                        new TranslationalVelConstraint(16)
                )

                .splineToConstantHeading(new Vector2d(62.2, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(18)
                )

                .lineToYConstantHeading(-48)
                .splineToConstantHeading(new Vector2d(63,-52), Math.toRadians(90),
                        null,
                        new ProfileAccelConstraint(-15, 60)
                )




//                Specimens
//                ---------------------------------------------------------------------------------------




                .afterTime(0, new MainBoomToSpecimenPickReady(this.bigArm.mainBoom))

                //Arc To Specimen
                .splineToConstantHeading(new Vector2d(52, -48), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(42, -57.5), Math.toRadians(270),
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-15,45)
                )





//              Cycle Specimen (2nd total)
//              ----------------------------------------------------------------------------







                .waitSeconds(0.3)

                .afterTime(0.0, () -> {
                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
                })

                .waitSeconds(0.15)


                //Raise Up Boom, Then Extend ViperSLide
                .afterTime(0, new SequentialAction(

                        new MainBoomToSpecimenHighReady(this.bigArm.mainBoom),
                        new WaitAction(0.3),

                        new ParallelAction(

                                new InstantAction(() -> {
                                    this.telemetry.log().add("2.1 Left MainBoom: " + this.bigArm.mainBoom.getSecondaryMotor().getCurrentPosition());
                                    this.telemetry.log().add("2.1 Right MainBoom: " + this.bigArm.mainBoom.getMotor().getCurrentPosition());
                                    this.telemetry.update();
                                }),

                                new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide, specimenCycleExtraTicks),

                                new InstantAction(() -> {

                                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());
                                }),

                                new InstantAction(() -> {
                                    this.telemetry.log().add("2.2 Left MainBoom: " + this.bigArm.mainBoom.getSecondaryMotor().getCurrentPosition());
                                    this.telemetry.log().add("2.2 Right MainBoom: " + this.bigArm.mainBoom.getMotor().getCurrentPosition());
                                    this.telemetry.update();
                                })

                        ))
                )



                //Hang Specimen
                .setTangent(Math.toRadians(140))
                .splineToConstantHeading(new Vector2d(3,-60), Math.toRadians(180),
                        new TranslationalVelConstraint(20))
                .splineToConstantHeading(new Vector2d(3,-38), Math.toRadians(90),
                        new TranslationalVelConstraint(17))




                .afterTime(0, () -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS))

                .afterTime(0.18, () -> this.littleArm.middleServo.setPosition((Constants.MIDDLE_SERVO_SPECIMEN_PLACED)))



                .afterTime(0.5, new SequentialAction(

                        new ViperSlideToZero(this.bigArm.viperSlide),

                        new ParallelAction(
                                new MainBoomToSpecimenPickReady(this.bigArm.mainBoom),

                                new InstantAction(() -> {

                                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());

                                })
                        )
                ))



                //TODO:See if this changes anything
                .waitSeconds(0.2)



                //Retreat back to next Specimen
                .setTangent(Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(24, -51), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(42, -57.5), Math.toRadians(270),
                        new TranslationalVelConstraint(18),
                        new ProfileAccelConstraint(-60, 45)
                )





//              Cycle Specimen (3rd total)
//              -----------------------------------------------------------------------------------------





                .waitSeconds(0.25)

                .afterTime(0.0, () -> {
                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
                })

                .waitSeconds(0.15)


                //Raise Up Boom, Then Extend ViperSLide
                .afterTime(0, new SequentialAction(

                        new MainBoomToSpecimenHighReady(this.bigArm.mainBoom),
                        new WaitAction(0.3),

                        new ParallelAction(

                                new InstantAction(() -> {
                                    this.telemetry.log().add("3.1 Left MainBoom: " + this.bigArm.mainBoom.getSecondaryMotor().getCurrentPosition());
                                    this.telemetry.log().add("3.1 Right MainBoom: " + this.bigArm.mainBoom.getMotor().getCurrentPosition());
                                    this.telemetry.update();
                                }),

                                new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide, specimenCycleExtraTicks),

                                new InstantAction(() -> {

                                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());

                                }),

                                new InstantAction(() -> {
                                    this.telemetry.log().add("3.2 Left MainBoom: " + this.bigArm.mainBoom.getSecondaryMotor().getCurrentPosition());
                                    this.telemetry.log().add("3.2 Right MainBoom: " + this.bigArm.mainBoom.getMotor().getCurrentPosition());
                                    this.telemetry.update();
                                })
                        )
                ))




                //Hang Specimen
                .setTangent(Math.toRadians(140))
                .splineToConstantHeading(new Vector2d(15,-60), Math.toRadians(180),
                        new TranslationalVelConstraint(20))
                .splineToConstantHeading(new Vector2d(3,-38), Math.toRadians(90),
                        new TranslationalVelConstraint(17))





                .afterTime(0, () -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS))

                .afterTime(0.18, () -> this.littleArm.middleServo.setPosition((Constants.MIDDLE_SERVO_SPECIMEN_PLACED)))



                .afterTime(0.5, new SequentialAction(

                        new ViperSlideToZero(this.bigArm.viperSlide),

                        new ParallelAction(
                                new MainBoomToZero(this.bigArm.mainBoom),

                                new InstantAction(() -> {

                                    this.littleArm.middleServo.setPosition(Constants.MIDDLE_SERVO_INIT_POS);
                                    this.littleArm.doubleServos.setPosition(Constants.DOUBLE_SERVOS_INIT_POS);
                                    this.littleArm.clawRotator.setPosition(Constants.CLAW_ROTATOR_INIT_POS);
                                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);

                                })
                        )
                ))


                //TODO:See if this changes anything
                .waitSeconds(0.2)


//                //Retreat back to next Specimen
//                .setTangent(Math.toRadians(270))
//                .splineToConstantHeading(new Vector2d(24, -51), Math.toRadians(0))
//                .splineToConstantHeading(new Vector2d(40, -57.5), Math.toRadians(270),
//                        new TranslationalVelConstraint(18),
//                        new ProfileAccelConstraint(-60, 45)
//                )

                //Retreat back to observation zone
                .setTangent(Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(24, -51), Math.toRadians(0),
                        new TranslationalVelConstraint(25))
                .splineToConstantHeading(new Vector2d(44, -52), Math.toRadians(270),
                        new TranslationalVelConstraint(20))

                .waitSeconds(80)










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
