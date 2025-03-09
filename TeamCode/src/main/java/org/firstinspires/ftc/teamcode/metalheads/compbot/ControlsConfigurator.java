package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.encodedmotor.MotorToPosition;

/**
 *
 */
public class ControlsConfigurator {

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
            // this.compBot.terminateOpModeNow();
            this.compBot.killAllActions();
        });

        // clear and re-init
        this.compBot.addGp1_Start_PressHandler(event -> {
            this.compBot.setArmPos(CompBot.ArmPos.INIT);
            this.compBot.runAction(this.compBot.getActionFactory().initPos());
        });

        // X button
        //this.gp1_X_Button();

        // triggers
        this.gp1_Left_Trigger();
        this.gp1_Right_Trigger();
    }

    /**
     *
     */
    public void configureGamePad2() {

        // Panic Button / Kill Switch
        this.compBot.addGp2_Back_PressHandler(event -> {
            //this.compBot.terminateOpModeNow();
            this.compBot.killAllActions();
        });

        // main boom
        //this.compBot.bigArm.mainBoom.addControl(Control.Gp2_RightStickY);
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


        // viper slides
        this.compBot.addGp2_RightStick_X_Handler(event -> {
            this.compBot.bigArm.viperSlide.move(event.getPosition());
        });
        //this.compBot.bigArm.viperSlide.addControl(Control.Gp2_RightStickX);

        // double servos and middle servo
        this.gp2_Dpad();

        this.compBot.littleArm.clawRotator.addGp2_LeftStick_X_Handler(event -> {

            double servoPos;

            double x = event.getPosition() * -1;

            //y=0.02x^{2}+0.32x+0.3
            servoPos = 0.02 * Math.pow(x, 2) + 0.32 * x + 0.3;
            //y=0.5x+0.5
            //servoPos = 0.5 * x + 0.5;

            this.compBot.littleArm.clawRotator.setPosition(servoPos);
        });

        // claw pincher
        this.gp2_Triggers();

        // clear and re-init
        this.compBot.addGp2_Start_PressHandler(event -> {
            this.compBot.setArmPos(CompBot.ArmPos.INIT);
            this.compBot.runAction(this.compBot.getActionFactory().initPos());
        });

        // presets
        this.gp2_A_Button();
        this.gp2_B_Button();
        this.gp2_X_Button();
        this.gp2_Y_Button();
        this.gp1_Left_Bumper_Button();
        this.gp1_Right_Bumper_Button();
    }

    /**
     */
    public void gp1_Left_Trigger()
    {
        this.compBot.addGp1_Left_Trigger_Handler(event -> {
            this.compBot.bigArm.viperSlide.move(-event.getPosition());
        });
    }

    /**
     */
    public void gp1_Right_Trigger()
    {
        this.compBot.addGp1_Right_Trigger_Handler(event -> {
            this.compBot.bigArm.viperSlide.move(event.getPosition());
        });
    }

    /**
     */
    public void gp1_X_Button()
    {
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
    public void gp1_Left_Bumper_Button()
    {
        this.compBot.addGp1_Left_Bumper_DownHandler(event -> {
            this.compBot.runAction(this.compBot.getActionFactory().sweeperOpen());
        });

    }

    /**
     */
    public void gp1_Right_Bumper_Button()
    {
        this.compBot.addGp1_Right_Bumper_DownHandler(event -> {
            this.compBot.runAction(this.compBot.getActionFactory().sweeperClose());
        });
    }

    /**
     */
    public void gp1_B_Button()
    {
        this.compBot.addGp1_B_PressHandler(event -> {
            this.compBot.bigArm.viperSlide.resetEncoder();
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
    public void gp2_Left_Bumper() {
        this.compBot.addGp2_Left_Bumper_PressHandler(event -> {
            this.compBot.runAction(new MotorToPosition(this.compBot.bigArm.mainBoom, Constants.MAIN_BOOM_MAX_TICS));
        });
    }

    /**
     */
    public void gp2_Right_Bumper() {
        this.compBot.addGp2_Right_Bumper_PressHandler(event -> {
            this.compBot.runAction(new MotorToPosition(this.compBot.bigArm.mainBoom, Constants.MAIN_BOOM_MIN_TICS));
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
            if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_UP)) {
                this.compBot.runAction(this.compBot.getActionFactory().retractSample());
            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_RETRACTED)) {
                this.compBot.runAction(this.compBot.getActionFactory().sampleExtendReady());
            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_EXTEND_READY)) {

                this.compBot.runAction(this.compBot.getActionFactory().extendToSampleDropHigh());
            }
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
