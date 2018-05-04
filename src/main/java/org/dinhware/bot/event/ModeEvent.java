package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 12:50
 */

public class ModeEvent extends Event {

    private String user;
    private boolean isMod;

    public ModeEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.user = arguments[4];
        this.isMod = arguments[3].startsWith("+");
    }

    public String getUser() {
        return user;
    }

    public boolean isMod() {
        return isMod;
    }
}
