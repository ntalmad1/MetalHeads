package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.motor.EncodedMotor;
import org.firstinspires.ftc.library.motor.EncodedMotorConfig;
import org.firstinspires.ftc.library.utility.Controls;

@TeleOp(name="Linear Actuator Component", group="Linear OpMode")
//@Disabled
public class LinearActuatorComponent extends IsaacBot {

    /**
     */
    private EncodedMotor linAct;

    /**
     *
     */
    public LinearActuatorComponent () {
        super();

        EncodedMotorConfig config = new EncodedMotorConfig(this);
        config.motorName = "actuatorMotor";
        config.minTics = 100;
        config.maxTics = 3400;
        config.increment = 200;
        config.debug = true;
        config.control = Controls.Gp2_RightStickX;

        this.linAct = new EncodedMotor(config);
    }

    /**
     *
     */
    public void initBot () {
        super.initBot();

        this.linAct.init();
    }

    /**
     *
     */
    public void go () {
        super.go();

        this.linAct.gotoPosition(1000);
        this.linAct.gotoPosition(50);
    }

    /**
     *
     */
    public void run () {
        super.run();

        this.linAct.run();
    }

}
