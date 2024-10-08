package org.firstinspires.ftc.library.drivetrain;

import org.firstinspires.ftc.library.IsaacBot;

public class AbstractDriveTrainConfig
{
    /**
     */
    public IsaacBot robot;

    /**
     */
    public String leftFrontDeviceName;
    public String rightFrontDeviceName;
    public String rightRearDeviceName;
    public String leftRearDeviceName;

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
}
