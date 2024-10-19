package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.library.servo.ServoPos;

/**
 *
 */
public class Constants {

    /**
     */
    public static final double INTAKE_PINCHER_OPEN_POS = 0.373;
    public static final double INTAKE_PINCHER_CLOSE_POS = 0.544;

    /**
     */
    public static final double CLAW_PINCHER_OPEN_POS = 0.65;
    public static final double CLAW_PINCHER_CLOSE_POS = 0.484;

    /**
     */
    public static final double INTAKE_V_SERVO_INIT_POS = 0.364;

    /**
     */
    public static final double VIPER_SLIDE_VOLTS_MAX = 1.20;

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_PICK_READY_MIN = new CompBot.PositionConstants(){
        @Override
        public void setValues() {
            hServoPos = new ServoPos(0.5033);
            vServoPos = new ServoPos(0.5044);
            vSlideVolts = 0.7;
            mainBoomPos = new MotorPos(-518);
            clawRotatorPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_PICK_LEFT_READY_MIN = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-736, 0.5);
            vSlideVolts = 0.694;
            hServoPos = new ServoPos(0.8633);
            vServoPos = new ServoPos(0.4606);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_OPEN_POS, 1);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_PICK_RIGHT_READY_MIN = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-697, 0.5);
            vSlideVolts = 0.698;
            hServoPos = new ServoPos(0.1917);
            vServoPos = new ServoPos(0.47);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_OPEN_POS, 1);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_PICK_READY_MAX = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-288, 0.5);
            vSlideVolts = 0.9;
            hServoPos = new ServoPos(0.4994);
            vServoPos = new ServoPos(0.4944);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_OPEN_POS, 1);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_PICK_LEFT_READY_MAX = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-450);
            //vSlideVolts = 0.959;
            hServoPos = new ServoPos(0.8422);
            vServoPos = new ServoPos(0.453);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_OPEN_POS, 1);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_PICK_RIGHT_READY_MAX = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-416, 0.5);
            //vSlideVolts = 0.959;
            hServoPos = new ServoPos(0.1717);
            vServoPos = new ServoPos(0.4578);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_OPEN_POS, 1);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_CARRY = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(0, 0.75);
            vSlideVolts = 0.586;
            hServoPos = new ServoPos(0.4856);
            vServoPos = new ServoPos(0.5367);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_CLOSE_POS, 1);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_EXTEND_READY = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(2178, 1);
            vSlideVolts = 0.587;
            hServoPos = new ServoPos(0.4856);
            vServoPos = new ServoPos(0.5367);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_CLOSE_POS, 1);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_PLACE_LOW_READY = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(2223, 0.5);
            vSlideVolts = 0.813;
            hServoPos = new ServoPos(0.4922);
            vServoPos = new ServoPos(0.4239);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_CLOSE_POS, 1);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_PLACE_HIGH_READY = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(2783, 0.5);
            vSlideVolts = VIPER_SLIDE_VOLTS_MAX;
            hServoPos = new ServoPos(0.5039);
            vServoPos = new ServoPos(0.51);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_CLOSE_POS, 1);
        }
    };

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     */
    public static final CompBot.PositionConstants SPECIMEN_PICK_READY = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-355, 0.5);
            vSlideVolts = 0.8;
            hServoPos = new ServoPos(0.375);
            vServoPos = new ServoPos(0.1528);
            clawRotatorPos = new ServoPos(0.3117);
            clawPincherPos = new ServoPos(CLAW_PINCHER_OPEN_POS, 1);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_CLOSE_POS, 1);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SPECIMEN_PICK = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-6, 0.75);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SPECIMEN_PLACE_HIGH_READY = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(1724, 0.75);
            vSlideVolts = 0.76;
            hServoPos = new ServoPos(0.375);
            vServoPos = new ServoPos(0.0967);
            clawRotatorPos = new ServoPos(0.465);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SPECIMEN_PLACE_HIGH = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(985, 1);
            vSlideVolts = 0.747;
            hServoPos = new ServoPos(0.425);
            vServoPos = new ServoPos(0.0967);
            clawRotatorPos = new ServoPos(0.445);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SPECIMEN_PLACE_LOW_READY = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(157, 0.75);
            vSlideVolts = 0.709;
            hServoPos = new ServoPos(0.375);
            vServoPos = new ServoPos(0.1);
            clawRotatorPos = new ServoPos(0.3733);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SPECIMEN_PLACE_LOW = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-616, 0.75);
        }
    };

    /**
     * Hidden constructor - make class essentially static
     */
    private Constants() {
    }
}
