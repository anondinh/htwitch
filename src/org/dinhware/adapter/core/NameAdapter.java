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

@ListenerType(type = EventType.NAMES)
public abstract class NameAdapter implements Listener {

    @Override
    public void dispatch(Map<String, String> tags, String[] arguments, String line) {
        onNames(new UnknownEvent(tags, arguments, line));
    }

    protected abstract void onNames(Event event);
}