package org.firstinspires.ftc.library.pixelcatcher.events.leftarmopen;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class PixelCatcherLeftArmOpenEvent extends Event<PixelCatcherLeftArmOpenHandler> {

    /**
     *
     */
    public static EventType<PixelCatcherLeftArmOpenHandler> TYPE = new EventType<PixelCatcherLeftArmOpenHandler>();

    /**
     * Constructor
     *
     */
    public PixelCatcherLeftArmOpenEvent() {

    }

    @Override
    public EventType<PixelCatcherLeftArmOpenHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(PixelCatcherLeftArmOpenHandler handler) {
        handler.onLeftArmOpen(this);
    }
}
