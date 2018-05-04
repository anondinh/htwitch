package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 24.01.2018
 * Alias: Dinh
 * Time: 13:25
 */

public class HostEvent extends Event {

    private boolean isStart;
    private int amount;

    public HostEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.isStart = tags.containsKey("number-of-viewers");
        this.amount = isStart ? Integer.parseInt(tags.get("number-of-viewers")) : 0;
    }

    public boolean isStart() {
        return isStart;
    }

    public int getAmount() {
        return amount;
    }
}
