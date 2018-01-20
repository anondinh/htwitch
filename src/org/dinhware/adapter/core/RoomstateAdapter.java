package org.dinhware.adapter.core;

import org.dinhware.adapter.Event;
import org.dinhware.adapter.Listener;
import org.dinhware.adapter.ListenerType;
import org.dinhware.event.UnknownEvent;
import org.dinhware.objects.EventType;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 11:40
 */

@ListenerType(type = EventType.ROOMSTATE)
public abstract class RoomstateAdapter implements Listener {

    @Override
    public void dispatch(Map<String, String> tags, String[] arguments, String line) {
        UnknownEvent event = new UnknownEvent(tags, arguments, line);
        if (tags.size() == 1) {
            onRoomStateChange(event);
        } else {
            onRoomState(event);
        }
    }

    protected abstract void onRoomState(Event event);

    protected abstract void onRoomStateChange(Event event);

}
