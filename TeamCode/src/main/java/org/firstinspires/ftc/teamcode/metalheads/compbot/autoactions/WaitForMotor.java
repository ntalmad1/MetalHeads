package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

public class WaitForMotor implements Action {

    /**
     */
    private int targetPosition;

    /**
     */
    private int buffer;

    /**
     */
    private EncodedMotor motor;


    /**
     * Constructor
     * @param motor
     */
    public WaitForMotor(EncodedMotor motor, int targetPosition) {
        this(motor, targetPosition, 8);
    }


    public WaitForMotor(EncodedMotor motor, int targetPosition, int buffer) {
        this.motor = motor;
        this.targetPosition = targetPosition;
        this.buffer = buffer;
    }



    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        double pos = motor.getCurrentPosition();

        if ((pos > targetPosition -buffer) && (pos < targetPosition + buffer)) {
            return STOP;
        } else return CONTINUE;
    }
}
