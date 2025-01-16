package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;

public class ViperSlideToSampleGrab implements Action {
    // checks if the lift motor has been powered on
    private boolean initialized = false;
    private EncodedMotor viperSlide;
    private int extraTicks;
    private int sample;

    /**
     * Constructor
     * @param viperSlide
     */
    public ViperSlideToSampleGrab(EncodedMotor viperSlide, int sample) {
        this(viperSlide, sample, 0);
    }

    public ViperSlideToSampleGrab(EncodedMotor viperSlide, int sample, int extraTicks) {
        this.viperSlide = viperSlide;
        this.sample = sample;
        this.extraTicks = extraTicks;
    }

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        int targetPosition = extraTicks;

        if (sample == 1) {
            targetPosition += 893;

        } else if (sample == 2) {
            targetPosition += 1610;

        } else if (sample == 3) {
            targetPosition += 2065;
        }

        // powers on motor, if it is not on
        if (!initialized) {
            viperSlide.setTargetPosition(targetPosition);
            viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viperSlide.setPower(1);
            initialized = true;
        }

        if () {}

        // checks Slides current position
        double pos = viperSlide.getCurrentPosition();
        packet.put("viperSlidePos", pos);

        if ((pos > targetPosition - 10) && (pos < targetPosition + 10)) {
            return STOP;

        } else {

            return CONTINUE;
        }
    }
}
