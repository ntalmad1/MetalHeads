package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;

public class MotorToPosition extends AbstractAction {
    // checks if the lift motor has been powered on
    private boolean initialized = false;
    private int targetPosition;
    private EncodedMotor motor;
    private ElapsedTime runtime = new ElapsedTime();
    private boolean flag = false;
    private Integer timeout;
    private boolean powerOff;

    /**
     * Constructor
     * @param motor
     */
    public MotorToPosition(EncodedMotor motor, int targetPosition) {
        this(motor, targetPosition, false);
    }

    public MotorToPosition(EncodedMotor motor, int targetPosition, boolean powerOff) {
        this(motor, targetPosition, powerOff, 250);
    }

    public MotorToPosition(EncodedMotor motor, int targetPosition, boolean powerOff, Integer timeout) {
        this.motor = motor;
        this.targetPosition = targetPosition;
        this.timeout = timeout;
        this.powerOff = powerOff;
    }



    // actions are formatted via telemetry packets as below
    @Override
    public boolean run() {

        // powers on motor, if it is not on
        if (!initialized) {
            motor.setTargetPosition(targetPosition);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(1);
            initialized = true;
        }

        double pos = motor.getCurrentPosition();

        if (!flag) {
            if ((pos > targetPosition -20) && (pos < targetPosition + 20)) {
                flag = true;
                runtime.reset();
            }
        }

        if (flag) {
            if (timeout != null) {
                if (runtime.milliseconds() >= timeout) {
                    if (powerOff) motor.setPower(0);
                    return STOP;
                }
            } else {
                if (powerOff) motor.setPower(0);
                return STOP;
            }
        }

        return CONTIUE;

    }
}
