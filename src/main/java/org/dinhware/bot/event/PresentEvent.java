package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;
import org.dinhware.bot.objects.User;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 15:31
 */

public class PresentEvent extends Event {

    private boolean isPresent;
    private User user;

    public PresentEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.isPresent = arguments[1].equals("JOIN");
        this.user = new User(getChannel(), line.substring(1).split("!")[0], -1L);
    }

    public boolean isPresent() {
        return isPresent;
    }

    public User getUser() {
        return user;
    }
}
