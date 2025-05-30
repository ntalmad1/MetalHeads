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
        this.clawRotator.run();
        this.clawPincher.run();

        if (this.isDebug()) {
            telemetry.addData("Base Servo:", this.baseServo.getPosition());
            telemetry.addData("Claw Rotator:", this.clawRotator.getPosition());
            telemetry.addData("Claw Pincher:", this.clawPincher.getPosition());

        }
    }
}
