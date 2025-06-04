package org.firstinspires.ftc.teamcode.metalheads.competition.base;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.boom.arm.Arm;
import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeCommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.component.command.WaitCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.component.event.gp2_left_trigger.Gp2_Left_Trigger_Event;
import org.firstinspires.ftc.library.component.event.ping.PingEvent;
import org.firstinspires.ftc.library.component.event.ping.PingHandler;
import org.firstinspires.ftc.library.dronelauncher.DroneLauncher;
import org.firstinspires.ftc.library.dronelauncher.DroneLauncherConfig;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcherConfig;
import org.firstinspires.ftc.library.winch.ConstantOutWinchCommand;
import org.firstinspires.ftc.library.winch.ConstantPressureWinchCommand;
import org.firstinspires.ftc.library.winch.Winch;
import org.firstinspires.ftc.library.winch.WinchConfig;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.ArmCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.DroneLauncherCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.PixelCatcherCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.RobotConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.WinchCompConfig;

//No more robotics :(, goodbye repository, to buried in the depths of the internet eventually deleted

/**
 *
 */
@TeleOp(name="CompBot", group="Linear OpMode")
@Disabled
public class CompBot extends IsaacBot{

    /**
     */
    public enum ArmPosition {
        INIT,
        PIXEL_READY,
        PIXEL_PLACE_ROW1,
        PIXEL_PLACE_ROW2,
        PIXEL_PLACE_ROW3,
    }


    /**
     */
    protected Arm arm;

    /**
     */
    protected ArmCompConfig armConfig;

    /**
     */
    protected ArmPosition armPosition = ArmPosition.INIT;

    /**
     */
    protected DroneLauncher droneLauncher;

    /**
     */
    protected DroneLauncherConfig droneLauncherConfig;

    /**
     */
    protected PixelCatcherConfig pixelCatcherConfig;

    /**
     */
    protected PixelCatcher pixelCatcher;
    /**
     *
     */
    protected RobotConfig robotConfig;


    /**
     */
    protected RevTouchSensor trolleySensor;

    /**
     */
    protected Winch winch;

    /**
     */
    protected WinchConfig winchConfig;
    /**
     *
     */
    private float hangPower = (float)0.1;

    /**
     */
    private boolean pickingPixels = false;


    /**
     * Constructor
     *
     */
    public CompBot() {
        super();

        this.robotConfig = new RobotConfig();

        this.armConfig = new ArmCompConfig(this);
        this.armConfig.debug = false;

        this.droneLauncherConfig = new DroneLauncherCompConfig(this);

        this.pixelCatcherConfig = new PixelCatcherCompConfig(this);

        this.winchConfig = new WinchCompConfig(this);
        this.winchConfig.debug = false;

        //-------------------------------------------------
        // A Button
        // First, cancel all commands then do onAPress()
        //-------------------------------------------------
        this.addGp2_A_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();
            this.onAPress();

        });

        //-------------------------------------------------
        // B Button
        // First, cancel all commands then do onBPress()
        //-------------------------------------------------
        this.addGp2_B_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();
            this.onBPress();

        });

        //-------------------------------------------------------
        // X Button
        // First, cancel all commands then do onXPress()
        // If the robot is picking pixels, move the bottom boom up
        // so the claw does not collide with the pixel catcher
        //-------------------------------------------------------
        this.addGp2_X_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

            if (this.pickingPixels) {
                this.addCommand(new OneTimeSynchronousCommand() {
                    public void runOnce(ICommand outerCommand) {
                        CompBot.this.arm.moveBottomToPosition(CompBot.this.robotConfig.pixelReady_bottomBoom, 1)
                                .wait(250, new CommandCallbackAdapter(){
                                    public void onSuccess(CommandSuccessEvent successEvent) {
                                        CompBot.this.pickingPixels = false;
                                        CompBot.this.onXPress();
                                        outerCommand.markAsCompleted();
                                    }
                                });
                    }
                });
            }
            else {
                this.onXPress();
            }
        });
    }

    /**
     *
     */
    public void initBot () {
        super.initBot();

        this.arm = new Arm(armConfig);
        this.arm.init();

        this.droneLauncher = new DroneLauncher(this.droneLauncherConfig);
        this.droneLauncher.init();

        this.pixelCatcher = new PixelCatcher(this.pixelCatcherConfig);
        this.pixelCatcher.init();

        this.winch = new Winch(this.winchConfig);
        this.winch.init();
    }

    /**
     *
     */
    public void go () {
        super.go();

        this.trolleySensor = this.hardwareMap.get(RevTouchSensor.class, "trolleySensor");
        trolleySensor.resetDeviceConfigurationForOpMode();
    }

    /**
     *
     */
    public void run () {
        super.run();
    }

    /**
     *
     * @param milliseconds
     * @param callbackHandler
     * @return
     */
    public CompBot wait (int milliseconds, CommandCallbackHandler callbackHandler) {
        return (CompBot) super.wait(milliseconds, callbackHandler);
    }

    /**
     *
     */
    protected void doHang () {
        this.arm.addCommand(new OneTimeCommand() {
                    @Override
                    public void runOnce(ICommand command) {
                        CompBot.this.winch.setBrakeOn();
                        CompBot.this.arm.setBottomBoomOff();
                        command.markAsCompleted();
                    }
                });

        final ICommand constantPressureCommand = new ConstantPressureWinchCommand(this.winch, this.trolleySensor,-1, 200);

        this.arm.addCommand(constantPressureCommand);

        this.arm.moveLinearActuatorToPosition(1024, new CommandCallbackAdapter(){
            @Override
            public void onSuccess(CommandSuccessEvent successEvent) {
                constantPressureCommand.markAsCompleted();
            }
        });

        this.arm.moveClawToPosition(this.arm.getClaw().getConfig().clawBoomConfig.homePosition);
        this.arm.wait(250);

        for (int i = 0; i < 60; ++i)
        {
            this.arm.addCommand(new OneTimeCommand() {
                @Override
                public void runOnce(ICommand command) {
                  CompBot.this.getEventBus().fireEvent(new Gp2_Left_Trigger_Event(hangPower));
                  command.markAsCompleted();
                }
            });
            this.arm.wait(2);

            if (hangPower < 1) {
                hangPower += 1;
            }

            if (hangPower > 1) {
                hangPower = 1;
            }
        }
    }

    /**
     */
    protected void doHangStop () {

    }

    /**
     *
     * @return
     */
    protected RobotConfig getRobotConfig () {
        return this.robotConfig;
    }

    /**
     *
     */
    protected void moveArm_toHomePosition () {

        this.armPosition = ArmPosition.INIT;

        this.arm.moveBottomToPosition(0.3, 1);
        this.arm.moveClawToPosition(0.5, 1);
        this.arm.wait(1000);
        this.arm.moveLinearActuatorToPosition(0);
        this.arm.wait(1000);
        this.arm.moveClawToPosition(this.arm.getClaw().getConfig().clawBoomConfig.homePosition, 1);
        this.arm.moveBottomToPosition(this.arm.getBottomBoom().getConfig().homePosition, 1);
    }

    /**
     *
     */
    protected void moveArm_fromInit_toPixelReady(PixelCatcher.WinchPosition winchPosition) {
        this.armPosition = ArmPosition.PIXEL_READY;
        _moveArm_fromInit_toPixelReady(winchPosition);
    }

    /**
     *
     */
    protected void moveArm_fromInit_toPixelPlace() { _moveArm_fromInit_toPixelPlace(); }

    /**
     *
     */
    protected void moveArm_fromPixelReady_toPixelPlace () { _moveArm_fromPixelReady_toPixelPlace(); }

    /**
     *
     */
    protected void moveArm_fromPixelReady_doPixelPick () { _moveArm_fromPixelReady_doPickPixels(); }

    /**
     *
     */
    protected void moveArm_fromPixelPlace_toPixelReady () { _moveArm_fromPixelPlace_toPixelReady(); }

    /**
     *
     */
    protected void moveArm_fromPixelPlace_toPixelPlaceRow2 () { _moveArm_fromPixelPlace_toPixelPlaceRow2(); }

    protected void moveArm_fromPixelPlace_toPixelPlaceRow2 (double bottomBoomPos) {
        _moveArm_fromPixelPlace_toPixelPlaceRow2(bottomBoomPos);
    }

    /**
     *
     */
    protected void moveArm_fromPixelPlaceRow2_toPixelPlaceRow3 () { _moveArm_fromPixelPlaceRow2_toPixelPlaceRow3(); }

    /**
     *
     */
    protected void moveArm_fromPixelPlaceHigh_toPixelPlace () { _moveArm_fromPixelPlaceHigh_toPixelPlace(); }

    /**
     *
     */
    protected void moveArm_toHangReady () {
        if (this.arm.getBottomBoom().getPosition() < this.robotConfig.pixelReady_bottomBoom) {
            this.arm.moveBottomToPosition(this.robotConfig.hangReady_bottomBoom, 1);
        }

        final ICommand constantOutCommand = new ConstantOutWinchCommand(winch, this.trolleySensor, 1, 2100);

        this.arm.addCommand(constantOutCommand);

        this.arm.moveBottomToPosition(this.robotConfig.hangReady_bottomBoom, 1)
                .moveLinearActuatorToPosition(this.robotConfig.hangReady_linAct)
                .moveClawToPosition(this.robotConfig.hangReady_clawBoom, 1)
                .addCommand(new OneTimeCommand() {
                    @Override
                    public void runOnce(ICommand command) {
                        if (CompBot.this.pixelCatcher.getWinchPosition().equals(PixelCatcher.WinchPosition.DOWN)) {
                            CompBot.this.pixelCatcher.toggleWinch();
                        }
                        command.markAsCompleted();
                    }
                })
                .wait(2000, new CommandCallbackAdapter(){
                    @Override
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        constantOutCommand.markAsCompleted();
                    }
                });

    }

    /**
     *
     */
    private void onAPress () {
        if (this.armPosition.equals(ArmPosition.INIT)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_READY;
            CompBot.this.moveArm_fromInit_toPixelReady(PixelCatcher.WinchPosition.DOWN);
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW1) ||
                this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW2) ||
                this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW3)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_READY;
            CompBot.this.moveArm_fromPixelPlace_toPixelReady();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_READY)) {
            CompBot.this.moveArm_fromPixelReady_doPixelPick();
        }
    }

    /**
     *
     */
    private void onBPress () {
        if (this.armPosition.equals(ArmPosition.INIT)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_READY;
            CompBot.this.moveArm_fromInit_toPixelReady(PixelCatcher.WinchPosition.DOWN);
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW1) ||
                this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW2) ||
                this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW3)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_READY;
            CompBot.this.moveArm_fromPixelPlace_toPixelReady();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_READY)) {
            CompBot.this.moveArm_fromPixelPlace_toPixelReady();
        }
    }

    /**
     *
     */
    private void onXPress () {
        if (this.armPosition.equals(ArmPosition.INIT)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW1;
            CompBot.this.moveArm_fromInit_toPixelPlace();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW1)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW2;
            CompBot.this.moveArm_fromPixelPlace_toPixelPlaceRow2();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW2)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW3;
            CompBot.this.moveArm_fromPixelPlaceRow2_toPixelPlaceRow3();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW3)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW1;
            CompBot.this.moveArm_fromPixelPlaceHigh_toPixelPlace();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_READY)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW1;
            CompBot.this.moveArm_fromPixelReady_toPixelPlace();
        }
    }

    /**
     *
     */
    private void _moveArm_fromInit_toPixelReady (PixelCatcher.WinchPosition winchPosition) {

        if (!this.pixelCatcher.getWinchPosition().equals(winchPosition)){
            this.pixelCatcher.toggleWinch();
        }

        this.arm.moveBottomToPosition(0.25, 0.001)
                .moveLinearActuatorToPosition(this.robotConfig.pixelReady_linAct)
                .addCommand(new WaitCommand(250));
        this.arm.moveClawToPosition(this.robotConfig.pixelReady_clawBoom, 1)
                .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1);
    }

    /**
     *
     */
    private void _moveArm_fromInit_toPixelPlace () {
        if (this.pixelCatcher.getWinchPosition().equals(PixelCatcher.WinchPosition.UP)){
            this.pixelCatcher.toggleWinch();
        }
        this.arm.moveBottomToPosition(0.3, 1)
                .moveLinearActuatorToPosition(this.robotConfig.pixelPlace_linAct)
                .addCommand(new WaitCommand(250));
        this.arm.moveClawToPosition(this.robotConfig.pixelPlace_clawBoom, 1)
                .moveBottomToPosition(this.robotConfig.pixelPlace_bottomBoom, 1);
    }

    private void _moveArm_fromPixelPlace_toPixelPlaceRow2 () {
        this._moveArm_fromPixelPlace_toPixelPlaceRow2(this.robotConfig.pixelPlaceRow2_bottomBoom);
    }

    /**
     *
     */
    private void _moveArm_fromPixelPlace_toPixelPlaceRow2 (final double bottomBoomPos) {
        this.arm.addCommand(new OneTimeCommand() {
            @Override
            public void runOnce(ICommand command) {
                if (PixelCatcher.WinchPosition.DOWN.equals(CompBot.this.pixelCatcher.getWinchPosition()))
                {
                    CompBot.this.pixelCatcher.toggleWinch();
                }

                command.markAsCompleted();
            }
        });
        this.arm.wait(250);
        this.arm.moveBottomToPosition(this.robotConfig.pixelPlaceRow2_bottomBoom, 1)
                .moveLinearActuatorToPosition(this.robotConfig.pixelPlaceRow2_linAct)
                .moveClawToPosition(this.robotConfig.pixelPlaceRow2_clawBoom, 1)
                .wait(0);
    }

    /**
     *
     */
    private void _moveArm_fromPixelPlaceRow2_toPixelPlaceRow3 () {
        this.arm.moveBottomToPosition(this.robotConfig.pixelPlaceRow3_bottomBoom, 1)
                .moveLinearActuatorToPosition(this.robotConfig.pixelPlaceRow3_linAct)
                .moveClawToPosition(this.robotConfig.pixelPlaceRow3_clawBoom, 1)
                .wait(0);
    }

    /**
     *
     */
    private void _moveArm_fromPixelPlace_toPixelReady() {
        this.arm.addCommand(new OneTimeCommand() {
                    @Override
                    public void runOnce(ICommand command) {
                        if (PixelCatcher.WinchPosition.UP.equals(CompBot.this.pixelCatcher.getWinchPosition()))
                        {
                            CompBot.this.pixelCatcher.toggleWinch();
                        }

                        command.markAsCompleted();
                    }
                });
        this.arm.wait(350);
        this.arm.moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                .moveLinearActuatorToPosition(this.robotConfig.pixelReady_linAct)
                .moveClawToPosition(this.robotConfig.pixelReady_clawBoom, 1)
                .wait(0);
    }

    /**
     *
     */
    private void _moveArm_fromPixelReady_toPixelPlace () {
        this.arm.moveBottomToPosition(this.robotConfig.pixelPlace_bottomBoom, 1)
                .moveLinearActuatorToPosition(this.robotConfig.pixelPlace_linAct)
                .moveClawToPosition(this.robotConfig.pixelPlace_clawBoom, 1)
                .wait(0);
    }

    /**c
     *
     */
    private void _moveArm_fromPixelPlaceHigh_toPixelPlace () {
        this.arm.moveBottomToPosition(this.robotConfig.pixelPlace_bottomBoom, 1)
                .moveLinearActuatorToPosition(this.robotConfig.pixelPlace_linAct)
                .moveClawToPosition(this.robotConfig.pixelPlace_clawBoom, 1)
                .wait(0);
    }

    /**
     *
     */
    private void _moveArm_fromPixelReady_doPickPixels () {
        this.arm.wait(0, new CommandCallbackAdapter () {
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        CompBot.this.pickingPixels = true;
                    }
                })
                .closeLeftClaw()
                .closeRightClaw()
                .wait(350)
                .moveBottomToPosition(this.robotConfig.pixelPick_bottomBoom, 1)
                .moveClawToPosition(this.robotConfig.pixelPick_clawBoom, 0.25)
                .wait(350)
                .openLeftClaw()
                .openRightClaw();

        this.arm.addCommand(new OneTimeCommand() {
                    @Override
                    public void runOnce(ICommand leftArmCommand) {
                        CompBot.this.pixelCatcher.setLeftArmPosition(0.6);
                        leftArmCommand.markAsCompleted();
                    }
                });
        this.arm.addCommand(new OneTimeCommand() {
                    @Override
                    public void runOnce(ICommand rightArmCommand) {
                        CompBot.this.pixelCatcher.setRightArmPosition(0.6);
                        rightArmCommand.markAsCompleted();
                    }
                });

        this.arm.wait(250)
                .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                .moveClawToPosition(this.robotConfig.pixelReady_clawBoom, 0.25)
                .wait(500, new CommandCallbackAdapter () {
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        CompBot.this.pickingPixels = false;
                    }
                });
    }
}
