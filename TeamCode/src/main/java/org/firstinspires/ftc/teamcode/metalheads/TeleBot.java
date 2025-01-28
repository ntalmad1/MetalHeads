package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.action.InstantActionImpl;
import org.firstinspires.ftc.teamcode.library.action.SequentialActionImpl;
import org.firstinspires.ftc.teamcode.metalheads.compbot.CompBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;
import org.firstinspires.ftc.teamcode.metalheads.compbot.DriveTrainPositionStruct;

@TeleOp(name = "TeleBot", group = "Comp")
public class TeleBot extends CompBot {

    /**
     * Constructor
     *
     */
    public TeleBot() {
        super();

        this.setConfig(new TeleBotConfig(this));
        this.configureBot();
    }

    @Override
    public void initBot() {
        super.initBot();


//        AbstractAction action = new SequentialActionImpl(
//                this.bigArm.mainBoom.gotoPositionAction(525),
//                new InstantActionImpl(() -> { this.setArmPos(ArmPos.INIT); })
//        );
//
//        TelemetryPacket tp = new TelemetryPacket();
//        while (action.run(tp) == AbstractAction.CONTIUE){}
    }

    /**
     *
     */
    @Override
    protected void configureBot() {
        super.configureBot();

        // initialize roadrunner from last op pose
        this.initialPose = DriveTrainPositionStruct.pose == null ? new Pose2d(8, -61, Math.toRadians(90)) : DriveTrainPositionStruct.pose;


    }
}
