package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.dcmotor.DcMotorComponent;
import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;

/**
 *
 */
public class EncodedMotor extends DcMotorComponent {

    /**
     */
    protected TouchSensor touchSensor;

    /**
     * Constructor
     *
     * @param config
     */
    public EncodedMotor(EncodedMotorConfig config) {
        super(config);
    }

    /**
     *
     * @return
     */
    public EncodedMotorConfig getConfig() {
        return (EncodedMotorConfig)super.getConfig();
    }

    /**
     *
     * @return
     */
    public TouchSensor getTouchSensor() {
        return this.touchSensor;
    }

    /**
     *
     * @param position
     */
    public AbstractAction gotoPositionAction (int position) {
        return this.gotoPositionAction(position, 1);
    }



    /**
     *
     * @param position
     * @param power
     * @return
     */
    public AbstractAction gotoPositionAction (int position, double power) {
        return new EncodedMotorGoToPositionAction(this, position, power, null);
    }

    /**
     *
     */
    public void init () {
        super.init();

        this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    /**
     * Called from event handler e.g. "left stick y"
     * @param power
     */
//    public void move (double power) {
//        if (this.getDirection().equals(DcMotorSimple.Direction.FORWARD)) {
//            if (power > 0) {
//                int newPosition = (int) (this.getCurrentPosition() + this.getConfig().scale * power);
//
//                if (newPosition >= this.getConfig().maxTics) {
//                    newPosition = this.getConfig().maxTics;
//                }
//
//                power = 1;
//
//                this.setTargetPosition(newPosition);
//                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                this.setPower(power);
//
//            } else if (power < 0) {
//                int newPosition = (int) (this.getCurrentPosition() - Math.abs(this.getConfig().scale * power));
//
//                if (newPosition <= this.getConfig().minTics) {
//                    newPosition = this.getConfig().minTics;
//                }
//
//                power = -1;
//
//                this.setTargetPosition(newPosition);
//                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                this.setPower(power);
//            } else {
//                if (this.isBrakeOn()) {
//                    //                this.setTargetPosition(this.getCurrentPosition());
//                    //                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    //                this.setPower(1);
//                } else {
//                    this.setPower(0);
//                    this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                }
//            }
//        }
//        else {
//            // viper slides
//            if (power < 0) {
//                int newPosition = (int) (this.getCurrentPosition() + Math.abs(this.getConfig().scale * power));
//
//
//                if (newPosition >= this.getConfig().maxTics) {
//                    newPosition = this.getConfig().maxTics;
//                }
//
//                power = 1;
//
//                this.setTargetPosition(newPosition);
//                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                this.setPower(power);
//
//            } else if (power > 0) {
//                int newPosition = (int) (this.getCurrentPosition() - this.getConfig().scale * power);
//
//                if (newPosition <= this.getConfig().minTics) {
//                    newPosition = this.getConfig().minTics;
//                }
//
//                power = -1;
//
//                this.setTargetPosition(newPosition);
//                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                this.setPower(power);
//
//            } else {
//                if (this.isBrakeOn()) {
//                    //                this.setTargetPosition(this.getCurrentPosition());
//                    //                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    //                this.setPower(1);
//                } else {
//                    this.setPower(0);
//                    this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                }
//            }
//        }
//    }

    public void move (double power) {
        if (power > 0) {

            this.setTargetPosition(this.getConfig().maxTics);
            this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.setPower(power);

        } else if (power < 0) {
            this.setTargetPosition(this.getConfig().minTics);
            this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.setPower(-power);
        } else {
//            if (this.isBrakeOn()) {
//                //                this.setTargetPosition(this.getCurrentPosition());
//                //                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                //                this.setPower(1);
//            } else {
//                this.setTargetPosition(this.getCurrentPosition());
//                this.setPower(0);
//                this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            }

            this.setPower(1);
            this.setTargetPosition(this.getCurrentPosition());
            this.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }
    }

    /**
     *
     */
    public void resetEncoder () {
        this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    boolean toggle;

    /**
     */
    public void run () {
        super.run();

//        if (this.touchSensor != null && this.touchSensor.isPressed() && this.toggle) {
//            this.toggle = false;
//            this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            this.setTargetPosition(10);
//            this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            this.setPower(1);
//        }
//
//        if (this.touchSensor != null && !this.touchSensor.isPressed()) {
//            toggle = true;
//        }
    }

    /**
     *
     * @parm sensor
     */
    public void setTouchSensor(TouchSensor sensor) {
        this.touchSensor = sensor;
    }
}
