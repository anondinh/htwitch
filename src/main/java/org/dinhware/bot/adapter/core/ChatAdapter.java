package org.dinhware.bot.adapter.core;

import org.dinhware.bot.adapter.Listener;
import org.dinhware.bot.adapter.ListenerType;
import org.dinhware.bot.event.ClearChatEvent;
import org.dinhware.bot.objects.EventType;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 11:40
 */

@ListenerType(type = EventType.CLEARCHAT)
public abstract class ChatAdapter implements Listener {

    @Override
    public void dispatch(Map<String, String> tags, String[] arguments, String line) {
        ClearChatEvent event = new ClearChatEvent(tags, arguments, line);
        if (tags.containsKey("ban-duration")) {
            onTimeout(event);
        } else {
            onBan(event);
        }
    }

    protected abstract void onTimeout(ClearChatEvent event);

    protected abstract void onBan(ClearChatEvent event);
}
