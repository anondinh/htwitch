package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 13:24
 */

public class ClearChatEvent extends Event {

    private String reason, user;
    private int duration;

    public ClearChatEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.reason = tags.get("ban-reason");
        this.user = arguments[4].substring(1);
        if (tags.containsKey("ban-duration")) {
            duration = Integer.parseInt(tags.get("ban-duration"));
        }
    }

    public String getReason() {
        return reason;
    }

    public String getUser() {
        return user;
    }

    public int getDuration() {
        return duration;
    }
}
