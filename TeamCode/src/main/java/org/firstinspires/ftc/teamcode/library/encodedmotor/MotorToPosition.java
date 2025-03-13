package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

public class MotorToPosition extends AbstractAction {

    /**
     */
    private boolean initialized = false;

    /**
     */
    private int targetPosition;

    /**
     */
    private EncodedMotor motor;

    /**
     */
    private Integer buffer;

    /**
     */
    private boolean powerOff;

    /**
     */
    private ElapsedTime runtime = new ElapsedTime();

    /**
     */
    private boolean flag;

    /**
     */
    private Integer timeout;

    /**
     * Constructor
     * @param motor
     */
    public MotorToPosition(EncodedMotor motor, int targetPosition) {
        this(motor, targetPosition, true);
    }

    public MotorToPosition(EncodedMotor motor, int targetPosition, boolean powerOff) {

        this(motor, targetPosition, powerOff, 25);
    }

    public MotorToPosition(EncodedMotor motor, int targetPosition, boolean powerOff, Integer buffer) {
        this.motor = motor;
        this.targetPosition = targetPosition;
        this.buffer = buffer;
        this.powerOff = powerOff;
        this.timeout = 110;
    }



    @Override
    public boolean run() {

        // powers on motor, if it is not on
        if (!initialized) {
            motor.setTargetPosition(targetPosition);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(1);

            if (powerOff) {
                flag = false;
            } else flag = true;

            initialized = true;
        }

        if (!flag) {
            double pos = motor.getCurrentPosition();
            if ((pos > targetPosition - buffer) && (pos < targetPosition + buffer)) {
                flag = true;
                runtime.reset();
            }
        } else {
            if (powerOff) {
                if (runtime.milliseconds() > timeout) {
                    motor.setPower(0);
                    return STOP;
                }
            } else {
                double pos = motor.getCurrentPosition();
                if ((pos > targetPosition - buffer) && (pos < targetPosition + buffer)) {
                    return STOP;
                }
            }
        }
        return CONTIUE;
    }
}
