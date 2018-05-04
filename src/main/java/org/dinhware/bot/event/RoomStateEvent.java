package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 24.01.2018
 * Alias: Dinh
 * Time: 13:48
 */

public class RoomStateEvent extends Event {

    private boolean isOriginal;

    public RoomStateEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.isOriginal = tags.size() > 1;
    }

    public boolean isOriginal() {
        return isOriginal;
    }
}
