package org.dinhware.event;

import org.dinhware.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 14:26
 */

public class BitEvent extends Event {
    private String name, loginName;

    public BitEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.name = tags.get("display-name");
        this.loginName = arguments[1].substring(1).split("!")[0];
    }

    public String getName() {
        return name;
    }

    public String getLoginName() {
        return loginName;
    }
}
