package org.dinhware.bot.adapter.core;

import org.dinhware.bot.adapter.Listener;
import org.dinhware.bot.adapter.ListenerType;
import org.dinhware.bot.event.RoomStateEvent;
import org.dinhware.bot.objects.EventType;

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
