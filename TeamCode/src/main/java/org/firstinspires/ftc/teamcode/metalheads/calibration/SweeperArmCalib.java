package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 */
@TeleOp(name="SweeperArmCalib", group="Calibration")
//@Disabled
public class SweeperArmCalib extends IsaacBot {

    private ServoComponent sweeperBottom;
    private ServoComponent sweeperMiddle;
    private ServoComponent sweeperTop;
    private ServoComponent servo;

    private ServoComponentConfig config1;
    private ServoComponentConfig config2;
    private ServoComponentConfig config3;

    private  int toggle = 0;
    private double gamePadIncrement = 0.006;

    public SweeperArmCalib() {
        super();

        config1 = new ServoComponentConfig(this);
        config1.servoName = "sweeperBottom";
        config1.maxIncrement = 0.006;
        config1.minPosition = 0;
        config1.maxPosition = 1;
        config1.homePosition = 0;
        config1.zeroDegreePosition = 0.5;

        config2 = new ServoComponentConfig(this);
        config2.servoName = "sweeperMiddle";
        config2.maxIncrement = 0.006;
        config2.minPosition = 0;
        config2.maxPosition = 1;
        config2.homePosition = 0;
        config2.zeroDegreePosition = 0.5;

        config3 = new ServoComponentConfig(this);
        config3.servoName = "sweeperTop";
        config3.maxIncrement = 0.006;
        config3.minPosition = 0;
        config3.maxPosition = 1;
        config3.homePosition = 0;
        config3.zeroDegreePosition = 0.5;


    }

    @Override
    public void initBot() {
        super.initBot();

        this.sweeperBottom = new ServoComponent(config1);
        this.sweeperBottom.init();

        this.sweeperMiddle = new ServoComponent(config1);
        this.sweeperMiddle.init();

        this.sweeperTop = new ServoComponent(config1);
        this.sweeperTop.init();

        servo = sweeperBottom;


        this.addGp1_A_PressHandler(event -> {

            if (toggle == 0) {
                toggle = 1;
                servo = sweeperMiddle;
            } else if (toggle == 1) {
                toggle = 2;
                servo = sweeperTop;
            } else {
                toggle = 0;
                servo = sweeperBottom;
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

        String servo = "Sweeper Bottom";
        telemetry.addData(servo +" Pos: ", "%.3f", sweeperBottom.getPosition());
        telemetry.update();
    }
}