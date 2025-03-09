package org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Left_Trigger_Event extends Event<Gp1_Left_Trigger_Handler> {

    /**
     */
    public final static EventType<Gp1_Left_Trigger_Handler> TYPE = new EventType<Gp1_Left_Trigger_Handler>();

    /**
     */
    private float position;

    /**
     * Constructor
     *
     */
    public Gp1_Left_Trigger_Event(float pos) {
        this.position = pos;
    }

    /**
     *
     * @return float
     */
    public float getPosition () {
        return this.position;
    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp1_Left_Trigger_Handler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp1_Left_Trigger_Handler handler) {
        handler.onGp1_Left_Trigger(this);
    }
}
