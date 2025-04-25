package org.firstinspires.ftc.teamcode.metalheads.components;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.library.action.InstantActionImpl;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

/**
 *
 */
public class LittleArm extends Component {

    /**
     */
    public LittleArmConfig config;

    /**
     */
    public ServoComponent baseServo;

    /**
     */
    public ServoComponent middleServo;

    /**
     */
    public ServoComponent clawRotator;

    /**
     */
    public ServoComponent clawPincher;


    /**
     * Constructor
     *
     * @param littleArmConfig
     */
    public LittleArm(LittleArmConfig littleArmConfig) {
        super(littleArmConfig.robot);

        this.config = littleArmConfig;

        this.baseServo = new ServoComponent(this.config.baseServoConfig);

        this.middleServo = new ServoComponent(this.config.middleServoConfig);

        this.clawRotator = new ServoComponent(this.config.clawRotatorConfig);

        this.clawPincher = new ServoComponent(this.config.clawPincherConfig);
    }

    /**
     *
     */
    @Override
    public void init() {
        super.init();

        this.baseServo.init();
        this.middleServo.init();
        this.clawRotator.init();
        this.clawPincher.init();
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        this.baseServo.run();
        this.middleServo.run();
        this.clawRotator.run();
        this.clawPincher.run();

        if (this.isDebug()) {
            telemetry.addData("Double Servos:", this.baseServo.getPosition());
            telemetry.addData("Middle Servo:", this.middleServo.getPosition());
            telemetry.addData("Claw Rotator:", this.clawRotator.getPosition());
            telemetry.addData("Claw Pincher:", this.clawPincher.getPosition());

        }
    }

    /**
     *
     * @return
     */
    public Action closeClawAction() {
        return this.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_CLOSE_POS, 1);
    }

    /**
     *
     * @return
     */
    public Action openClawAction() {
        return this.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1);
    }
}
