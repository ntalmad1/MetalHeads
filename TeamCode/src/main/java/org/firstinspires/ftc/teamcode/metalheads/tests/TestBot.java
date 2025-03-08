package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.encodedmotor.MotorToPosition;
import org.firstinspires.ftc.teamcode.metalheads.compbot.CompBotConfig;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

/**
 *
 */
@TeleOp(name = "TestBot", group = "Tests")
public class TestBot extends IsaacBot {

    /**
     */
    private EncodedMotor mainBoom;

    /**
     * Constructor
     *
     */
    public TestBot () {
        super();

        CompBotConfig compBotConfig = new CompBotConfig(this);

        mainBoom = new EncodedMotor(compBotConfig.bigArmConfig.mainBoomConfig);
    }

    /**
     *
     */
    @Override
    public void initBot () {
        super.initBot();

        this.mainBoom.init();

        this.mainBoom.addGp1_Left_Bumper_UpHandler(event -> {
            this.runAction(new MotorToPosition(this.mainBoom, Constants.MAIN_BOOM_MAX_TICS));
        });

        this.mainBoom.addGp1_Right_Bumper_UpHandler(event -> {
            this.runAction(new MotorToPosition(this.mainBoom, Constants.MAIN_BOOM_MIN_TICS));
        });
    }

    /**
     *
     */
    @Override
    public void go () {

    }

    /**
     *
     */
    @Override
    public void run () {
        super.run();

        this.mainBoom.run();

        telemetry.addData("M0 Target Pos: ", this.mainBoom.getTargetPosition());
        telemetry.addData("M0 Current Pos: ", this.mainBoom.getCurrentPosition());
        telemetry.addData("M0 Power: ", this.mainBoom.getPower());
        telemetry.addData("M0 Mode: ", this.mainBoom.getMotor().getMode());
        telemetry.addData("M0 Brake: ", this.mainBoom.getMotor().getZeroPowerBehavior());
        telemetry.addData("M0 Busy: ", this.mainBoom.getMotor().isBusy());
        telemetry.addLine("-----");
        telemetry.addData("M1 Target Pos: ", this.mainBoom.getSecondaryMotor().getTargetPosition());
        telemetry.addData("M1 Current Pos: ", this.mainBoom.getSecondaryMotor().getCurrentPosition());
        telemetry.addData("M1 Power: ", this.mainBoom.getSecondaryMotor().getPower());
        telemetry.addData("M1 Mode: ", this.mainBoom.getSecondaryMotor().getMode());
        telemetry.addData("M1 Brake: ", this.mainBoom.getSecondaryMotor().getZeroPowerBehavior());
        telemetry.addData("M1 Busy: ", this.mainBoom.getSecondaryMotor().isBusy());

        telemetry.update();
    }

}
