package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;
import org.dinhware.bot.objects.User;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 12:50
 */

public class MessageEvent extends Event {

    private String displayed;
    private String[] message;
    private User user;

    public MessageEvent(Map<String, String> tags, String[] arguments, String line, String[] message, String displayed) {
        super(tags, arguments, line);
        this.displayed = displayed;
        this.message = message;
        this.user = new User(getChannel(), tags.get("display-name"), Long.parseLong(tags.get("user-id")));
    }

    public String getDisplayed() {
        return displayed;
    }

    public String[] getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
