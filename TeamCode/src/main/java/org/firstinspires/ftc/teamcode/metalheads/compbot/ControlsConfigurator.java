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
        });

        this.gp1_X_Button();

        this.gp1_B_Button();

        this.gp1_Y_Button();

        this.gp1_A_Button();

        this.gp1_Triggers();

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
        });


        this.gp2_A_Button();

        this.gp2_X_Button();

        this.gp2_B_Button();

        this.gp2_Y_Button();


        this.gp2_Dpad();


        this.gp2_Bumpers();


        this.gp2_Triggers();


        this.gp2_LeftStick();


        this.gp2_RightStick();
    }

    /**
     */
    public void gp1_Triggers() {
        this.compBot.addGp1_Left_Trigger_Handler(event -> {


        });

        this.compBot.addGp1_Right_Trigger_Handler(event -> {


        });
    }

    /**
     */
    public void gp1_X_Button() {
        this.compBot.addGp1_X_PressHandler(event -> {


        });
    }

    /**
     */
    public void gp1_B_Button() {
        this.compBot.addGp1_B_PressHandler(event -> {


        });
    }

    public void gp1_Y_Button() {
        this.compBot.addGp1_Y_PressHandler(event -> {


        });

    }

    public void gp1_A_Button() {
        this.compBot.addGp1_A_PressHandler(event -> {

            

        });
    }

    /**
     */
    public void gp1_Bumpers() {
        this.compBot.addGp1_Left_Bumper_DownHandler(event -> {


        });
        this.compBot.addGp1_Right_Bumper_DownHandler(event -> {


        });
    }

    /**
     */
    public void  gp1_Dpad() {
    }

    /**
     */
    private void gp2_RightStick() {
        //ViperSlide
        this.compBot.addGp2_RightStick_X_Handler(event -> {


        });

//        // main boom
//        this.compBot.bigArm.mainBoom.addGp2_RightStick_Y_Handler(event -> {
//            double deadZone = 0.2;
//
//            double pos = event.getPosition();
//
//            double power = 0;
//
//            if (pos > deadZone) {
//                power = (pos - deadZone) / (1 - deadZone);
//            }
//            else if (pos < (deadZone * -1)) {
//
//                power = (pos - (deadZone * -1)) / (1 - deadZone);
//            }
//
//            this.compBot.bigArm.mainBoom.move(-power);
//        });
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


        });
    }

    /**
     */
    public void gp2_B_Button() {
        this.compBot.addGp2_B_PressHandler(event -> {


        });
    }

    /**
     */
    public void gp2_X_Button() {
        this.compBot.addGp2_X_PressHandler(event -> {


        });
    }

    /**
     */
    public void gp2_Y_Button() {
        this.compBot.addGp2_Y_PressHandler(event -> {


        });
    }

    /**
     */
    public void gp2_Dpad() {
        // dpad up
        this.compBot.addGp2_Dpad_Up_DownHandler(event -> {


        });

        // dpad down
        this.compBot.addGp2_Dpad_Down_DownHandler(event -> {


        });

        // dpad left
        this.compBot.addGp2_Dpad_Left_DownHandler(event -> {


        });

        //dpad right
        this.compBot.addGp2_Dpad_Right_DownHandler(event -> {


        });
    }

    /**
     */
    public void gp2_Bumpers() {
        this.compBot.addGp2_Left_Bumper_PressHandler(event -> {


        });
        this.compBot.addGp2_Right_Bumper_PressHandler(event -> {


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
