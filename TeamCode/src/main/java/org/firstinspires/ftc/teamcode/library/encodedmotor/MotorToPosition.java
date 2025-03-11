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
    private boolean flag = false;

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

        this(motor, targetPosition, powerOff,
                powerOff
                    ? Constants.MAIN_BOOM_TIMEOUT_DEFAULT
                    : Constants.VIPER_SLIDES_TIMEOUT_DEFAULT);
    }

    public MotorToPosition(EncodedMotor motor, int targetPosition, boolean powerOff, Integer buffer) {
        this.motor = motor;
        this.targetPosition = targetPosition;
        this.buffer = buffer;
        this.powerOff = powerOff;
        this.timeout = 120;
    }



    @Override
    public boolean run() {

        // powers on motor, if it is not on
        if (!initialized) {
            motor.setTargetPosition(targetPosition);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(1);
            initialized = true;
        }

        if (!flag) {
            double pos = motor.getCurrentPosition();
            if ((pos > targetPosition - buffer) && (pos < targetPosition + buffer)) {
                flag = true;
                runtime.reset();
            }
        } else {
            if (runtime.milliseconds() > timeout) {
                if (!powerOff) motor.setPower(0);
                return STOP;
            }
        }
        return CONTIUE;
    }
}
