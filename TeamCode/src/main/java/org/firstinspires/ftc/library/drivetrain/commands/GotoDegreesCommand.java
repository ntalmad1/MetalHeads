//package org.firstinspires.ftc.library.drivetrain.commands;
//
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//import org.firstinspires.ftc.library.component.command.AbstractSynchronousCommand;
//import org.firstinspires.ftc.library.component.command.ICommand;
//import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
//import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
//import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
//import org.firstinspires.ftc.library.utility.Direction;
//
///**
// *
// */
//public class GotoDegreesCommand extends AbstractDriveTrainGyroTurnCommand {
//
//    protected Direction direction;
//
//
//
//    /**
//     * Constructor
//     *
//     * 0 - straight forwards from when the imu was last initialized
//     * 180 - opposite of forwards from when the imu was last initialized
//     * 90 - left
//     * -90 - right
//     *
//     * @param driveTrain
//     * @param startPower
//     * @param maxPower
//     * @param degrees
//     */
//    public GotoDegreesCommand (SimpleDriveTrain driveTrain, double startPower, double maxPower, double degrees) {
//        super(driveTrain, startPower, maxPower, degrees, Orientation.ABSOLUTE);
//
//        this.direction = direction;
//    }
//
//
//    @Override
//    public void init () {
//
//        this.driveTrain.resetMotorGroup();
//
//
//        double yaw = this.driveTrain.getRobot().getYaw();
//
//        double targetDegrees = this.degrees - Math.abs(yaw);
//
//        if (this.getOrientation().equals(Orientation.ABSOLUTE)) {
//            targetDegrees = degrees;
//        }
//
//        if (targetDegrees < 0) {
//            targetDegrees = Math.abs(targetDegrees);
//            direction = direction.invert();
//        }
//
//        if (direction.equals(Direction.RIGHT)) {
//            this.degrees = -targetDegrees;
//
//            this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
//            this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);
//            this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
//            this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);
//
//            this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        }
//        else {
//            this.degrees = targetDegrees;
//
//            this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
//            this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);
//            this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
//            this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);
//
//            this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        }
//
//
//        super.init();
//
//    }
//
//}
