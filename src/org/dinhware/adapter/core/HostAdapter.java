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

@ListenerType(type = EventType.HOSTTARGET)
public abstract class HostAdapter implements Listener {

    @Override
    public void dispatch(Map<String, String> tags, String[] arguments, String line) {
        UnknownEvent event = new UnknownEvent(tags, arguments, line);
        if (arguments.length == 4) {
            onHostStart(event);
        } else {
            onHostEnd(event);
        }
    }

    protected abstract void onHostStart(Event event);

    protected abstract void onHostEnd(Event event);
}
