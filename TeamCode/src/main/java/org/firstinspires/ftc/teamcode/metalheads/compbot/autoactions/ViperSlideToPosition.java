package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;

public class ViperSlideToPosition implements Action {

    /**
     */
    private boolean initialized = false;

    /**
     */
    private int targetPosition;

    /**
     */
    private EncodedMotor viperSlide;

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
     * @param viperSlide
     */
    public ViperSlideToPosition(EncodedMotor viperSlide, int targetPosition, Integer timeout) {
        this.viperSlide = viperSlide;
        this.targetPosition = targetPosition;
        this.timeout = timeout;
    }

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        // powers on motor, if it is not on
        if (!initialized) {
            viperSlide.setTargetPosition(targetPosition);
            viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viperSlide.setPower(1);
            initialized = true;
        }

        double pos = viperSlide.getCurrentPosition();

        if (!flag) {
            if ((pos > targetPosition -20) && (pos < targetPosition + 20)) {
                flag = true;
                runtime.reset();
            }
        }

        if (flag) {
            if (timeout != null) {
                if (runtime.milliseconds() >= timeout) {
                    return STOP;
                }
            } else {
                return STOP;
            }
        }

        return CONTINUE;

    }
}
