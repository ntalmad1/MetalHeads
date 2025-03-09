package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import com.acmerobotics.roadrunner.Action;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

public class MotorToPositionRR implements Action {

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
    private ElapsedTime runtime = new ElapsedTime();

    /**
     */
    private boolean flag = false;

    /**
     */
    private Integer timeout;

    /**
     */
    private boolean powerOff;


    /**
     * Constructor
     * @param motor
     */
    public MotorToPositionRR(EncodedMotor motor, int targetPosition) {
        this(motor, targetPosition, true);
    }

    public MotorToPositionRR(EncodedMotor motor, int targetPosition, boolean powerOff) {

        this(motor, targetPosition, powerOff,
                powerOff
                    ? Constants.MAIN_BOOM_TIMEOUT_DEFAULT
                    : Constants.VIPER_SLIDES_TIMEOUT_DEFAULT);
    }

    public MotorToPositionRR(EncodedMotor motor, int targetPosition, boolean powerOff, Integer timeout) {
        this.motor = motor;
        this.targetPosition = targetPosition;
        this.timeout = timeout;
        this.powerOff = powerOff;
    }



    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

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

        return CONTINUE;

    }
}
