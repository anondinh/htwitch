package org.dinhware.event;

import org.dinhware.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 13:37
 */

public class RaidEvent extends Event {

    private String name;
    private int amount;

    public RaidEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.name = tags.get("msg-param-displayName");
        this.amount = Integer.parseInt(tags.get("msg-param-viewerCount"));
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
