package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitMilliseconds implements Action {

    /**
     */
    private boolean initialized = false;

    /**
     */
    private ElapsedTime runtime = new ElapsedTime();

    /**
     */
    private int duration;


    /**
     * Constructor
     * @param duration
     */
    public WaitMilliseconds(int duration) {
        this.duration = duration;
    }



    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        // powers on motor, if it is not on
        if (!initialized) {
            runtime.reset();
            initialized = true;
        }



        if (runtime.milliseconds() >= duration)
        {
            return STOP;

        } else {

            return CONTINUE;

        }

    }
}
