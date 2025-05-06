package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.utility.GridUtils;
import org.firstinspires.ftc.teamcode.library.utility.Point;
import org.firstinspires.ftc.teamcode.roadrunner.Drawing;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.TankDrive;

/**
 *
 */
public class RoadrunnerDriveTrain extends Component
{
    /**
     */
    private RoadrunnerDriveTrainConfig config;

    /**
     */
    private TankDrive drive;

    /**
     * Constructor
     *
     * @param roadrunnerDriveTrainConfig
     */
    public RoadrunnerDriveTrain(RoadrunnerDriveTrainConfig roadrunnerDriveTrainConfig)
    {
        super(roadrunnerDriveTrainConfig.robot);

        this.config = roadrunnerDriveTrainConfig;
    }

    /**
     *
     * @return
     */
    public RoadrunnerDriveTrainConfig getConfig() {
        return config;
    }

    /**
     *
     */
    public void init (Pose2d initialPose)
    {
        super.init();

        this.robot.setImuName(this.getConfig().imuName);
        this.robot.initImu(this.getConfig().imuName);

        drive = new RoadrunnerDrive(this.robot.hardwareMap, initialPose);
    }

    /**
     *
     */
    public void run ()
    {
        double yaw = -(this.getYaw() + getConfig().yawOffset);

        float leftY = -this.robot.gamepad1.left_stick_y;
        float leftX = this.robot.gamepad1.left_stick_x;
        float rx = this.robot.gamepad1.right_stick_x;

        Point newPoint = GridUtils.rotatePointByDegrees(leftX,leftY,yaw);
        double x = newPoint.getX();
        double y = newPoint.getY();

        double drive = y; // Forward/backward
        double turn = rx;  // Turning

        // Arcade drive calculations
        double leftPower = drive + turn;
        double rightPower = drive - turn;



        Powers powers = new Powers();
        powers.leftPower = leftPower;
        powers.rightPower = rightPower;

        this.drive.setDrivePowers(powers);
        this.drive.updatePoseEstimate();

        if (this.isDebug()) {
            telemetry.addData("Roadrunner x", this.drive.pose.position.x);
            telemetry.addData("Roadrunner y", this.drive.pose.position.y);
            telemetry.addData("Roadrunner heading (deg)", Math.toDegrees(this.drive.pose.heading.toDouble()));

            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), this.drive.pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }

    /**
     *
     * @return
     */
    protected double getYaw () {
        if (this.getConfig().alwaysForwards) {
            return this.robot.getYaw();
        }
        else {
            return 0;
        }
    }

    /**
     *
     * @return
     */
    public TankDrive getDrive() {
        return this.drive;
    }
}
