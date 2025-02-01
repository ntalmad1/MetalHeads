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

public class MainBoomToPosition implements Action {

    /**
     */
    private boolean initialized = false;

    /**
     */
    private int targetPosition;

    /**
     */
    private EncodedMotor mainBoom;

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
     * @param mainBoom
     */
    public MainBoomToPosition(EncodedMotor mainBoom, int targetPosition, Integer timeout) {
        new MainBoomToPosition(mainBoom, targetPosition, timeout, true);
    }

    public MainBoomToPosition(EncodedMotor mainBoom, int targetPosition, Integer timeout, boolean powerOff) {
        this.mainBoom = mainBoom;
        this.targetPosition = targetPosition;
        this.timeout = timeout;
        this.powerOff = powerOff;
    }

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        // powers on motor, if it is not on
        if (!initialized) {
            mainBoom.setTargetPosition(targetPosition);
            mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            mainBoom.setPower(1);
            initialized = true;
        }

        // checks lift's current position
        double pos = mainBoom.getCurrentPosition();

        if (!flag)
        {
            if ((pos > targetPosition -20) && (pos < targetPosition + 20))
            {
                flag = true;
                runtime.reset();
            }
        }

        if (flag)
        {
            if (timeout != null) {
                if (runtime.milliseconds() >= timeout)
                {
                    mainBoom.setPower(0);
                    return STOP;
                }
            }
            else {
                mainBoom.setPower(0);
                return STOP;
            }
        }

        return CONTINUE;

    }
}
