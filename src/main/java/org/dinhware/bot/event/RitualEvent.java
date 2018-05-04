package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 13:37
 */

public class RitualEvent extends Event {

    public RitualEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
    }
}
