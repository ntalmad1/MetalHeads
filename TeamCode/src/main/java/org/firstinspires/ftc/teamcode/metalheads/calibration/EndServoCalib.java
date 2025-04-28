package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressHandler;

/**
 */
@TeleOp(name="EndServoCalib", group="Calibration")
//@Disabled
public class EndServoCalib extends IsaacBot {

    /**
     */
    private double yStickIncrement;

    /**
     */
    private double gamePadIncrement;

    /**
     */
    private String servoName;

    private double servoInitPos;

    private double servoMinPos;

    private double servoMaxPos;

    /**
     */
    private Servo servo;

    @Override
    public void initBot() {
        yStickIncrement = 0.0006;
        gamePadIncrement = 0.002;
        servoName = "end";
        servoInitPos = 0.5;
        servoMinPos = 0.0;
        servoMaxPos = 0.783;

        servo = this.hardwareMap.get(Servo.class, servoName);
        servo.resetDeviceConfigurationForOpMode();

        this.addGp1_Dpad_Down_PressHandler(new Gp1_Dpad_Down_PressHandler() {
            public void onGp1_Dpad_Down_Press(Gp1_Dpad_Down_PressEvent event) {
                double newPos = servo.getPosition() - gamePadIncrement;

                if (newPos < servoMinPos) newPos = servoMinPos;
                if (newPos > servoMaxPos) newPos = servoMaxPos;

                servo.setPosition(newPos);
            }
        });

        this.addGp1_Dpad_Up_PressHandler(new Gp1_Dpad_Up_PressHandler() {
            public void onGp1_Dpad_Up_Press(Gp1_Dpad_Up_PressEvent event) {
                double newPos = servo.getPosition() + gamePadIncrement;

                if (newPos < servoMinPos) newPos = servoMinPos;
                if (newPos > servoMaxPos) newPos = servoMaxPos;

                servo.setPosition(newPos);
            }
        });

        this.addGp1_A_PressHandler(event -> {

            if (servo.getPosition() > 0.5) {
                servo.setPosition(servoMinPos);
            }
            else {
                servo.setPosition(servoMaxPos);
            }

        });
    }

    /**
     *
     */
    @Override
    public void go() {
        servo.setPosition(this.servoInitPos);
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        if (this.gamepad1.left_stick_y != 0) {

            double ly = this.gamepad1.left_stick_y;

            double newPos = servo.getPosition() + (ly > 0 ? -yStickIncrement : yStickIncrement);

            if (newPos < servoMinPos) newPos = servoMinPos;
            if (newPos > servoMaxPos) newPos = servoMaxPos;

            servo.setPosition(newPos);
        }

        telemetry.addData("Middle Pos: ", "%.3f", servo.getPosition());
        telemetry.update();
    }
}
