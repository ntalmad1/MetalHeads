package org.firstinspires.ftc.teamcode.library.component;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.component.command.Command;
import org.firstinspires.ftc.teamcode.library.component.command.CommandQueue;
import org.firstinspires.ftc.teamcode.library.component.event.EventBus;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_dpad_down_down.Gp2_Dpad_Down_DownEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_dpad_down_down.Gp2_Dpad_Down_DownHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_dpad_left_down.Gp2_Dpad_Left_DownEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_dpad_left_down.Gp2_Dpad_Left_DownHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_dpad_right_down.Gp2_Dpad_Right_DownHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_dpad_right_down.Gp2_Dpad_Right_Down_DownEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_dpad_up_down.Gp2_Dpad_Up_DownEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_dpad_up_down.Gp2_Dpad_Up_DownHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_bumper_press.Gp2_Left_Bumper_PressEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_bumper_press.Gp2_Left_Bumper_PressHandler;
import org.firstinspires.ftc.teamcode.library.component.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_a_press.Gp2_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_a_press.Gp2_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_b_press.Gp2_B_PressEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_b_press.Gp2_B_PressHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_y.Gp2_LeftStickYEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_y.Gp2_LeftStickYHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_bumper_press.Gp2_Right_Bumper_PressEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_bumper_press.Gp2_Right_Bumper_PressHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_x.Gp2_RightStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_x.Gp2_RightStickXHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_y.Gp2_RightStickYEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_y.Gp2_RightStickYHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_x_press.Gp2_X_PressEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_x_press.Gp2_X_PressHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_y_press.Gp2_Y_PressEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_y_press.Gp2_Y_PressHandler;

/**
 *
 */
public abstract class Component implements IComponent {

    /**
     */
    private CommandQueue commandQueue;

    /**
     */
    protected IsaacBot robot;

    /**
     */
    public Telemetry telemetry;

    /**
     *
     * @param robot
     */
    public Component (IsaacBot robot)
    {
        this.robot = robot;

        this.telemetry = this.robot.telemetry;

        this.commandQueue = new CommandQueue(this);
    }

    /**
     *
     * @param command
     */
    public void addCommand (Command command) {
        this.commandQueue.add(command);
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

    public HandlerRegistration addGp2_Left_Bumper_PressHandler (Gp2_Left_Bumper_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Left_Bumper_PressEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Right_Bumper_PressHandler (Gp2_Right_Bumper_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Right_Bumper_PressEvent.TYPE, handler);
    }


    //-----------------------------------------------------------------------------------------

    public HandlerRegistration addGp2_Dpad_Left_DownHandler (Gp2_Dpad_Left_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Left_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Dpad_Right_DownHandler (Gp2_Dpad_Right_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Right_Down_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Dpad_Up_DownHandler (Gp2_Dpad_Up_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Up_DownEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_Dpad_Down_DownHandler (Gp2_Dpad_Down_DownHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_Dpad_Down_DownEvent.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_LeftStickXHandler (Gp2_LeftStickXHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_LeftStickXEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_LeftStickYHandler (Gp2_LeftStickYHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_LeftStickYEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_RightStickXHandler (Gp2_RightStickXHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_RightStickXEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_RightStickYHandler (Gp2_RightStickYHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_RightStickYEvent.TYPE, handler);
    }

    /**
     *
     */
    public void cancelAllCommands () {
        this.commandQueue.clear();
    }

    /**
     *
     * @return
     */
    public CommandQueue getCommandQueue () {
        return this.commandQueue;
    }

    /**
     *
     */
    public void init (){}

    /**
     *
     */
    public void run (){
        this.commandQueue.run();
    }

    public void runCommand (Command command) {
        if (command.isCompleted()) {
            return;
        }
        command.run();
    }
}
