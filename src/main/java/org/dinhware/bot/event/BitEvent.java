package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 14:26
 */

public class BitEvent extends Event {
    private String name, loginName;
    private int amount;

    public BitEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.name = tags.get("display-name");
        this.loginName = arguments[1].substring(1).split("!")[0];
        this.amount = Integer.parseInt(tags.get("bits"));
    }

    public String getName() {
        return name;
    }

    public String getLoginName() {
        return loginName;
    }

    public int getAmount() {
        return amount;
    }
}
