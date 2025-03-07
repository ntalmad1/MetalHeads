package org.firstinspires.ftc.teamcode.library.event;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.event.gp1_a_press.Gp1_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_b_press.Gp1_B_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_back_press.Gp1_Back_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_down_down.Gp1_Dpad_Down_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_left_down.Gp1_Dpad_Left_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_right_down.Gp1_Dpad_Right_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_up_down.Gp1_Dpad_Up_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_left_press.Gp1_Dpad_Left_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_right_press.Gp1_Dpad_Right_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_bumper_down.Gp1_Left_Bumper_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_bumper_up.Gp1_Left_Bumper_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_x.Gp1_LeftStick_X_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_y.Gp1_LeftStick_Y_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger_down.Gp1_Left_Trigger_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger_up.Gp1_Left_Trigger_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger.Gp1_Right_Trigger_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger.Gp1_Left_Trigger_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_down.Gp1_Right_Bumper_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_up.Gp1_Right_Bumper_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_x.Gp1_RightStick_X_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_y.Gp1_RightStick_Y_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_down.Gp1_Right_Trigger_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_up.Gp1_Right_Trigger_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_start_press.Gp1_Start_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_x_press.Gp1_X_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_y_press.Gp1_Y_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_a_press.Gp2_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_b_press.Gp2_B_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_back_press.Gp2_Back_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_down_down.Gp2_Dpad_Down_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_left_down.Gp2_Dpad_Left_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_right_down.Gp2_Dpad_Right_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_up_down.Gp2_Dpad_Up_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_down_press.Gp2_Dpad_Down_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_left_press.Gp2_Dpad_Left_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_right_press.Gp2_Dpad_Right_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_up_press.Gp2_Dpad_Up_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_down.Gp2_Left_Bumper_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_press.Gp2_Left_Bumper_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_up.Gp2_Left_Bumper_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_x.Gp2_LeftStick_X_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_y.Gp2_LeftStick_Y_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_trigger.Gp2_Left_Trigger_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_trigger_down.Gp2_Left_Trigger_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_trigger_up.Gp2_Left_Trigger_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_down.Gp2_Right_Bumper_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_press.Gp2_Right_Bumper_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_up.Gp2_Right_Bumper_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_x.Gp2_RightStick_X_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_y.Gp2_RightStick_Y_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_trigger.Gp2_Right_Trigger_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_trigger_down.Gp2_Right_Trigger_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_trigger_up.Gp2_Right_Trigger_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_start_press.Gp2_Start_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_x_press.Gp2_X_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_y_press.Gp2_Y_PressEvent;

/**
 *
 */
public class EventBus extends HandlerManager {

    /**
     *
     */
    public static EventBus instance;

    /**
     *
     * @return
     */
    public static EventBus getInstance() {
        return instance;
    }

    /**
     *
     * @param robot
     */
    public static void init (IsaacBot robot) {
        instance = new EventBus();
    }

    /**
     *
     */
    private boolean gp1_left_bumper_down;
    private boolean gp1_right_bumper_down;

    /**
     *
     */
    private boolean gp1_left_trigger_down;
    private boolean gp1_right_trigger_down;

    private float gp1_left_trigger;
    private float gp1_right_trigger;

    private float gp2_left_trigger;
    private float gp2_right_trigger;

    private boolean gp2_left_trigger_down;
    private boolean gp2_right_trigger_down;

    /**
     *
     */
    private double gp1_leftStickX;
    private double gp1_leftStickY;
    private double gp1_rightStickX;
    private double gp1_rightStickY;

    /**
     *
     */
    private double gp2_leftStickX;
    private double gp2_leftStickY;
    private double gp2_rightStickX;
    private double gp2_rightStickY;

    /**
     *
     */
    private boolean gp1_a_down;
    private boolean gp1_b_down;
    private boolean gp1_x_down;
    private boolean gp1_y_down;

    private boolean gp1_back_down;
    private boolean gp1_start_down;

    /**
     *
     */
    private boolean gp2_a_down;
    private boolean gp2_b_down;
    private boolean gp2_x_down;
    private boolean gp2_y_down;

    private boolean gp2_back_down;
    private boolean gp2_start_down;

    private boolean gp2_left_bumper_down;
    private boolean gp2_right_bumper_down;

    /**
     *
     */
    private boolean g1_dpad_down_down;
    private boolean g1_dpad_up_down;
    private boolean g1_dpad_left_down;
    private boolean g1_dpad_right_down;

    /**
     *
     */
    private boolean g2_dpad_down_down;
    private boolean g2_dpad_up_down;
    private boolean g2_dpad_left_down;
    private boolean g2_dpad_right_down;

    /**
     * Hidden Constructor
     *
     */
    protected EventBus () {
        super();
    }

    /**
     *
     */
    public void run ()
    {
        //this.robot.telemetry.log().add("EventBus.run()");

        boolean current_gp1_right_bumper_down = this.robot.gamepad1.right_bumper;
        if (this.gp1_right_bumper_down && !current_gp1_right_bumper_down) {
            this.gp1_right_bumper_down = false;
            this.fireEvent(new Gp1_Right_Bumper_UpEvent());
        }
        else if (current_gp1_right_bumper_down) { // && !this.gp1_right_bumper_down) {
            this.gp1_right_bumper_down = true;
            this.fireEvent(new Gp1_Right_Bumper_DownEvent());
        }

        boolean current_gp1_left_bumper_down = this.robot.gamepad1.left_bumper;
        if (this.gp1_left_bumper_down && !current_gp1_left_bumper_down) {
            this.gp1_left_bumper_down = false;
            this.fireEvent(new Gp1_Left_Bumper_UpEvent());
        }
        else if (current_gp1_left_bumper_down) { // && !this.gp1_left_bumper_down) {
            this.gp1_left_bumper_down = true;
            this.fireEvent(new Gp1_Left_Bumper_DownEvent());
        }

        boolean current_gp2_right_bumper_down = this.robot.gamepad2.right_bumper;
        if (this.gp2_right_bumper_down && !current_gp2_right_bumper_down) {
            this.gp2_right_bumper_down = false;
            this.fireEvent(new Gp2_Right_Bumper_UpEvent());
            this.fireEvent(new Gp2_Right_Bumper_PressEvent());
        }
        else if (current_gp2_right_bumper_down && !this.gp2_right_bumper_down) {
            this.gp2_right_bumper_down = true;
            this.fireEvent(new Gp2_Right_Bumper_DownEvent());
        }

        boolean current_gp2_left_bumper_down = this.robot.gamepad2.left_bumper;
        if (this.gp2_left_bumper_down && !current_gp2_left_bumper_down) {
            this.gp2_left_bumper_down = false;
            this.fireEvent(new Gp2_Left_Bumper_UpEvent());
            this.fireEvent(new Gp2_Left_Bumper_PressEvent());
        }
        else if (current_gp2_left_bumper_down && !this.gp2_left_bumper_down) {
            this.gp2_left_bumper_down = true;
            this.fireEvent(new Gp2_Left_Bumper_DownEvent());
        }

        //------------------------------------------------------------------------------------------


        boolean current_gp1_left_trigger_down = this.robot.gamepad1.left_trigger >= 0.5 ;
        if (this.gp1_left_trigger_down && !current_gp1_left_trigger_down) {
            this.gp1_left_trigger_down = false;
            this.fireEvent(new Gp1_Left_Trigger_UpEvent());
        }
        else if (current_gp1_left_trigger_down && !this.gp1_left_trigger_down) {
            this.gp1_left_trigger_down = true;
            this.fireEvent(new Gp1_Left_Trigger_DownEvent());
        }

        boolean current_gp1_right_trigger_down = this.robot.gamepad1.right_trigger >= 0.5 ;
        if (this.gp1_right_trigger_down && !current_gp1_right_trigger_down) {
            this.gp1_right_trigger_down = false;
            this.fireEvent(new Gp1_Right_Trigger_UpEvent());
        }
        else if (current_gp1_right_trigger_down && !this.gp1_right_trigger_down) {
            this.gp1_right_trigger_down = true;
            this.fireEvent(new Gp1_Right_Trigger_DownEvent());
        }

//        float current_gp2_left_trigger = this.robot.gamepad2.left_trigger;
//        if (current_gp2_left_trigger >= 0.5 && !this.gp2_left_trigger_down) {
//            this.fireEvent(new Gp2_Left_Trigger_DownEvent());
//            this.gp2_left_trigger_down = true;
//        }
//        else if (current_gp2_left_trigger < 0.5) {
//            this.gp2_left_trigger_down = false;
//        }

        boolean current_gp2_left_trigger_down = this.robot.gamepad2.left_trigger >= 0.5 ;
        if (this.gp2_left_trigger_down && !current_gp2_left_trigger_down) {
            this.gp2_left_trigger_down = false;
            this.fireEvent(new Gp2_Left_Trigger_UpEvent());
        }
        else if (current_gp2_left_trigger_down && !this.gp2_left_trigger_down) {
            this.gp2_left_trigger_down = true;
            this.fireEvent(new Gp2_Left_Trigger_DownEvent());
        }

//        float current_gp2_right_trigger = this.robot.gamepad2.right_trigger;
//        if (current_gp2_right_trigger >= 0.5 && !this.gp2_right_trigger_down) {
//            this.fireEvent(new Gp2_Right_Trigger_DownEvent());
//            this.gp2_right_trigger_down = true;
//        }
//        else if (current_gp2_right_trigger < 0.5) {
//            this.gp2_right_trigger_down = false;
//        }

        boolean current_gp2_right_trigger_down = this.robot.gamepad2.right_trigger >= 0.5 ;
        if (this.gp2_right_trigger_down && !current_gp2_right_trigger_down) {
            this.gp2_right_trigger_down = false;
            this.fireEvent(new Gp2_Right_Trigger_UpEvent());
        }
        else if (current_gp2_right_trigger_down && !this.gp2_right_trigger_down) {
            this.gp2_right_trigger_down = true;
            this.fireEvent(new Gp2_Right_Trigger_DownEvent());
        }

        float current_gp2_right_trigger = this.robot.gamepad2.right_trigger;
        float current_gp2_left_trigger  = this.robot.gamepad2.left_trigger;
        if ((current_gp2_left_trigger == 0 && this.gp2_left_trigger > 0) || (current_gp2_left_trigger > 0)) {
            this.fireEvent(new Gp2_Left_Trigger_Event(current_gp2_left_trigger));
        }

        if ((current_gp2_right_trigger == 0 && this.gp2_right_trigger > 0) || (current_gp2_right_trigger > 0)) {
            this.fireEvent(new Gp2_Right_Trigger_Event(current_gp2_right_trigger));
        }

        this.gp2_left_trigger = current_gp2_left_trigger;
        this.gp2_right_trigger = current_gp2_right_trigger;

        //--------------------------------------------------------

        float current_gp1_rightTrigger = this.robot.gamepad1.right_trigger;
        if (current_gp1_rightTrigger > 0
                || current_gp1_rightTrigger < 0
                || (current_gp1_rightTrigger == 0 && gp1_right_trigger != 0)) {

            Gp1_Right_Trigger_Event event = new Gp1_Right_Trigger_Event(current_gp1_rightTrigger);
            this.fireEvent(event);
            gp1_right_trigger = current_gp1_rightTrigger;
        }

        float current_gp1_leftTrigger = this.robot.gamepad1.left_trigger;
        if (current_gp1_leftTrigger > 0
        || current_gp1_leftTrigger < 0
        || (current_gp1_leftTrigger == 0 && gp1_left_trigger != 0)) {

            Gp1_Left_Trigger_Event event = new Gp1_Left_Trigger_Event(current_gp1_leftTrigger);
            this.fireEvent(event);
            gp1_left_trigger = current_gp1_leftTrigger;
        }

        double current_gp1_leftStickX = this.robot.gamepad1.left_stick_x;
        if (current_gp1_leftStickX > 0
         || current_gp1_leftStickX < 0
         || (current_gp1_leftStickX == 0 && gp1_leftStickX != 0)) {

            Gp1_LeftStick_X_Event event = new Gp1_LeftStick_X_Event(current_gp1_leftStickX);
            this.fireEvent(event);
            gp1_leftStickX = current_gp1_leftStickX;
        }

        double current_gp1_leftStickY = this.robot.gamepad1.left_stick_y;
        if (current_gp1_leftStickY > 0
                || current_gp1_leftStickY < 0
                || (current_gp1_leftStickY == 0 && gp1_leftStickY != 0)) {

            Gp1_LeftStick_Y_Event event = new Gp1_LeftStick_Y_Event(current_gp1_leftStickY);
            this.fireEvent(event);
            gp1_leftStickY = current_gp1_leftStickY;
        }

        double current_gp1_rightStickX = this.robot.gamepad1.right_stick_x;
        if (current_gp1_rightStickX > 0
                || current_gp1_rightStickX < 0
                || (current_gp1_rightStickX == 0 && gp1_rightStickX != 0)) {

            Gp1_RightStick_X_Event event = new Gp1_RightStick_X_Event(current_gp1_rightStickX);
            this.fireEvent(event);
            gp1_rightStickX = current_gp1_rightStickX;
        }

        double current_gp1_rightStickY = this.robot.gamepad1.right_stick_y;
        if (current_gp1_rightStickY > 0
                || current_gp1_rightStickY < 0
                || (current_gp1_rightStickY == 0 && gp1_rightStickY != 0)) {

            Gp1_RightStick_Y_Event event = new Gp1_RightStick_Y_Event(current_gp1_rightStickY);
            this.fireEvent(event);
            gp1_rightStickY = current_gp1_rightStickY;
        }

        //------------------------------------------------------------------------------------------

        double current_gp2_leftStickX = this.robot.gamepad2.left_stick_x;
        if (current_gp2_leftStickX > 0
                || current_gp2_leftStickX < 0
                || (current_gp2_leftStickX == 0 && gp2_leftStickX != 0)) {

            Gp2_LeftStick_X_Event event = new Gp2_LeftStick_X_Event(current_gp2_leftStickX);
            this.fireEvent(event);
            gp2_leftStickX = current_gp2_leftStickX;
        }

        double current_gp2_leftStickY = this.robot.gamepad2.left_stick_y;
        if (current_gp2_leftStickY > 0
                || current_gp2_leftStickY < 0
                || (current_gp2_leftStickY == 0 && gp2_leftStickY != 0)) {

            Gp2_LeftStick_Y_Event event = new Gp2_LeftStick_Y_Event(current_gp2_leftStickY);
            this.fireEvent(event);
            gp2_leftStickY = current_gp2_leftStickY;
        }

        double current_gp2_rightStickX = this.robot.gamepad2.right_stick_x;
        if (current_gp2_rightStickX > 0
                || current_gp2_rightStickX < 0
                || (current_gp2_rightStickX == 0 && gp2_rightStickX != 0)) {

            Gp2_RightStick_X_Event event = new Gp2_RightStick_X_Event(current_gp2_rightStickX);
            this.fireEvent(event);
            gp2_rightStickX = current_gp2_rightStickX;
        }

        double current_gp2_rightStickY = this.robot.gamepad2.right_stick_y;
        if (current_gp2_rightStickY > 0
                || current_gp2_rightStickY < 0
                || (current_gp2_rightStickY == 0 && gp2_rightStickY != 0)) {

            Gp2_RightStick_Y_Event event = new Gp2_RightStick_Y_Event(current_gp2_rightStickY);
            this.fireEvent(event);
            gp2_rightStickY = current_gp2_rightStickY;
        }

        // Gamepad 1 Dpad Events
        //------------------------------------------------------------------------------------
        boolean current_gp1_dpad_down = this.robot.gamepad1.dpad_down;
        if (this.g1_dpad_down_down && !current_gp1_dpad_down) {
            this.g1_dpad_down_down = false;
            this.fireEvent(new Gp1_Dpad_Down_PressEvent());
        }
        else if (current_gp1_dpad_down) {
            this.g1_dpad_down_down = true;
            this.fireEvent(new Gp1_Dpad_Down_DownEvent());
        }

        boolean current_gp1_dpad_up = this.robot.gamepad1.dpad_up;
        if (this.g1_dpad_up_down && !current_gp1_dpad_up) {
            this.g1_dpad_up_down = false;
            this.fireEvent(new Gp1_Dpad_Up_PressEvent());
        }
        else if (current_gp1_dpad_up) {
            this.g1_dpad_up_down = true;
            this.fireEvent(new Gp1_Dpad_Up_DownEvent());
        }

        boolean current_gp1_dpad_left = this.robot.gamepad1.dpad_left;
        if (this.g1_dpad_left_down && !current_gp1_dpad_left) {
            this.g1_dpad_left_down = false;
            this.fireEvent(new Gp1_Dpad_Left_PressEvent());
        }
        else if (current_gp1_dpad_left) {
            this.g1_dpad_left_down = true;
            this.fireEvent(new Gp1_Dpad_Left_DownEvent());
        }

        boolean current_gp1_dpad_right = this.robot.gamepad1.dpad_right;
        if (this.g1_dpad_right_down && !current_gp1_dpad_right) {
            this.g1_dpad_right_down = false;
            this.fireEvent(new Gp1_Dpad_Right_PressEvent());
        }
        else if (current_gp1_dpad_right) {
            this.g1_dpad_right_down = true;
            this.fireEvent(new Gp1_Dpad_Right_DownEvent());
        }

        // Gamepad 2 Dpad Down Events
        boolean current_gp2_dpad_down = this.robot.gamepad2.dpad_down;
        if (this.g2_dpad_down_down && !current_gp2_dpad_down) {
            this.g2_dpad_down_down = false;
            this.fireEvent(new Gp2_Dpad_Down_PressEvent());
        }
        else if (current_gp2_dpad_down) {
            this.g2_dpad_down_down = true;
            this.fireEvent(new Gp2_Dpad_Down_DownEvent());
        }

        boolean current_gp2_dpad_up = this.robot.gamepad2.dpad_up;
        if (this.g2_dpad_up_down && !current_gp2_dpad_up) {
            this.g2_dpad_up_down = false;
            this.fireEvent(new Gp2_Dpad_Up_PressEvent());
        }
        else if (current_gp2_dpad_up) {
            this.g2_dpad_up_down = true;
            this.fireEvent(new Gp2_Dpad_Up_DownEvent());
        }

        boolean current_gp2_dpad_left = this.robot.gamepad2.dpad_left;
        if (this.g2_dpad_left_down && !current_gp2_dpad_left) {
            this.g2_dpad_left_down = false;
            this.fireEvent(new Gp2_Dpad_Left_PressEvent());
        }
        else if (current_gp2_dpad_left) {
            this.g2_dpad_left_down = true;
            this.fireEvent(new Gp2_Dpad_Left_DownEvent());
        }

        boolean current_gp2_dpad_right = this.robot.gamepad2.dpad_right;
        if (this.g2_dpad_right_down && !current_gp2_dpad_right) {
            this.g2_dpad_right_down = false;
            this.fireEvent(new Gp2_Dpad_Right_PressEvent());
        }
        else if (current_gp2_dpad_right) {
            this.g2_dpad_right_down = true;
            this.fireEvent(new Gp2_Dpad_Right_DownEvent());
        }

        //------------------------------------------------------------------------------------

        boolean current_gp2_a = this.robot.gamepad2.a;
        if (this.gp2_a_down && !current_gp2_a) {
            this.fireEvent(new Gp2_A_PressEvent());
        }
        this.gp2_a_down = current_gp2_a;

        boolean current_gp2_b = this.robot.gamepad2.b;
        if (this.gp2_b_down && !current_gp2_b) {
            this.fireEvent(new Gp2_B_PressEvent());
        }
        this.gp2_b_down = current_gp2_b;

        boolean current_gp2_x = this.robot.gamepad2.x;
        if (this.gp2_x_down && !current_gp2_x) {
            this.fireEvent(new Gp2_X_PressEvent());
        }
        this.gp2_x_down = current_gp2_x;

        boolean current_gp2_y = this.robot.gamepad2.y;
        if (this.gp2_y_down && !current_gp2_y) {
            this.fireEvent(new Gp2_Y_PressEvent());
        }
        this.gp2_y_down = current_gp2_y;

        boolean current_gp2_back = this.robot.gamepad2.back;
        if (this.gp2_back_down && !current_gp2_back) {
            this.fireEvent(new Gp2_Back_PressEvent());
        }
        this.gp2_back_down = current_gp2_back;

        boolean current_gp2_start = this.robot.gamepad2.start;
        if (this.gp2_start_down && !current_gp2_start) {
            this.fireEvent(new Gp2_Start_PressEvent());
        }
        this.gp2_start_down = current_gp2_start;

        //------------------------------------------------------------------------------------

        boolean current_gp1_a = this.robot.gamepad1.a;
        if (this.gp1_a_down && !current_gp1_a) {
            this.fireEvent(new Gp1_A_PressEvent());
        }
        this.gp1_a_down = current_gp1_a;

        boolean current_gp1_b = this.robot.gamepad1.b;
        if (this.gp1_b_down && !current_gp1_b) {
            this.fireEvent(new Gp1_B_PressEvent());
        }
        this.gp1_b_down = current_gp1_b;

        boolean current_gp1_x = this.robot.gamepad1.x;
        if (this.gp1_x_down && !current_gp1_x) {
            this.fireEvent(new Gp1_X_PressEvent());
        }
        this.gp1_x_down = current_gp1_x;

        boolean current_gp1_y = this.robot.gamepad1.y;
        if (this.gp1_y_down && !current_gp1_y) {
            this.fireEvent(new Gp1_Y_PressEvent());
        }
        this.gp1_y_down = current_gp1_y;

        boolean current_gp1_back = this.robot.gamepad1.back;
        if (this.gp1_back_down && !current_gp1_back) {
            this.fireEvent(new Gp1_Back_PressEvent());
        }
        this.gp1_back_down = current_gp1_back;

        boolean current_gp1_start = this.robot.gamepad1.start;
        if (this.gp1_start_down && !current_gp1_start) {
            this.fireEvent(new Gp1_Start_PressEvent());
        }
        this.gp1_start_down = current_gp1_start;

        //--------------------------------------------------------------------------


    }
}
