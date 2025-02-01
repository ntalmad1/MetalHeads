package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;

public class MainBoomToPosition extends AbstractAction {
    // checks if the lift motor has been powered on
    private boolean initialized = false;
    private int targetPosition;
    private EncodedMotor mainBoom;
    private ElapsedTime runtime = new ElapsedTime();
    private boolean flag = false;
    private Integer timeout;

    /**
     * Constructor
     * @param mainBoom
     */
    public MainBoomToPosition(EncodedMotor mainBoom, int targetPosition, Integer timeout) {
        this.mainBoom = mainBoom;
        this.targetPosition = targetPosition;
        this.timeout = timeout;
    }

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run() {

        // powers on motor, if it is not on
        if (!initialized) {
            mainBoom.setTargetPosition(targetPosition);
            mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            mainBoom.setPower(1);
            initialized = true;
        }

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

        return CONTIUE;

    }
}
