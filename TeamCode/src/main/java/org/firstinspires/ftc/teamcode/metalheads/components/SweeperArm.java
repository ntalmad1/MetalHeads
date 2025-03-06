package org.firstinspires.ftc.teamcode.metalheads.components;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.library.action.InstantActionImpl;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

/**
 *
 */
public class SweeperArm extends Component {

    /**
     */
    public SweeperArmConfig config;

    /**
     */
    public ServoComponent baseServo;

    /**
     */
    public ServoComponent middleServo;

    /**
     */
    public ServoComponent endServo;

    /**
     */
    public ServoComponent specimenBrace;

    /**
     * Constructor
     *
     * @param sweeperArmConfig
     */
    public SweeperArm(SweeperArmConfig sweeperArmConfig) {
        super(sweeperArmConfig.robot);

        this.config = sweeperArmConfig;

        this.baseServo = new ServoComponent(this.config.baseServoConfig);

        this.middleServo = new ServoComponent(this.config.middleServoConfig);

        this.endServo = new ServoComponent(this.config.endServoConfig);

        this.specimenBrace = new ServoComponent(this.config.specimenBrace);
    }

    /**
     *
     */
    @Override
    public void init() {
        super.init();

        this.baseServo.init();
        this.middleServo.init();
        this.endServo.init();
        this.specimenBrace.init();
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        this.baseServo.run();
        this.middleServo.run();
        this.endServo.run();
        this.specimenBrace.run();

        if (this.isDebug()) {
            telemetry.addData("Sweeper Base Servo: ", this.baseServo.getPosition());
            telemetry.addData("Sweeper Middle Servo: ", this.middleServo.getPosition());
            telemetry.addData("Sweeper End Servo: ", this.endServo.getPosition());
            telemetry.addData("Specimen Brace: ", this.specimenBrace.getPosition());
        }
    }

    /**
     *
     * @return
     */
    public Action openSweeperAction() {
        return new InstantActionImpl(() ->  {
            this.baseServo.setPosition(Constants.SWEEPER_BASE_SERVO_OPEN_POS);
            this.middleServo.setPosition(Constants.SWEEPER_MIDDLE_SERVO_OPEN_POS);
            this.endServo.setPosition(Constants.SWEEPER_END_SERVO_OPEN_POS);
        });
    }

    /**
     *
     * @return
     */
    public Action closeSweeperAction() {
        return new InstantActionImpl(() ->  {
            this.baseServo.setPosition(Constants.SWEEPER_BASE_SERVO_CLOSED_POS);
            this.middleServo.setPosition(Constants.SWEEPER_MIDDLE_SERVO_CLOSED_POS);
            this.endServo.setPosition(Constants.SWEEPER_END_SERVO_CLOSED_POS);
        });
    }
}
