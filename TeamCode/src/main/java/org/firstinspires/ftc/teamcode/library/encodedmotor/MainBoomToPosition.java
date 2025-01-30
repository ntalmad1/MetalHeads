package org.firstinspires.ftc.teamcode.library.encodedmotor;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;

public class MainBoomToPosition extends AbstractAction {
    // checks if the lift motor has been powered on
    private boolean initialized = false;
    private int position;
    private EncodedMotor mainBoom;
    private ElapsedTime runtime = new ElapsedTime();
    private double timer = 0.5;
    private boolean flag = false;

    /**
     * Constructor
     * @param mainBoom
     */
    public MainBoomToPosition(EncodedMotor mainBoom, int targetposition) {
        this.mainBoom = mainBoom;
        this.position = targetposition;
    }

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run() {
        int targetPosition = position;

        // powers on motor, if it is not on
        if (!initialized) {
            mainBoom.setTargetPosition(targetPosition);
            mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            mainBoom.setPower(1);
            initialized = true;
        }


        double pos = mainBoom.getCurrentPosition();
        if (!flag) {
            if ((pos > targetPosition -20) && (pos < targetPosition + 20)) {
                flag = true;
                runtime.reset();
            }
        }

        if (flag && (runtime.seconds() >= timer)) {
            mainBoom.setPower(0);
            return STOP;
        } else {
            return CONTINUE;
        }
    }
}
