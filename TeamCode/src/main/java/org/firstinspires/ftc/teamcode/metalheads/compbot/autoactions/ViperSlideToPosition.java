package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import com.acmerobotics.roadrunner.Action;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

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
     */
    private boolean powerOff;


    /**
     * Constructor
     * @param viperSlide
     */
    public ViperSlideToPosition(EncodedMotor viperSlide, int targetPosition) {
        this(viperSlide, targetPosition, false);
    }

    public ViperSlideToPosition(EncodedMotor viperSlide, int targetPosition, boolean powerOff) {
        this(viperSlide, targetPosition, powerOff, Constants.VIPER_SLIDES_TIMEOUT_DEFAULT);
    }

    public ViperSlideToPosition(EncodedMotor viperSlide, int targetPosition, boolean powerOff, Integer timeout) {
        this.viperSlide = viperSlide;
        this.targetPosition = targetPosition;
        this.powerOff = powerOff;
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
                    if (powerOff) this.viperSlide.setPower(0);
                    return STOP;
                }
            } else {
                if (powerOff) this.viperSlide.setPower(0);
                return STOP;
            }
        }

        return CONTINUE;

    }
}
