package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressHandler;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

/**
 */
@TeleOp(name="SweeperArmCalib", group="Calibration")
//@Disabled
public class SweeperArmCalib extends IsaacBot {

    private ServoComponent sweeperBase;
    private ServoComponent sweeperMiddle;
    private ServoComponent sweeperEnd;
    private ServoComponent specimenBrace;
    private ServoComponent servo;

    private ServoComponentConfig config1;
    private ServoComponentConfig config2;
    private ServoComponentConfig config3;
    private ServoComponentConfig config4;
    private ServoComponentConfig config;

    private int toggle = 0;
    private double gamePadIncrement = 0.006;
    private String currentServo = "Base Servo";

    public SweeperArmCalib() {
        super();

        config1 = new ServoComponentConfig(this);
        config1.servoName = "sweeperBase";
        config1.maxIncrement = 0.006;
        config1.minPosition = 0;
        config1.maxPosition = 1;
        config1.homePosition = Constants.SWEEPER_BASE_SERVO_OPEN_POS;
        config1.zeroDegreePosition = 0.5;

        config2 = new ServoComponentConfig(this);
        config2.servoName = "sweeperMiddle";
        config2.maxIncrement = 0.006;
        config2.minPosition = 0;
        config2.maxPosition = 1;
        config2.homePosition = Constants.SWEEPER_MIDDLE_SERVO_OPEN_POS;
        config2.zeroDegreePosition = 0.5;

        config3 = new ServoComponentConfig(this);
        config3.servoName = "sweeperEnd";
        config3.maxIncrement = 0.006;
        config3.minPosition = 0;
        config3.maxPosition = 1;
        config3.homePosition = Constants.SWEEPER_END_SERVO_OPEN_POS;
        config3.zeroDegreePosition = 0.5;

        config4 = new ServoComponentConfig(this);
        config4.servoName = "specimenBrace";
        config4.maxIncrement = 0.006;
        config4.minPosition = 0;
        config4.maxPosition = 1;
        config4.homePosition = 0.5;
        config4.zeroDegreePosition = 0.5;

    }

    @Override
    public void initBot() {
        super.initBot();

        this.sweeperBase = new ServoComponent(config1);
        this.sweeperBase.init();

        this.sweeperMiddle = new ServoComponent(config2);
        this.sweeperMiddle.init();

        this.sweeperEnd = new ServoComponent(config3);
        this.sweeperEnd.init();

        this.specimenBrace = new ServoComponent(config4);
        this.specimenBrace.init();

        servo = sweeperBase;
        config = config1;


        this.addGp1_Y_PressHandler(event -> {

            if (toggle == 0) {
                toggle = 1;
                currentServo = "Base Servo";
                servo = sweeperBase;
                config = config1;
            } else if (toggle == 1) {
                toggle = 2;
                currentServo = "Middle Servo";
                servo = sweeperMiddle;
                config = config2;
            } else if (toggle == 2){
                toggle = 3;
                currentServo = "End Servo";
                servo = sweeperEnd;
                config = config3;
            } else {
                toggle = 0;
                currentServo = "SpecimenBrace Servo";
                servo = specimenBrace;
                config = config4;
            }
        });

        this.addGp1_Dpad_Down_PressHandler(new Gp1_Dpad_Down_PressHandler() {
            public void onGp1_Dpad_Down_Press(Gp1_Dpad_Down_PressEvent event) {


                double newPos = servo.getPosition() - gamePadIncrement;

                if (newPos < config.minPosition) newPos = config.minPosition;
                if (newPos > config.maxPosition) newPos = config.maxPosition;

                servo.setPosition(newPos);



            }
        });

        this.addGp1_Dpad_Up_PressHandler(new Gp1_Dpad_Up_PressHandler() {
            public void onGp1_Dpad_Up_Press(Gp1_Dpad_Up_PressEvent event) {
                double newPos = servo.getPosition() + gamePadIncrement;

                if (newPos < config.minPosition) newPos = config.minPosition;
                if (newPos > config.maxPosition) newPos = config.maxPosition;

                servo.setPosition(newPos);
            }
        });

        this.addGp1_A_PressHandler(event -> {

            if (servo.getPosition() >= 0.5) {
                servo.setPosition(config.minPosition);
            }
            else {
                servo.setPosition(config.maxPosition);
            }

        });

        this.addGp1_X_PressHandler(event -> {

            servo.setPosition(config.zeroDegreePosition);

        });

        this.addGp1_LeftStick_X_Handler(event -> {

            if (servo.getPosition() < 1 && servo.getPosition() > 0) {
                servo.setPosition(servo.getPosition() + (config.maxIncrement * event.getPosition()));
            }
        });



    }

    /**
     *
     */
    @Override
    public void go() {


    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        telemetry.addLine("Current Servo: " + currentServo);
        telemetry.addData("Base Servo Pos: ", sweeperBase.getPosition());
        telemetry.addData("Middle Servo Pos: ", sweeperMiddle.getPosition());
        telemetry.addData("End Servo Pos: ", sweeperEnd.getPosition());
        telemetry.addData("Specimen Brace Pos: ", specimenBrace.getPosition());
        telemetry.update();
    }
}