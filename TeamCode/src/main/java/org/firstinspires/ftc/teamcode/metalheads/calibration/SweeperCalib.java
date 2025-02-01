package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 */
@TeleOp(name="SweeperBaseCalib", group="Calibration")
//@Disabled
public class SweeperCalib extends IsaacBot {

    private ServoComponent servo;

    private ServoComponentConfig config;

    private double gamePadIncrement = 0.006;


    public SweeperCalib() {
        super();

        config = new ServoComponentConfig(this);

        config.servoName = "sweeperBase";

        config.maxIncrement = 0.006;

        config.minPosition = 0;
        config.maxPosition = 1;

        config.homePosition = 0.016;
        config.zeroDegreePosition = 0.5;

        config.controllerInputMethod = Control.Gp2_LeftStickX;

    }

    @Override
    public void initBot() {
        super.initBot();

        this.servo = new ServoComponent(config);
        this.servo.init();
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

        telemetry.addData("Claw Rotator Pos: ", "%.3f", servo.getPosition());
        telemetry.update();
    }
}
