package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger.Gp1_Left_Trigger_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger.Gp1_Left_Trigger_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger.Gp1_Right_Trigger_Event;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger.Gp1_Right_Trigger_Handler;

@TeleOp(name="GamePad1TriggersTest", group="Tests")
public class GamePad1TriggersTest extends IsaacBot
{
    /**
     *
     */
    public GamePad1TriggersTest () {
        super();
    }

    /**
     *
     */
    public void initBot() {
        super.initBot();

        this.addGp1_Left_Trigger_Handler(new Gp1_Left_Trigger_Handler() {
            @Override
            public void onGp1_Left_Trigger(Gp1_Left_Trigger_Event event) {
                GamePad1TriggersTest.this.telemetry.log().add("Left trigger pressed! (" + event.getPosition() + ")");
            }
        });

        this.addGp1_Right_Trigger_Handler(new Gp1_Right_Trigger_Handler() {
            @Override
            public void onGp1_Right_Trigger(Gp1_Right_Trigger_Event event) {
                GamePad1TriggersTest.this.telemetry.log().add("Right trigger pressed! (" + event.getPosition() + ")");
            }
        });
    }

    /**
     *
     */
    public void go() {

    }

    public void run() {
        super.run();
    }
}
