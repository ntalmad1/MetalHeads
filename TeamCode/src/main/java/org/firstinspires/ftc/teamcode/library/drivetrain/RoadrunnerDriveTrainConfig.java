package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 *
 */
public class RoadrunnerDriveTrainConfig
{
    /**
     */
    public IsaacBot robot;

    /**
     *
     */
    public double yawOffset = 0;

    /**
     */
    public boolean alwaysForwards = false;

    /**
     */
    public String imuName;

    /**
     */
    public boolean debug = false;

    /**
     *
     * @return True if debug else false
     */
    public boolean isDebug ()
    {
        return this.debug;
    }

    /**
     *
     * @param robot
     */
    public RoadrunnerDriveTrainConfig(IsaacBot robot) {
        super();

        this.robot = robot;
    }

}
