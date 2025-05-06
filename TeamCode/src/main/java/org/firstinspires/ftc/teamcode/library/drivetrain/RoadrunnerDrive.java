package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.TankDrive;

/**
 *
 */
public class RoadrunnerDrive extends TankDrive {

    /**
     *
     * @param hardwareMap
     * @param pose
     */
    public RoadrunnerDrive(HardwareMap hardwareMap, Pose2d pose) {
        super(hardwareMap, pose);
    }

    /**
     *
     * @param powers
     */
    public void setDrivePowers(Powers powers) {

        for (DcMotorEx m : leftMotors) {
            m.setPower(powers.leftPower);
        }
        for (DcMotorEx m : rightMotors) {
            m.setPower(powers.rightPower);
        }
    }
}
