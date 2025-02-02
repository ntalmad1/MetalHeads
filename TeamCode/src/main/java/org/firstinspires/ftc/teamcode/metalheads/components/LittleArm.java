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
    public ServoComponent doubleServos;

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
     */
    public ServoComponent sweeperBase;

    /**
     */
    public ServoComponent sweeperMiddle;

    /**
     */
    public ServoComponent sweeperEnd;

    /**
     * Constructor
     *
     * @param littleArmConfig
     */
    public LittleArm(LittleArmConfig littleArmConfig) {
        super(littleArmConfig.robot);

        this.config = littleArmConfig;

        this.doubleServos = new ServoComponent(this.config.doubleServosConfig);

        this.middleServo = new ServoComponent(this.config.middleServoConfig);

        this.clawRotator = new ServoComponent(this.config.clawRotatorConfig);

        this.clawPincher = new ServoComponent(this.config.clawPincherConfig);

        // sweeper
        this.sweeperBase = new ServoComponent(this.config.sweeperBaseConfig);
        this.sweeperMiddle = new ServoComponent(this.config.sweeperMiddleConfig);
        this.sweeperEnd = new ServoComponent(this.config.sweeperEndConfig);
    }

    /**
     *
     */
    @Override
    public void init() {
        super.init();

        this.doubleServos.init();
        this.middleServo.init();
        this.clawRotator.init();
        this.clawPincher.init();

        // sweeper
        this.sweeperBase.init();
        this.sweeperMiddle.init();
        this.sweeperEnd.init();
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        this.doubleServos.run();
        this.middleServo.run();
        this.clawRotator.run();
        this.clawPincher.run();

        // sweeper
        this.sweeperBase.run();
        this.sweeperMiddle.run();
        this.sweeperEnd.run();

        if (this.isDebug()) {
            telemetry.addData("Double Servos:", this.doubleServos.getPosition());
            telemetry.addData("Middle Servo:", this.middleServo.getPosition());
            telemetry.addData("Claw Rotator:", this.clawRotator.getPosition());
            telemetry.addData("Claw Pincher:", this.clawPincher.getPosition());

            // sweeper
            telemetry.addData("Sweeper Base", this.sweeperBase.getPosition());
            telemetry.addData("Sweeper Middle", this.sweeperMiddle.getPosition());
            telemetry.addData("Sweeper End", this.sweeperEnd.getPosition());
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


    /**
     *
     * @return
     */
    public Action openSweeperAction() {
        return new InstantActionImpl(() ->  {
            this.sweeperBase.setPosition(Constants.SWEEPER_BASE_SERVO_OPEN_POS);
            this.sweeperMiddle.setPosition(Constants.SWEEPER_MIDDLE_SERVO_OPEN_POS);
            this.sweeperEnd.setPosition(Constants.SWEEPER_END_SERVO_OPEN_POS);
        });
    }

    /**
     *
     * @return
     */
    public Action closeSweeperAction() {
        return new InstantActionImpl(() ->  {
            this.sweeperBase.setPosition(Constants.SWEEPER_BASE_SERVO_CLOSED_POS);
            this.sweeperMiddle.setPosition(Constants.SWEEPER_MIDDLE_SERVO_CLOSED_POS);
            this.sweeperEnd.setPosition(Constants.SWEEPER_END_SERVO_CLOSED_POS);
        });
    }
}
