package org.dinhware.adapter.core;

import org.dinhware.adapter.Listener;
import org.dinhware.adapter.ListenerType;
import org.dinhware.event.RoomStateEvent;
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
        onRoomState(new RoomStateEvent(tags, arguments, line));
    }

    protected abstract void onRoomState(RoomStateEvent event);

}
