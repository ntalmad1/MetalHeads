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
    private Integer buffer;

    /**
     */
    private boolean powerOff;

    /**
     */
    private boolean flag = false;

    /**
     */
    private ElapsedTime runtime = new ElapsedTime();

    /**
     */
    private Integer timeout;


    /**
     * Constructor
     * @param motor
     */
    public MotorToPositionRR(EncodedMotor motor, int targetPosition) {
        this(motor, targetPosition, true);
    }

    public MotorToPositionRR(EncodedMotor motor, int targetPosition, boolean powerOff) {

        this(motor, targetPosition, powerOff, 20);
    }

    public MotorToPositionRR(EncodedMotor motor, int targetPosition, boolean powerOff, Integer buffer) {
        this.motor = motor;
        this.targetPosition = targetPosition;
        this.buffer = buffer;
        this.powerOff = powerOff;
        this.timeout = 125;
    }


    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

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
        return CONTINUE;
    }
}
