package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.action.InstantActionImpl;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToPosition;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToPosition;

/**
 *
 */
public class AutoActionFactory {

    /**
     */
    private CompBot compBot;

    /**
     * Contructor
     */
    public AutoActionFactory(CompBot compBot) {
        this.compBot = compBot;
    }

    /**
     * @return
     */
    public Action initPos() {
        return new SequentialAction(
            new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.VIPER_SLIDES_MIN_TICS),

            new ParallelAction(
                AutoActionFactory.this.compBot.littleArm.doubleServos.gotoPositionAction(
                        AutoActionFactory.this.compBot.getConfig().littleArmConfig.doubleServosConfig.homePosition, 1),
                AutoActionFactory.this.compBot.littleArm.middleServo.gotoPositionAction(
                        AutoActionFactory.this.compBot.getConfig().littleArmConfig.middleServoConfig.homePosition, 1),
                AutoActionFactory.this.compBot.littleArm.clawRotator.gotoPositionAction(
                        AutoActionFactory.this.compBot.getConfig().littleArmConfig.clawRotatorConfig.homePosition, 1),
                AutoActionFactory.this.compBot.littleArm.clawPincher.gotoPositionAction(
                        AutoActionFactory.this.compBot.getConfig().littleArmConfig.clawPincherConfig.homePosition, 1)
            ),
            new MainBoomToPosition(this.compBot.bigArm.mainBoom, 0),
            new InstantAction(() -> { AutoActionFactory.this.compBot.setArmPos(CompBot.ArmPos.INIT_READY); })
        );
    }

    /**
     * @return
     */
    public Action samplePickReady() {
        if (this.compBot.getArmPos().equals(CompBot.ArmPos.INIT_READY)) {
            return new SequentialAction(
                    new ParallelAction(
                            this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.SAMPLE_PICK_READY.vSlidePos),
                            this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                            this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                            this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawRotatorPos),
                            this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawPincherPos)),
                    new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY); }));
        }
        else {
            return new SequentialAction(
                    new ParallelAction(
                            this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.SAMPLE_PICK_READY.vSlidePos),
                            this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                            this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                            this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawRotatorPos),
                            this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawPincherPos)),
                    this.compBot.bigArm.mainBoom.gotoPositionAction(0, 1, 300),
                    new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY); }));

        }
    }

    /**
     *
     * @return
     */
    public Action samplePickDown() {
        return new SequentialAction(
                this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_DOWN.middleServoPos),
                new WaitAction(75),
                this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_DOWN.doubleServosPos),
            new InstantAction(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_DOWN))
        );
    }


    /**
     *
     * @return
     */
    public Action samplePickUp() {
        return new SequentialAction(
                new ParallelAction(
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_UP.doubleServosPos),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_UP.middleServoPos)
                        ),
                new InstantAction(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_UP))
        );
    }

    /**
     *
     * @return
     */
    public Action pickSample() {
        return new SequentialAction(
                this.compBot.getActionFactory().samplePickDown(),
                new WaitAction(250),
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_CLOSE_POS, 1),
                new WaitAction(350),
                this.compBot.getActionFactory().samplePickUp()
        );
    }

    /**
     * @return
     */
    public Action sampleDropHigh() {
        return new SequentialAction(
            this.compBot.littleArm.middleServo.gotoPositionAction(0.5, 1),
            new WaitAction(250),
            this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.VIPER_SLIDES_MIN_TICS),
            new InstantAction(() -> { this.compBot.setArmPos( CompBot.ArmPos.SAMPLE_DROPPED_HIGH );})
        );
    }

    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------

    /**
     * @return
     */
    public Action specimenPickReady() {
        if (this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY)) {
            return new SequentialAction(
                    new InstantAction(() -> {
                        this.compBot.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                        this.compBot.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                        this.compBot.littleArm.clawPincher.setPosition(Constants.SPECIMEN_PICK_READY.clawPincherPos.getPos());
                        this.compBot.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                    }),
                    new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.SPECIMEN_PICK_READY.vSlidePos.getPos(), false, 20),
                    new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.SPECIMEN_PICK_READY.mainBoomPos.getPos())

            );


        } else {
        return new SequentialAction(
                new InstantAction(() -> {
                    this.compBot.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                    this.compBot.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                    this.compBot.littleArm.clawPincher.setPosition(Constants.SPECIMEN_PICK_READY.clawPincherPos.getPos());
                    this.compBot.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                }),
                new ViperSlideToPosition(this.compBot.bigArm.viperSlide, Constants.SPECIMEN_PICK_READY.vSlidePos.getPos(), false, 20),
                new MainBoomToPosition(this.compBot.bigArm.mainBoom, Constants.SPECIMEN_PICK_READY.mainBoomPos.getPos())

        );
            }
    }


    /**
     *
     * @return
     */
    public Action specimenPlaceHighReady() {
        return new SequentialAction(
                new InstantAction(() -> {
                    AutoActionFactory.this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY);
                    this.compBot.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                    this.compBot.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                    this.compBot.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());
                    this.compBot.littleArm.clawPincher.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawPincherPos.getPos());
                }),
                new MainBoomToPosition(compBot.bigArm.mainBoom, Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos.getPos(), false, 50),
                new ViperSlideToPosition(compBot.bigArm.viperSlide, Constants.SPECIMEN_PLACE_HIGH_READY.vSlidePos.getPos())
        );
    }

    /**
     * @return
     */
    public Action sweeperOpen() {
        return new InstantAction(() -> {
            this.compBot.sweeperArm.baseServo.setPosition(Constants.SWEEPER_BASE_SERVO_OPEN_POS);
            this.compBot.sweeperArm.middleServo.setPosition(Constants.SWEEPER_MIDDLE_SERVO_OPEN_POS);
            this.compBot.sweeperArm.endServo.setPosition(Constants.SWEEPER_END_SERVO_OPEN_POS);
        });
    }

    /**
     * @return
     */
    public Action sweeperClose() {
        return new InstantAction(() -> {
            this.compBot.sweeperArm.baseServo.setPosition(Constants.SWEEPER_BASE_SERVO_CLOSED_POS);
            this.compBot.sweeperArm.middleServo.setPosition(Constants.SWEEPER_MIDDLE_SERVO_CLOSED_POS);
            this.compBot.sweeperArm.endServo.setPosition(Constants.SWEEPER_END_SERVO_CLOSED_POS);
        });
    }
}