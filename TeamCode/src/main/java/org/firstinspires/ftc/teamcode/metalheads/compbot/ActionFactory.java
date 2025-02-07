package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.action.InstantActionImpl;
import org.firstinspires.ftc.teamcode.library.action.ParallelActionImpl;
import org.firstinspires.ftc.teamcode.library.action.SequentialActionImpl;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.library.encodedmotor.MainBoomToPosition;
import org.firstinspires.ftc.teamcode.library.encodedmotor.ViperSlideToPosition;

/**
 *
 */
public class ActionFactory {

    /**
     */
    private CompBot compBot;

    /**
     * Contructor
     */
    public ActionFactory(CompBot compBot) {
        this.compBot = compBot;
    }

    /**
     * @return
     */
    public AbstractAction sweeperOpen() {
        return new InstantActionImpl(() -> {
            this.compBot.sweeperArm.baseServo.setPosition(Constants.SWEEPER_BASE_SERVO_OPEN_POS);
            this.compBot.sweeperArm.middleServo.setPosition(Constants.SWEEPER_MIDDLE_SERVO_OPEN_POS);
            this.compBot.sweeperArm.endServo.setPosition(Constants.SWEEPER_END_SERVO_OPEN_POS);
        });
    }

    /**
     * @return
     */
    public AbstractAction sweeperClose() {
        return new InstantActionImpl(() -> {
            this.compBot.sweeperArm.baseServo.setPosition(Constants.SWEEPER_BASE_SERVO_CLOSED_POS);
            this.compBot.sweeperArm.middleServo.setPosition(Constants.SWEEPER_MIDDLE_SERVO_CLOSED_POS);
            this.compBot.sweeperArm.endServo.setPosition(Constants.SWEEPER_END_SERVO_CLOSED_POS);
        });
    }

    /**
     * @return
     */
    public AbstractAction hangReady() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> this.compBot.setArmPos(CompBot.ArmPos.HANG_READY)),
            new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.HANG_READY.mainBoomPos.getPos(), Constants.MAIN_BOOM_TIMEOUT_DEFAULT),
            new ParallelActionImpl(
                new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.HANG_READY.vSlidePos.getPos(), Constants.VIPER_SLIDES_TIMEOUT_DEFAULT),
                new InstantActionImpl(() -> {
                    this.compBot.littleArm.doubleServos.setPosition(Constants.HANG_READY.doubleServosPos.getPos());
                    this.compBot.littleArm.middleServo.setPosition(Constants.HANG_READY.middleServoPos.getPos());
                    this.compBot.littleArm.clawRotator.setPosition(Constants.HANG_READY.clawRotatorPos.getPos());
                    this.compBot.littleArm.clawPincher.setPosition(Constants.HANG_READY.clawPincherPos.getPos());
                })
            )
        );
    }

    /**
     *
     * @return
     */
    public AbstractAction doHang() {
        return new SequentialActionImpl(
            new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.HANG); }),
            new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.HANG.vSlidePos.getPos(), Constants.VIPER_SLIDES_TIMEOUT_DEFAULT),
            new WaitAction(1000),
            new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.HANG.mainBoomPos.getPos(), Constants.MAIN_BOOM_TIMEOUT_DEFAULT)
        );
    }

    /**
     * @return
     */
    public AbstractAction initPos() {
        return new SequentialActionImpl(
            new InstantActionImpl(() -> { ActionFactory.this.compBot.setArmPos(CompBot.ArmPos.INIT_READY); }),
            new ViperSlideToPosition(this.compBot.bigArm.viperSlide,Constants.VIPER_SLIDES_MIN_TICS, Constants.VIPER_SLIDES_TIMEOUT_DEFAULT),
            new InstantActionImpl(() -> {
                this.compBot.littleArm.doubleServos.setPosition(Constants.DOUBLE_SERVOS_INIT_POS);
                this.compBot.littleArm.middleServo.setPosition(Constants.MIDDLE_SERVO_INIT_POS);
                this.compBot.littleArm.clawRotator.setPosition(Constants.CLAW_ROTATOR_INIT_POS);
                this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_INIT_POS);
            }),
            new WaitAction(200),
            new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.MAIN_BOOM_MIN_TICS, Constants.MAIN_BOOM_TIMEOUT_DEFAULT)

        );
    }

    /**
     * @return
     */
    public AbstractAction samplePickReady() {
        if (this.compBot.getArmPos().equals(CompBot.ArmPos.INIT_READY)) {
            return new SequentialActionImpl(
                    new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY); }),
                    new ParallelActionImpl(
                            new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.SAMPLE_PICK_READY.vSlidePos.getPos(), Constants.VIPER_SLIDES_TIMEOUT_DEFAULT),
                            new InstantActionImpl(() -> {
                                this.compBot.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                                this.compBot.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                                this.compBot.littleArm.clawRotator.setPosition(Constants.SAMPLE_PICK_READY.clawRotatorPos.getPos());
                                this.compBot.littleArm.clawPincher.setPosition(Constants.SAMPLE_PICK_READY.clawPincherPos.getPos());
                            })
                    )
            );
        }
        else {
            return new SequentialActionImpl(
                    new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY); }),
                    new ParallelActionImpl(
                          new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.SAMPLE_PICK_READY.vSlidePos.getPos(), Constants.VIPER_SLIDES_TIMEOUT_DEFAULT),
                          new InstantActionImpl(() -> {
                                this.compBot.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                                this.compBot.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                                this.compBot.littleArm.clawRotator.setPosition(Constants.SAMPLE_PICK_READY.clawRotatorPos.getPos());
                                this.compBot.littleArm.clawPincher.setPosition(Constants.SAMPLE_PICK_READY.clawPincherPos.getPos());
                            })
                    ),
                    new MainBoomToPosition(this.compBot.bigArm.mainBoom,Constants.MAIN_BOOM_MIN_TICS, Constants.MAIN_BOOM_TIMEOUT_DEFAULT)
            );
        }
    }

    /**
     *
     * @return
     */
    public AbstractAction samplePickDown() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_DOWN)),
                new InstantActionImpl(() -> this.compBot.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_DOWN.middleServoPos.getPos())),
                new WaitAction(75),
                new InstantActionImpl(() -> this.compBot.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_DOWN.doubleServosPos.getPos()))
        );
    }


    /**
     *
     * @return
     */
    public AbstractAction samplePickUp() {
        return new InstantActionImpl(() -> {
            this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_UP);
            this.compBot.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_UP.doubleServosPos.getPos());
            this.compBot.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_UP.middleServoPos.getPos());
        });
    }

    /**
     *
     * @return
     */
    public AbstractAction pickSample() {
        return new SequentialActionImpl(
                this.compBot.getActionFactory().samplePickDown(),
                new WaitAction(250),
                new InstantActionImpl(() -> this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)),
                new WaitAction(350),
                this.compBot.getActionFactory().samplePickUp()
        );
    }

    /**
     *
     * @return
     */
    public AbstractAction inverseSamplePick() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY)),
            new ParallelActionImpl(
                new InstantActionImpl(() -> {
                    this.compBot.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                    this.compBot.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                    this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS);
                })
            )
        );
    }

//    /**
//     * @return
//     */
//    public AbstractAction sampleExtendReady() {
//        return new SequentialActionImpl(
//            new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_EXTEND_READY); }),
//            new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS, Constants.VIPER_SLIDES_TIMEOUT_DEFAULT),
//            new ParallelActionImpl(
//                new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS, Constants.MAIN_BOOM_TIMEOUT_DEFAULT),
//                new SequentialActionImpl(
//                    new WaitAction(500),
//                    new InstantActionImpl(() -> this.compBot.littleArm.doubleServos.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.doubleServosPos.getPos())),
//                    new WaitAction(250),
//                    new InstantActionImpl(() -> this.compBot.littleArm.middleServo.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.middleServoPos.getPos())),
//                    new InstantActionImpl(() -> this.compBot.littleArm.clawRotator.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.clawRotatorPos.getPos()))
//                )
//            )
//        );
//    }

    /**
     * @return
     */
    public AbstractAction sampleExtendReady() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_EXTEND_READY); }),
                new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS, Constants.VIPER_SLIDES_TIMEOUT_DEFAULT),
                new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS, Constants.MAIN_BOOM_TIMEOUT_DEFAULT),
                new SequentialActionImpl(
                        new WaitAction(500),
                        new InstantActionImpl(() -> this.compBot.littleArm.doubleServos.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.doubleServosPos.getPos())),
                        new WaitAction(250),
                        new InstantActionImpl(() -> this.compBot.littleArm.middleServo.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.middleServoPos.getPos())),
                        new InstantActionImpl(() -> this.compBot.littleArm.clawRotator.setPosition(Constants.SAMPLE_PLACE_HIGH_READY.clawRotatorPos.getPos()))
                )
        );
    }

    /**
     * @return
     */
    public AbstractAction sampleDropHigh() {
        return new SequentialActionImpl(
            new InstantActionImpl(() -> { this.compBot.setArmPos( CompBot.ArmPos.SAMPLE_DROPPED_HIGH );}) ,
            new InstantActionImpl(() -> this.compBot.littleArm.middleServo.setPosition(0.5)),
            new WaitAction(250),
            new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS, Constants.VIPER_SLIDES_TIMEOUT_DEFAULT),
            new WaitAction(200)

        );
    }

    /**
     *
     * @return
     */
    public AbstractAction extendToSampleDropHigh() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROP_HIGH_READY); }),
                new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.VIPER_SLIDES_MAX_TICS, Constants.VIPER_SLIDES_TIMEOUT_DEFAULT)

        );
    }

    /**
     *
     * @return
     */
    public AbstractAction retractSample() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_RETRACTED); }),
                new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS, Constants.VIPER_SLIDES_TIMEOUT_DEFAULT)
        );
    }

    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------

    /**
     * @return
     */
    public AbstractAction specimenPickReady() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY)),
                new ParallelActionImpl(
                        new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS, Constants.MAIN_BOOM_TIMEOUT_DEFAULT),
                        new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.SPECIMEN_PICK_READY.vSlidePos.getPos(), Constants.VIPER_SLIDES_TIMEOUT_DEFAULT),
                        new InstantActionImpl(() -> {
                            this.compBot.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                            this.compBot.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                            this.compBot.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                            this.compBot.littleArm.clawPincher.setPosition(Constants.SPECIMEN_PICK_READY.clawPincherPos.getPos());
                        })
                )

        );
    }

    /**
     * @return
     */
    public AbstractAction specimenPick() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK)),
                new InstantActionImpl(() -> this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS)),
                new WaitAction(250),
                new MainBoomToPosition(this.compBot.bigArm.mainBoom,(Constants.MAIN_BOOM_MAX_TICS - 375), Constants.MAIN_BOOM_TIMEOUT_DEFAULT)

        );
    }

    /**
     *
     * @return
     */
    public AbstractAction inverseSpecimenPick() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY)),
                new InstantActionImpl(() -> this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS)),
                new WaitAction(250),
                new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS, Constants.MAIN_BOOM_TIMEOUT_DEFAULT)

            );
    }


    /**
     *
     * @return
     */
    public AbstractAction specimenPlaceHighReady() {
        return new SequentialActionImpl(
                new InstantActionImpl(() ->  this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY)),
                new ParallelActionImpl(
                        new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos.getPos(), Constants.MAIN_BOOM_TIMEOUT_DEFAULT),
                        new InstantActionImpl(() -> {
                            this.compBot.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                            this.compBot.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                        })
                ),
                new ParallelActionImpl(
                        new InstantActionImpl(() -> this.compBot.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos())),
                        new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.SPECIMEN_PLACE_HIGH_READY.vSlidePos.getPos(), Constants.VIPER_SLIDES_TIMEOUT_DEFAULT)
                )

            );
    }

    /**
     *
     * @return
     */
    public AbstractAction specimenPlaceHigh() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_HIGH); }),
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY); }),
                new InstantActionImpl(() -> this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS)),
                new WaitAction(400),
                new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS, 1),
                new InstantActionImpl(() -> this.compBot.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos())),
                new WaitAction(250),
                new ParallelActionImpl(
                        new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.SPECIMEN_PICK_READY.mainBoomPos.getPos(), Constants.MAIN_BOOM_TIMEOUT_DEFAULT),
                        new InstantActionImpl(() -> {
                            this.compBot.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                            this.compBot.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                        })
                )

        );
    }
}