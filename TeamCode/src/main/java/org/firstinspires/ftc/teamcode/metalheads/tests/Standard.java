package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name="StandardTest", group="Tests")
@Disabled
public class Standard extends LinearOpMode {



    public void runOpMode() {


        waitForStart();
        while (this.opModeIsActive()) {

           telemetry.addData("RightTrigger: ", gamepad1.right_trigger);
           telemetry.addData("LeftTrigger: ", gamepad1.left_trigger);
           telemetry.update();

        }
    }
}