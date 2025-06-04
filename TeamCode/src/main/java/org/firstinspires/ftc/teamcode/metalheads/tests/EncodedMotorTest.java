package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

@TeleOp(name="EncodedMotorTest", group="Tests")
@Disabled
public class EncodedMotorTest extends IsaacBot {

    /**
     */
    private EncodedMotor motor;

    /**
     */
    private String motorName;

    public EncodedMotorTest() {
        super();

        EncodedMotorConfig config = new EncodedMotorConfig(this);
        config.brakeOn = false;
        config.maxTics = 100000;
        config.minTics = -100000;
        config.motorName = "rightWorm";
        config.control = Control.Gp1_LeftStickY;
        config.scale = 100;

        config.isDualMotor = true;
        config.secondaryMotorName = "leftWorm";

        motor = new EncodedMotor(config);
    }

    /**
     *
     */
    public void initBot() {
        super.initBot();

        this.motor.init();
    }

    /**
     *
     */
    @Override
    public void go() {

        //this.motor.gotoPosition(5000, 1);

    }

    /**
     *
     */
    public void run() {
        super.run();

        this.motor.run();

        this.telemetry.addData("Direction:", this.motor.getDirection());
        this.telemetry.addData("Position:", this.motor.getCurrentPosition());
        this.telemetry.addData("TargetPosition:", this.motor.getTargetPosition());
        this.telemetry.addData("Power:", this.motor.getPower());
        this.telemetry.update();
    }



}
