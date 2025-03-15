package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.action.InstantActionImpl;
import org.firstinspires.ftc.teamcode.library.action.SequentialActionImpl;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.library.encodedmotor.MotorToPosition;

/**
 *
 */
public class ControlsConfigurator {

    private boolean Bflag = true;

    private boolean Yflag = true;

    /**
     */
    private CompBot compBot;

    /**
     * Constructor
     *
     * @param compBot
     */
    public ControlsConfigurator(CompBot compBot) {
        this.compBot = compBot;
    }

    /**
     *
     */
    public void configureGamePad1() {

        // panic button / kill switch
        this.compBot.addGp1_Back_PressHandler(event -> {
            this.compBot.killAllActions();
        });

        // clear and re-init
        this.compBot.addGp1_Start_PressHandler(event -> {
            this.compBot.setArmPos(CompBot.ArmPos.INIT);
            this.compBot.runAction(this.compBot.getActionFactory().initPos());
        });

        //DoHang
        this.gp1_X_Button();

        //Brute Force MainBoom
        this.gp1_B_Button();

        //Reset MainBoom Encoder
        this.gp1_Y_Button();

        //ViperSlides
        this.gp1_Triggers();

        //Sweeper Open/Close
        this.gp1_Bumpers();
    }

    /**
     */
    public void configureGamePad2() {

        // Panic Button / Kill Switch
        this.compBot.addGp2_Back_PressHandler(event -> {
            //this.compBot.terminateOpModeNow();
            this.compBot.killAllActions();
        });

        // clear and re-init
        this.compBot.addGp2_Start_PressHandler(event -> {
            this.compBot.setArmPos(CompBot.ArmPos.INIT);
            this.compBot.runAction(this.compBot.getActionFactory().initPos());
        });

        //Sample Grabing
        this.gp2_A_Button();
        //Sample Placing
        this.gp2_X_Button();
        //Specimen Grabbing
        this.gp2_B_Button();
        //Specimen Placing
        this.gp2_Y_Button();

        // double servos and middle servo
        this.gp2_Dpad();

        //MainBoom Left/Min Right/Max
        this.gp2_Bumpers();

        // claw pincher
        this.gp2_Triggers();

        //X -> Claw Rotator
        this.gp2_LeftStick();

        //Y -> MainBoom
        //X -> ViperSlide
        this.gp2_RightStick();
    }

    /**
     */
    public void gp1_Triggers() {
        this.compBot.addGp1_Left_Trigger_Handler(event -> {
            this.compBot.bigArm.viperSlide.move(-event.getPosition());
        });

        this.compBot.addGp1_Right_Trigger_Handler(event -> {
            this.compBot.bigArm.viperSlide.move(event.getPosition());
        });
    }

    /**
     */
    public void gp1_X_Button() {
        this.compBot.addGp1_X_PressHandler(event -> {
            if (!CompBot.ArmPos.HANG_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(this.compBot.getActionFactory().hangReady());
            }
            else if (CompBot.ArmPos.HANG_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(this.compBot.getActionFactory().doHang());
            }
        });
    }

    /**
     */
    public void gp1_B_Button() {
        this.compBot.addGp1_B_PressHandler(event -> {
            if (Bflag) {
                this.compBot.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                this.compBot.bigArm.mainBoom.setPower(-1);
                Bflag = false;
            } else {
                this.compBot.bigArm.mainBoom.resetEncoder();
                this.compBot.bigArm.mainBoom.setTargetPosition(8);
                this.compBot.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Bflag = true;
            }
        });
    }

    public void gp1_Y_Button() {
        if (Yflag) {
            this.compBot.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.compBot.bigArm.mainBoom.setPower(-0.5);
            this.compBot.bigArm.mainBoom.getSecondaryMotor().setPower(0.5);
            Yflag = false;
        } else {
            this.compBot.bigArm.mainBoom.setPower(0);
            Yflag = true;
        }

    }

    /**
     */
    public void gp1_Bumpers() {
        this.compBot.addGp1_Left_Bumper_DownHandler(event -> {
            this.compBot.runAction(this.compBot.getActionFactory().sweeperClose());
        });
        this.compBot.addGp1_Right_Bumper_DownHandler(event -> {
            this.compBot.runAction(this.compBot.getActionFactory().sweeperOpen());
        });
    }

    /**
     */
    public void  gp1_Dpad() {
//        this.compBot.addGp1_Dpad_Left_PressHandler(event -> {
//            this.compBot.runAction(
//                new SequentialActionImpl(
//                        new MotorToPosition(this.compBot.bigArm.viperSlide, (Constants.VIPER_SLIDES_MIN_TICS - 10), true),
//                        new WaitAction(100)
//                )
//            );
//        });
//
//        this.compBot.addGp1_Dpad_Right_PressHandler(event -> {
//            this.compBot.runAction(
//                    new SequentialActionImpl(
//                            new MotorToPosition(this.compBot.bigArm.viperSlide, (Constants.VIPER_SLIDES_MIN_TICS + 10), false),
//                            new WaitAction(100),
//                            new InstantActionImpl(() -> this.compBot.bigArm.viperSlide.resetEncoder())
//                    )
//            );
//        });
//
//        this.compBot.addGp1_Dpad_Down_PressHandler(event -> {
//            this.compBot.runAction(
//                    new SequentialActionImpl(
//
//                            new MotorToPosition(this.compBot.bigArm.mainBoom, (Constants.MAIN_BOOM_MIN_TICS - 10), false),
//                            new WaitAction(200),
//                            new InstantActionImpl(() -> this.compBot.bigArm.mainBoom.resetEncoder())
//                    )
//            );
//        });
//
//        this.compBot.addGp1_Dpad_Up_PressHandler(event -> {
//            this.compBot.runAction(
//                    new SequentialActionImpl(
//                            new MotorToPosition(this.compBot.bigArm.mainBoom, (Constants.MAIN_BOOM_MIN_TICS + 10), false),
//                            new WaitAction(200),
//                            new InstantActionImpl(() -> this.compBot.bigArm.mainBoom.resetEncoder())
//                    )
//            );
//        });
    }

    /**
     */
    private void gp2_RightStick() {
        //ViperSlide
        this.compBot.addGp2_RightStick_X_Handler(event -> {
            this.compBot.bigArm.viperSlide.move(event.getPosition());
        });

        // main boom
        this.compBot.bigArm.mainBoom.addGp2_RightStick_Y_Handler(event -> {
            double deadZone = 0.2;

            double pos = event.getPosition();

            double power = 0;

            if (pos > deadZone) {
                power = (pos - deadZone) / (1 - deadZone);
            }
            else if (pos < (deadZone * -1)) {

                power = (pos - (deadZone * -1)) / (1 - deadZone);
            }

            this.compBot.bigArm.mainBoom.move(-power);
        });
    }

    /**
     */
    public void gp2_LeftStick() {
        this.compBot.littleArm.clawRotator.addGp2_LeftStick_X_Handler(event -> {

            double servoPos;

            double x = event.getPosition();

            //y=0.04x^{2}-0.36x+0.32
            servoPos = 0.04 * Math.pow(x, 2) - 0.36 * x + 0.32;

            //y=0.5x+0.5
            //servoPos = 0.5 * x + 0.5;

            this.compBot.littleArm.clawRotator.setPosition(servoPos);
        });
    }

    /**
     */
    public void gp2_A_Button() {
        this.compBot.addGp2_A_PressHandler(event -> {
            if (!this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_READY)
                && !this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_DOWN)
                && !this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_UP)) {

                this.compBot.runAction(this.compBot.getActionFactory().samplePickReady());

            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_READY)) {

                this.compBot.runAction(this.compBot.getActionFactory().pickSample());

            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_UP)) {

                this.compBot.runAction(this.compBot.getActionFactory().inverseSamplePick());
            }
        });
    }

    /**
     */
    public void gp2_B_Button() {
        this.compBot.addGp2_B_PressHandler(event -> {
            if (!this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PICK_READY)
                    && !this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PICK)) {

                this.compBot.runAction(this.compBot.getActionFactory().specimenPickReady());

            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PICK_READY)) {

                this.compBot.runAction(this.compBot.getActionFactory().specimenPick());

            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PICK)) {

                this.compBot.runAction(this.compBot.getActionFactory().inverseSpecimenPick());
            }
        });
    }

    /**
     */
    public void gp2_X_Button() {
        this.compBot.addGp2_X_PressHandler(event -> {
            //Retract Sample
            if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_UP)) {
                this.compBot.runAction(this.compBot.getActionFactory().retractSample());
            }
            //Sample Extend Ready
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_RETRACTED)) {
                this.compBot.runAction(this.compBot.getActionFactory().sampleExtendReady());
            }
            //Extend To Sample Drop High
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_EXTEND_READY)) {
                this.compBot.runAction(this.compBot.getActionFactory().extendToSampleDropHigh());
            }
            //Sample Drop High
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_DROP_HIGH_READY)) {
                this.compBot.runAction(this.compBot.getActionFactory().sampleDropHigh());
            }
        });
    }

    /**
     */
    public void gp2_Y_Button() {
        this.compBot.addGp2_Y_PressHandler(event -> {
            if (this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PICK)) {

                this.compBot.runAction(this.compBot.getActionFactory().specimenPlaceHighReady());

            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY)) {

                this.compBot.runAction(this.compBot.getActionFactory().specimenPlaceHigh());

            }
        });
    }

    /**
     */
    public void gp2_Dpad() {
        // dpad up
        this.compBot.addGp2_Dpad_Up_DownHandler(event -> {
            this.compBot.littleArm.doubleServos.move(1);
        });

        // dpad down
        this.compBot.addGp2_Dpad_Down_DownHandler(event -> {
            this.compBot.littleArm.doubleServos.move(-1);
        });

        // dpad left
        this.compBot.addGp2_Dpad_Left_DownHandler(event -> {
            this.compBot.littleArm.middleServo.move(-1);
        });

        //dpad right
        this.compBot.addGp2_Dpad_Right_DownHandler(event -> {
            this.compBot.littleArm.middleServo.move(1);
        });
    }

    /**
     */
    public void gp2_Bumpers() {
        this.compBot.addGp2_Left_Bumper_PressHandler(event -> {
            this.compBot.runAction(new MotorToPosition(this.compBot.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS));
        });
        this.compBot.addGp2_Right_Bumper_PressHandler(event -> {
            this.compBot.runAction(new MotorToPosition(this.compBot.bigArm.mainBoom, Constants.MAIN_BOOM_MIN_TICS));
        });
    }

    /**
     */
    public void gp2_Triggers() {
        // claw
        this.compBot.littleArm.clawPincher.addGp2_Right_Trigger_Handler(event -> {
            this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
        });

        this.compBot.littleArm.clawPincher.addGp2_Left_Trigger_Handler(event -> {
            this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS);
        });
    }
}
