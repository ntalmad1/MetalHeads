package org.firstinspires.ftc.teamcode.library.component;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventBus;
import org.firstinspires.ftc.teamcode.library.event.EventHandler;
import org.firstinspires.ftc.teamcode.library.event.EventType;
import org.firstinspires.ftc.teamcode.library.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.event.gp1_a_press.Gp1_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_a_press.Gp1_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_b_press.Gp1_B_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_b_press.Gp1_B_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_back_press.Gp1_Back_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_back_press.Gp1_Back_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_down_down.Gp1_Dpad_Down_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_down_down.Gp1_Dpad_Down_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_left_down.Gp1_Dpad_Left_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_left_down.Gp1_Dpad_Left_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_right_down.Gp1_Dpad_Right_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_right_down.Gp1_Dpad_Right_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_up_down.Gp1_Dpad_Up_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_up_down.Gp1_Dpad_Up_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_left_press.Gp1_Dpad_Left_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_left_press.Gp1_Dpad_Left_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_right_press.Gp1_Dpad_Right_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_right_press.Gp1_Dpad_Right_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_bumper_down.Gp1_Left_Bumper_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_bumper_down.Gp1_Left_Bumper_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_bumper_up.Gp1_Left_Bumper_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_bumper_up.Gp1_Left_Bumper_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_x.Gp1_LeftStick_X_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_x.Gp1_LeftStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_y.Gp1_LeftStick_Y_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_y.Gp1_LeftStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger_down.Gp1_Left_Trigger_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger_down.Gp1_Left_Trigger_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger_up.Gp1_Left_Trigger_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger_up.Gp1_Left_Trigger_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_down.Gp1_Right_Bumper_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_down.Gp1_Right_Bumper_DownHandler;

import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger.Gp1_Right_Trigger_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger.Gp1_Right_Trigger_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger.Gp1_Left_Trigger_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger.Gp1_Left_Trigger_Handler;


import org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_up.Gp1_Right_Bumper_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_up.Gp1_Right_Bumper_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_x.Gp1_RightStick_X_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_x.Gp1_RightStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_y.Gp1_RightStick_Y_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_y.Gp1_RightStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_down.Gp1_Right_Trigger_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_down.Gp1_Right_Trigger_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_up.Gp1_Right_Trigger_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_up.Gp1_Right_Trigger_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_start_press.Gp1_Start_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_start_press.Gp1_Start_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_x_press.Gp1_X_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_x_press.Gp1_X_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_y_press.Gp1_Y_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_y_press.Gp1_Y_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_a_press.Gp2_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_a_press.Gp2_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_b_press.Gp2_B_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_b_press.Gp2_B_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_back_press.Gp2_Back_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_back_press.Gp2_Back_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_down_down.Gp2_Dpad_Down_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_down_down.Gp2_Dpad_Down_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_left_down.Gp2_Dpad_Left_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_left_down.Gp2_Dpad_Left_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_right_down.Gp2_Dpad_Right_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_right_down.Gp2_Dpad_Right_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_up_down.Gp2_Dpad_Up_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_up_down.Gp2_Dpad_Up_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_down_press.Gp2_Dpad_Down_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_down_press.Gp2_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_left_press.Gp2_Dpad_Left_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_left_press.Gp2_Dpad_Left_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_right_press.Gp2_Dpad_Right_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_right_press.Gp2_Dpad_Right_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_up_press.Gp2_Dpad_Up_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_up_press.Gp2_Dpad_Up_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_down.Gp2_Left_Bumper_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_down.Gp2_Left_Bumper_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_press.Gp2_Left_Bumper_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_press.Gp2_Left_Bumper_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_up.Gp2_Left_Bumper_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_up.Gp2_Left_Bumper_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_x.Gp2_LeftStick_X_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_x.Gp2_LeftStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_y.Gp2_LeftStick_Y_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_y.Gp2_LeftStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_trigger.Gp2_Left_Trigger_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_trigger.Gp2_Left_Trigger_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_trigger_down.Gp2_Left_Trigger_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_trigger_down.Gp2_Left_Trigger_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_trigger_up.Gp2_Left_Trigger_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_trigger_up.Gp2_Left_Trigger_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_down.Gp2_Right_Bumper_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_down.Gp2_Right_Bumper_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_press.Gp2_Right_Bumper_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_press.Gp2_Right_Bumper_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_up.Gp2_Right_Bumper_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_up.Gp2_Right_Bumper_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_x.Gp2_RightStick_X_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_x.Gp2_RightStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_y.Gp2_RightStick_Y_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_y.Gp2_RightStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_trigger.Gp2_Right_Trigger_Event;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_trigger.Gp2_Right_Trigger_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_trigger_down.Gp2_Right_Trigger_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_trigger_down.Gp2_Right_Trigger_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_trigger_up.Gp2_Right_Trigger_UpEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_trigger_up.Gp2_Right_Trigger_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_start_press.Gp2_Start_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_start_press.Gp2_Start_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_x_press.Gp2_X_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_x_press.Gp2_X_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_y_press.Gp2_Y_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_y_press.Gp2_Y_PressHandler;

/**
 *
 */
public abstract class Component implements IComponent {

    /**
     */
    protected IsaacBot robot;

    /**
     */
    public Telemetry telemetry;

    /**
     */
    private boolean debug;

    /**
     *
     * @param robot
     */
    public Component (IsaacBot robot)
    {
        this.robot = robot;

        this.telemetry = this.robot.telemetry;
    }

    /**
     *
     * @return HandlerRegistration
     */
    public <H extends EventHandler> HandlerRegistration addHandler (EventType<H> type, H handler) {
        return EventBus.getInstance().addHandler(type, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Left_Bumper_DownHandler (Gp1_Left_Bumper_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Left_Bumper_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Left_Bumper_UpHandler (Gp1_Left_Bumper_UpHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Left_Bumper_UpEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Right_Bumper_UpHandler (Gp1_Right_Bumper_UpHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Right_Bumper_UpEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Right_Bumper_DownHandler (Gp1_Right_Bumper_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Right_Bumper_DownEvent.TYPE, handler);
    }

    //-----------------------------------------------------------------------------------------------

    public HandlerRegistration addGp2_Left_Bumper_DownHandler (Gp2_Left_Bumper_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Left_Bumper_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Left_Bumper_UpHandler (Gp2_Left_Bumper_UpHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Left_Bumper_UpEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Right_Bumper_UpHandler (Gp2_Right_Bumper_UpHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Right_Bumper_UpEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Right_Bumper_DownHandler (Gp2_Right_Bumper_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Right_Bumper_DownEvent.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Left_Trigger_DownHandler (Gp1_Left_Trigger_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Left_Trigger_DownEvent.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Left_Trigger_UpHandler (Gp1_Left_Trigger_UpHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Left_Trigger_UpEvent.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Right_Trigger_DownHandler (Gp1_Right_Trigger_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Right_Trigger_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Right_Trigger_UpHandler (Gp1_Right_Trigger_UpHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Right_Trigger_UpEvent.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Right_Trigger_Handler (Gp1_Right_Trigger_Handler handler) {
        return EventBus.getInstance().addHandler(Gp1_Right_Trigger_Event.TYPE, handler);
    }

    public HandlerRegistration addGp1_Left_Trigger_Handler (Gp1_Left_Trigger_Handler handler) {
        return EventBus.getInstance().addHandler(Gp1_Left_Trigger_Event.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Left_Trigger_DownHandler (Gp2_Left_Trigger_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Left_Trigger_DownEvent.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Right_Trigger_DownHandler (Gp2_Right_Trigger_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Right_Trigger_DownEvent.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Left_Trigger_UpHandler (Gp2_Left_Trigger_UpHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Left_Trigger_UpEvent.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Right_Trigger_UpHandler (Gp2_Right_Trigger_UpHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Right_Trigger_UpEvent.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Left_Trigger_Handler (Gp2_Left_Trigger_Handler handler) {
        return EventBus.getInstance().addHandler(Gp2_Left_Trigger_Event.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Right_Trigger_Handler (Gp2_Right_Trigger_Handler handler) {
        return EventBus.getInstance().addHandler(Gp2_Right_Trigger_Event.TYPE, handler);
    }

    //region Gamepad 2 A, B, X, Y Handlers
    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_A_PressHandler (Gp2_A_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_A_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_B_PressHandler (Gp2_B_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_B_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_X_PressHandler (Gp2_X_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_X_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Y_PressHandler (Gp2_Y_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Y_PressEvent.TYPE, handler);
    }
    //endregion

    //region Gamepad 1 A, B, X, Y Handlers
    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_A_PressHandler (Gp1_A_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_A_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_B_PressHandler (Gp1_B_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_B_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_X_PressHandler (Gp1_X_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_X_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Y_PressHandler (Gp1_Y_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Y_PressEvent.TYPE, handler);
    }
    //endregion

    public HandlerRegistration addGp2_Left_Bumper_PressHandler (Gp2_Left_Bumper_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Left_Bumper_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Right_Bumper_PressHandler (Gp2_Right_Bumper_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Right_Bumper_PressEvent.TYPE, handler);
    }

    //-----------------------------------------------------------------------------------------
    // Dpad
    //-----------------------------------------------------------------------------------------
    public HandlerRegistration addGp1_Dpad_Down_DownHandler (Gp1_Dpad_Down_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Dpad_Down_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Dpad_Up_DownHandler (Gp1_Dpad_Up_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Dpad_Up_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Dpad_Left_DownHandler (Gp1_Dpad_Left_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Dpad_Left_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Dpad_Right_DownHandler (Gp1_Dpad_Right_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Dpad_Right_DownEvent.TYPE, handler);
    }

    //------------------------------------------------------------------------------------------

    public HandlerRegistration addGp1_Dpad_Down_PressHandler (Gp1_Dpad_Down_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Dpad_Down_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Dpad_Up_PressHandler (Gp1_Dpad_Up_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Dpad_Up_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Dpad_Left_PressHandler (Gp1_Dpad_Left_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Dpad_Left_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Dpad_Right_PressHandler (Gp1_Dpad_Right_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Dpad_Right_PressEvent.TYPE, handler);
    }

    //---------------------------------------------------------------------------------------------

    public HandlerRegistration addGp2_Dpad_Left_DownHandler (Gp2_Dpad_Left_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Left_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Dpad_Right_DownHandler (Gp2_Dpad_Right_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Right_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Dpad_Up_DownHandler (Gp2_Dpad_Up_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Up_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Dpad_Down_DownHandler (Gp2_Dpad_Down_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Down_DownEvent.TYPE, handler);
    }

    //----------------------------------------------------------------------------------------------

    public HandlerRegistration addGp2_Dpad_Left_PressHandler (Gp2_Dpad_Left_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Left_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Dpad_Right_PressHandler (Gp2_Dpad_Right_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Right_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Dpad_Up_PressHandler (Gp2_Dpad_Up_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Up_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Dpad_Down_PressHandler (Gp2_Dpad_Down_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Down_PressEvent.TYPE, handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_LeftStick_X_Handler (Gp1_LeftStick_X_Handler handler) {
        return EventBus.getInstance().addHandler(Gp1_LeftStick_X_Event.TYPE, handler);
    }

    public HandlerRegistration addGp1_LeftStick_Y_Handler (Gp1_LeftStick_Y_Handler handler) {
        return EventBus.getInstance().addHandler(Gp1_LeftStick_Y_Event.TYPE, handler);
    }

    public HandlerRegistration addGp1_RightStick_X_Handler (Gp1_RightStick_X_Handler handler) {
        return EventBus.getInstance().addHandler(Gp1_RightStick_X_Event.TYPE, handler);
    }

    public HandlerRegistration addGp1_RightStick_Y_Handler (Gp1_RightStick_Y_Handler handler) {
        return EventBus.getInstance().addHandler(Gp1_RightStick_Y_Event.TYPE, handler);
    }

    //---------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_LeftStick_X_Handler (Gp2_LeftStick_X_Handler handler) {
        return EventBus.getInstance().addHandler(Gp2_LeftStick_X_Event.TYPE, handler);
    }

    public HandlerRegistration addGp2_LeftStick_Y_Handler (Gp2_LeftStick_Y_Handler handler) {
        return EventBus.getInstance().addHandler(Gp2_LeftStick_Y_Event.TYPE, handler);
    }

    public HandlerRegistration addGp2_RightStick_X_Handler (Gp2_RightStick_X_Handler handler) {
        return EventBus.getInstance().addHandler(Gp2_RightStick_X_Event.TYPE, handler);
    }

    public HandlerRegistration addGp2_RightStick_Y_Handler (Gp2_RightStick_Y_Handler handler) {
        return EventBus.getInstance().addHandler(Gp2_RightStick_Y_Event.TYPE, handler);
    }

    //----------------------------------------------------------------------------------------------

    public HandlerRegistration addGp1_Back_PressHandler (Gp1_Back_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Back_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Back_PressHandler (Gp2_Back_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Back_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp1_Start_PressHandler (Gp1_Start_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp1_Start_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Start_PressHandler (Gp2_Start_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Start_PressEvent.TYPE, handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    public IsaacBot getRobot () {
        return this.robot;
    }

    /**
     *
     * @param event
     */
    public void fireEvent (Event<?> event) {
        EventBus.getInstance().fireEvent(event);
    }

    /**
     *
     */
    public void init (){}

    /**
     *
     * @return boolean
     */
    public boolean isDebug() {
        return this.debug;
    }

    /**
     *
     * @param debug
     */
    public void run(boolean debug) {
        this.setDebug(debug);

        this.run();
    }

    /**
     *
     */
    public void run() {
    }

    /**
     *
     * @param debug
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
