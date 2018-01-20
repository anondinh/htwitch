package org.dinhware.adapter.core;

import org.dinhware.adapter.Event;
import org.dinhware.adapter.Listener;
import org.dinhware.adapter.ListenerType;
import org.dinhware.event.UnknownEvent;
import org.dinhware.objects.EventType;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 19.01.2018
 * Alias: Dinh
 * Time: 22:22
 */

@ListenerType(type = EventType.NOTICE)
public abstract class NoticeAdapter implements Listener {

    @Override
    public void dispatch(Map<String, String> tags, String[] arguments, String line) {
        onNotice(new UnknownEvent(tags, arguments, line));
    }

    protected abstract void onNotice(Event event);
}
