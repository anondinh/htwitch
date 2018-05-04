package org.dinhware.bot.adapter.core;

import org.dinhware.bot.adapter.Listener;
import org.dinhware.bot.adapter.ListenerType;
import org.dinhware.bot.event.NoticeEvent;
import org.dinhware.bot.objects.EventType;

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
        onNotice(new NoticeEvent(tags, arguments, line));
    }

    protected abstract void onNotice(NoticeEvent event);
}
