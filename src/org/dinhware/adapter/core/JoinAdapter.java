package org.dinhware.adapter.core;

import org.dinhware.adapter.Listener;
import org.dinhware.adapter.ListenerType;
import org.dinhware.event.PresentEvent;
import org.dinhware.objects.EventType;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 11:40
 */

@ListenerType(type = EventType.JOIN)
public abstract class JoinAdapter implements Listener {

    @Override
    public void dispatch(Map<String, String> tags, String[] arguments, String line) {
        PresentEvent event = new PresentEvent(tags, arguments, line);
        event.getChannel().addUser(event.getUser());
        onJoin(event);
    }

    protected abstract void onJoin(PresentEvent event);
}
