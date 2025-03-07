package org.firstinspires.ftc.teamcode.library;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.action.ActionQueue;
import org.firstinspires.ftc.teamcode.library.component.IComponent;
import org.firstinspires.ftc.teamcode.library.component.RobotComponent;
import org.firstinspires.ftc.teamcode.library.event.EventBus;
import org.firstinspires.ftc.teamcode.library.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.event.gp1_a_press.Gp1_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_b_press.Gp1_B_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_back_press.Gp1_Back_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_down_down.Gp1_Dpad_Down_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_left_down.Gp1_Dpad_Left_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_right_down.Gp1_Dpad_Right_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_up_down.Gp1_Dpad_Up_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_left_press.Gp1_Dpad_Left_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_right_press.Gp1_Dpad_Right_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_bumper_down.Gp1_Left_Bumper_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_bumper_up.Gp1_Left_Bumper_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_x.Gp1_LeftStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_y.Gp1_LeftStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger.Gp1_Left_Trigger_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger_down.Gp1_Left_Trigger_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger_up.Gp1_Left_Trigger_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_down.Gp1_Right_Bumper_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_up.Gp1_Right_Bumper_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_x.Gp1_RightStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_y.Gp1_RightStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger.Gp1_Right_Trigger_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_down.Gp1_Right_Trigger_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_up.Gp1_Right_Trigger_UpHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_start_press.Gp1_Start_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_x_press.Gp1_X_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_y_press.Gp1_Y_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_a_press.Gp2_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_b_press.Gp2_B_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_back_press.Gp2_Back_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_down_down.Gp2_Dpad_Down_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_left_down.Gp2_Dpad_Left_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_right_down.Gp2_Dpad_Right_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_up_down.Gp2_Dpad_Up_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_down_press.Gp2_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_left_press.Gp2_Dpad_Left_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_right_press.Gp2_Dpad_Right_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_up_press.Gp2_Dpad_Up_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_press.Gp2_Left_Bumper_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_x.Gp2_LeftStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_y.Gp2_LeftStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_press.Gp2_Right_Bumper_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_x.Gp2_RightStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_y.Gp2_RightStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_start_press.Gp2_Start_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_x_press.Gp2_X_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_y_press.Gp2_Y_PressHandler;

/**
 *
 * @noinspection unused
 */
public abstract class  IsaacBot extends LinearOpMode implements IComponent
{
    /**
     */
    private static IsaacBot instance;

    /**
     *
     * @return IsaacBot
     */
    public static IsaacBot getInstance() {
        return instance;
    }

    /**
     */
    protected final RobotComponent robotComponent;

    /**
     */
    private String imuName;

    /**
     */
    private IMU imu;

    /**
     */
    private ActionQueue actionQueue;

    /**
     * Constructor
     *
     */
    public IsaacBot()
    {
        super();

        instance = this;

        EventBus.init(this);

        this.robotComponent = new RobotComponent(this);
        this.actionQueue = new ActionQueue();
    }

//region addHandlers

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_A_PressHandler (Gp1_A_PressHandler handler) {
        return this.robotComponent.addGp1_A_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_B_PressHandler (Gp1_B_PressHandler handler) {
        return this.robotComponent.addGp1_B_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_A_PressHandler (Gp2_A_PressHandler handler) {
        return this.robotComponent.addGp2_A_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_B_PressHandler (Gp2_B_PressHandler handler) {
        return this.robotComponent.addGp2_B_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_X_PressHandler (Gp2_X_PressHandler handler) {
        return this.robotComponent.addGp2_X_PressHandler(handler);
    }

    public HandlerRegistration addGp2_Y_PressHandler (Gp2_Y_PressHandler handler) {
        return this.robotComponent.addGp2_Y_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_X_PressHandler (Gp1_X_PressHandler handler) {
        return this.robotComponent.addGp1_X_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Y_PressHandler (Gp1_Y_PressHandler handler) {
        return this.robotComponent.addGp1_Y_PressHandler(handler);
    }

    //----------------------------------------------------------------------------------------------

    public HandlerRegistration addGp1_Left_Bumper_DownHandler (Gp1_Left_Bumper_DownHandler handler) {
        return this.robotComponent.addGp1_Left_Bumper_DownHandler(handler);
    }

    public HandlerRegistration addGp1_Left_Bumper_UpHandler (Gp1_Left_Bumper_UpHandler handler) {
        return this.robotComponent.addGp1_Left_Bumper_UpHandler(handler);
    }

    public HandlerRegistration addGp1_Right_Bumper_DownHandler (Gp1_Right_Bumper_DownHandler handler) {
        return this.robotComponent.addGp1_Right_Bumper_DownHandler(handler);
    }

    public HandlerRegistration addGp1_Right_Bumper_UpHandler (Gp1_Right_Bumper_UpHandler handler) {
        return this.robotComponent.addGp1_Right_Bumper_UpHandler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Left_Bumper_PressHandler (Gp2_Left_Bumper_PressHandler handler) {
        return this.robotComponent.addGp2_Left_Bumper_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Right_Bumper_PressHandler (Gp2_Right_Bumper_PressHandler handler) {
        return this.robotComponent.addGp2_Right_Bumper_PressHandler(handler);
    }

    //-----------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Down_DownHandler (Gp1_Dpad_Down_DownHandler handler) {
        return this.robotComponent.addGp1_Dpad_Down_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Up_DownHandler (Gp1_Dpad_Up_DownHandler handler) {
        return this.robotComponent.addGp1_Dpad_Up_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Left_DownHandler (Gp1_Dpad_Left_DownHandler handler) {
        return this.robotComponent.addGp1_Dpad_Left_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Right_DownHandler (Gp1_Dpad_Right_DownHandler handler) {
        return this.robotComponent.addGp1_Dpad_Right_DownHandler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Down_PressHandler (Gp1_Dpad_Down_PressHandler handler) {
        return this.robotComponent.addGp1_Dpad_Down_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Up_PressHandler (Gp1_Dpad_Up_PressHandler handler) {
        return this.robotComponent.addGp1_Dpad_Up_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Left_PressHandler (Gp1_Dpad_Left_PressHandler handler) {
        return this.robotComponent.addGp1_Dpad_Left_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Right_PressHandler (Gp1_Dpad_Right_PressHandler handler) {
        return this.robotComponent.addGp1_Dpad_Right_PressHandler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Left_DownHandler (Gp2_Dpad_Left_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Left_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Right_DownHandler (Gp2_Dpad_Right_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Right_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Up_DownHandler (Gp2_Dpad_Up_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Up_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Down_DownHandler (Gp2_Dpad_Down_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Down_DownHandler(handler);
    }

    //-----------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Down_PressHandler (Gp2_Dpad_Down_PressHandler handler) {
        return this.robotComponent.addGp2_Dpad_Down_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Up_PressHandler (Gp2_Dpad_Up_PressHandler handler) {
        return this.robotComponent.addGp2_Dpad_Up_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Left_PressHandler (Gp2_Dpad_Left_PressHandler handler) {
        return this.robotComponent.addGp2_Dpad_Left_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Right_PressHandler (Gp2_Dpad_Right_PressHandler handler) {
        return this.robotComponent.addGp2_Dpad_Right_PressHandler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_LeftStick_X_Handler (Gp1_LeftStick_X_Handler handler) {
        return this.robotComponent.addGp1_LeftStick_X_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_LeftStick_Y_Handler (Gp1_LeftStick_Y_Handler handler) {
        return this.robotComponent.addGp1_LeftStick_Y_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_RightStick_X_Handler (Gp1_RightStick_X_Handler handler) {
        return this.robotComponent.addGp1_RightStick_X_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_RightStick_Y_Handler (Gp1_RightStick_Y_Handler handler) {
        return this.robotComponent.addGp1_RightStick_Y_Handler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_LeftStick_X_Handler (Gp2_LeftStick_X_Handler handler) {
        return this.robotComponent.addGp2_LeftStick_X_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_LeftStick_Y_Handler (Gp2_LeftStick_Y_Handler handler) {
        return this.robotComponent.addGp2_LeftStick_Y_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_RightStick_X_Handler (Gp2_RightStick_X_Handler handler) {
        return this.robotComponent.addGp2_RightStick_X_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_RightStick_Y_Handler (Gp2_RightStick_Y_Handler handler) {
        return this.robotComponent.addGp2_RightStick_Y_Handler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_RightTrigger_DownHandler(Gp1_Right_Trigger_DownHandler handler) {
        return this.robotComponent.addGp1_Right_Trigger_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_RightTrigger_UpHandler(Gp1_Right_Trigger_UpHandler handler) {
        return this.robotComponent.addGp1_Right_Trigger_UpHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_LeftTrigger_DownHandler(Gp1_Left_Trigger_DownHandler handler) {
        return this.robotComponent.addGp1_Left_Trigger_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_LeftTrigger_UpHandler(Gp1_Left_Trigger_UpHandler handler) {
        return this.robotComponent.addGp1_Left_Trigger_UpHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Left_Trigger_Handler(Gp1_Left_Trigger_Handler handler) {
        return this.robotComponent.addGp1_Left_Trigger_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Right_Trigger_Handler(Gp1_Right_Trigger_Handler handler) {
        return this.robotComponent.addGp1_Right_Trigger_Handler(handler);
    }

    //----------------------------------------------------------------------------------------------

    public HandlerRegistration addGp1_Back_PressHandler (Gp1_Back_PressHandler handler) {
        return this.robotComponent.addGp1_Back_PressHandler(handler);
    }

    public HandlerRegistration addGp2_Back_PressHandler (Gp2_Back_PressHandler handler) {
        return this.robotComponent.addGp2_Back_PressHandler(handler);
    }

    public HandlerRegistration addGp1_Start_PressHandler (Gp1_Start_PressHandler handler) {
        return this.robotComponent.addGp1_Start_PressHandler(handler);
    }

    public HandlerRegistration addGp2_Start_PressHandler (Gp2_Start_PressHandler handler) {
        return this.robotComponent.addGp2_Start_PressHandler(handler);
    }

    //endregion

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    public EventBus getEventBus () {
        return EventBus.getInstance();
    }

    /**
     *
     * @return
     */
    public double getYaw ()
    {
        YawPitchRollAngles gyro = imu.getRobotYawPitchRollAngles();
        return gyro.getYaw(AngleUnit.DEGREES);
    }

    /**
     *
     */
    public void resetYaw () {
        this.initImu(this.imuName);
    }

    /**
     *
     * @param imuName
     */
    public void initImu (String imuName)
    {
        this.imu = this.hardwareMap.get(IMU.class, imuName);
        this.imu.resetYaw();
    }

    /**
     * This code gets ran at the start of runOpMode before start is pressed
     */
    public void initBot () {
        this.robotComponent.init();
    }

    /**
     * This code gets ran right after start pressed and is only ran once
     */
    public void go () {

    }

    /**
     * This code gets ran after start pressed and "whileOpIsActive" in a loop
     */
    public void run () {
        this.getEventBus().run();
        this.robotComponent.run();
        this.actionQueue.run();
    }

    /**
     * This code gets ran right after the stop is pressed and is only ran once
     */
    public void onStop () {
    }

    /**
     *
     */
    public void killAllActions () {
        this.actionQueue.killAllActions();
    }

    /**
     *
     * @param action
     */
    public void runAction (AbstractAction action) {
        this.killAllActions();

        this.actionQueue.add(action);
    }

    /**
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {
        this.initBot();

        this.waitForStart();

        this.go();

        while (this.opModeIsActive()) {
            this.run();
        }

        this.onStop();
    }

    /**
     *
     * @param imuName
     */
    public void setImuName (String imuName) {
        this.imuName = imuName;
    }

}
