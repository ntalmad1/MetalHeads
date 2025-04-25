package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.action.InstantActionImpl;
import org.firstinspires.ftc.teamcode.library.action.ParallelActionImpl;
import org.firstinspires.ftc.teamcode.library.action.SequentialActionImpl;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.library.encodedmotor.MotorToPosition;

/**
 *
 */
public class ActionFactory {

    /**
     */
    private CompBot compBot;

    /**
     * Contructor
     */
    public ActionFactory(CompBot compBot) {
        this.compBot = compBot;
    }

    /**
     * @return
     */
//    public AbstractAction initPos() {
//        return new SequentialActionImpl(
//            new InstantActionImpl(() -> { ActionFactory.this.compBot.setArmPos(CompBot.ArmPos.INIT_READY); }),
//            new MotorToPosition(this.compBot.bigArm.viperSlide,Constants.VIPER_SLIDES_MIN_TICS),
//            new InstantActionImpl(() -> {
//                this.compBot.littleArm.doubleServos.setPosition(Constants.DOUBLE_SERVOS_INIT_POS);
//                this.compBot.littleArm.middleServo.setPosition(Constants.MIDDLE_SERVO_INIT_POS);
//                this.compBot.littleArm.clawRotator.setPosition(Constants.CLAW_ROTATOR_INIT_POS);
//                this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_INIT_POS);
//            }),
//            new WaitAction(200),
//            new MotorToPosition(this.compBot.bigArm.mainBoom, Constants.MAIN_BOOM_MIN_TICS)
//
//        );
//    }


}