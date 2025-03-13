package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.library.servo.ServoPos;

/**
 *
 */
public class Constants {

    /**
     */
    public static final int MAIN_BOOM_MIN_TICS = 0;
    public static final int MAIN_BOOM_MAX_TICS = 1148;
    public static final int MAIN_BOOM_INIT_TICS = 525;
    public static final int MAIN_BOOM_SCALE = 200;
    public static final int MAIN_BOOM_TIMEOUT_DEFAULT = 180;

    /**
     */
    public static final double VIPER_SLIDES_VOLTS_INIT = 0.30745;
    public static final int VIPER_SLIDES_MIN_TICS = 14;
    public static final int VIPER_SLIDES_MAX_TICS = 2150;
    public static final int VIPER_SLIDES_SPECIMEN_TEST = 486;
    public static final int VIPER_SLIDES_SCALE = 143;
    public static final int VIPER_SLIDES_TIMEOUT_DEFAULT = 15;
    /**
     */
    public static final double DOUBLE_SERVOS_MIN_POS = 0;
    public static final double DOUBLE_SERVOS_MAX_POS = 1;
    public static final double DOUBLE_SERVOS_INIT_POS = 0.98;
    public static final double DOUBLE_SERVOS_INCREMENT = 0.006;

    /**
     */
    public static final double MIDDLE_SERVO_MIN_POS = 0;
    public static final double MIDDLE_SERVO_MAX_POS = 0.783;
    public static final double MIDDLE_SERVO_INIT_POS = 0.783;
    public static final double MIDDLE_SERVO_INCREMENT = 0.006;
    public static final double MIDDLE_SERVO_SPECIMEN_PLACED = 0.173;

    /**
     */
    public static final double CLAW_ROTATOR_MIN_POS = 0;
    public static final double CLAW_ROTATOR_0_DEG = 0;
    public static final double CLAW_ROTATOR_90_DEG = 0.3;
    public static final double CLAW_ROTATOR_180_DEG = 0.64;
    public static final double CLAW_ROTATOR_270_DEG = 1;
    public static final double CLAW_ROTATOR_MAX_POS = 1;
    public static final double CLAW_ROTATOR_INIT_POS = 0.3;
    public static final double CLAW_ROTATOR_INCREMENT = 0.015;

    /**
     */
    public static final double CLAW_PINCHER_OPEN_POS = 0.0;
    public static final double CLAW_PINCHER_CLOSE_POS = 0.23;
    public static final double CLAW_PINCHER_INIT_POS = 0.23;

    /**
     */
    public static final double SWEEPER_BASE_SERVO_CLOSED_POS = 0.486;
    public static final double SWEEPER_MIDDLE_SERVO_CLOSED_POS = 0.155;
    public static final double SWEEPER_END_SERVO_CLOSED_POS = 0;


    public static final double SWEEPER_BASE_SERVO_OPEN_POS = 0.750;
    public static final double SWEEPER_MIDDLE_SERVO_OPEN_POS = 0.683;
    public static final double SWEEPER_END_SERVO_OPEN_POS = 0.582;

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_READY = new PositionsStruct(){
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MIN_TICS);
            vSlidePos = new MotorPos(165);
            doubleServosPos = new ServoPos(0.568);
            middleServoPos = new ServoPos(0.724);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_90_DEG);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_OPEN_POS);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_DOWN = new PositionsStruct(){
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MIN_TICS);
            doubleServosPos = new ServoPos(0.489);
            middleServoPos = new ServoPos(0.671);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_UP = new PositionsStruct(){
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MIN_TICS);
            doubleServosPos = new ServoPos(0.728);
            middleServoPos = new ServoPos(0.783);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PLACE_HIGH_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MAX_TICS);
            vSlidePos = new MotorPos(Constants.VIPER_SLIDES_MAX_TICS);
            doubleServosPos = new ServoPos(0.44);
            middleServoPos = new ServoPos(0.023);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_90_DEG);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_CLOSE_POS);
        }
    };

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     */
    public static final PositionsStruct SPECIMEN_PICK_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MAX_TICS);
            vSlidePos = new MotorPos(Constants.VIPER_SLIDES_MIN_TICS);
            doubleServosPos = new ServoPos(0.963);
            middleServoPos = new ServoPos(0.408);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_90_DEG);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_OPEN_POS);
        }
    };

    /**
     */
    public static final PositionsStruct SPECIMEN_PLACE_HIGH_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            // was 526
            mainBoomPos = new MotorPos(536);
            vSlidePos = new MotorPos(1080);
            doubleServosPos = new ServoPos(0.260);
            middleServoPos = new ServoPos(0.0);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_MAX_POS);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_CLOSE_POS);
        }
    };


    /**
     */
    public static final PositionsStruct SPECIMEN_PLACE_HIGH_READY_TELEOP = new PositionsStruct() {
        @Override
        public void setValues() {
            // was 526
            mainBoomPos = new MotorPos(536);
            vSlidePos = new MotorPos(1081);
            doubleServosPos = new ServoPos(0.260);
            middleServoPos = new ServoPos(0.0);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_0_DEG);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_CLOSE_POS);
        }
    };


    /**
     */
    public static final PositionsStruct HANG_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(669);
            vSlidePos = new MotorPos(1373);
            doubleServosPos = new ServoPos(0.9);
            middleServoPos = new ServoPos(0.783);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_0_DEG);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_OPEN_POS);
        }
    };

    /**
     */
    public static final PositionsStruct HANG = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(863);
            vSlidePos = new MotorPos(40);
        }
    };



    /**
     * Hidden constructor - make class essentially static
     */
    private Constants() {
    }
}
