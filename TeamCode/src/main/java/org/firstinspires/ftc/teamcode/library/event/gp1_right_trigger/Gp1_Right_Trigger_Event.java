package org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Right_Trigger_Event extends Event<Gp1_Right_Trigger_Handler> {

    public final static EventType<Gp1_Right_Trigger_Handler> TYPE = new EventType<Gp1_Right_Trigger_Handler>();

    /**
     *
     */
    private float position;

    /**
     *
     * @param pos
     */
    public Gp1_Right_Trigger_Event(float pos) {
        this.position = pos;
    }

    /**
     *
     * @return
     */
    public double getPosition () {
        return this.position;
    }

    @Override
    public EventType<Gp1_Right_Trigger_Handler> getType() {
        return TYPE;
    }

    @Override
    public void handle(Gp1_Right_Trigger_Handler handler) {
        handler.onGp1_Right_Trigger(this);
    }
}
