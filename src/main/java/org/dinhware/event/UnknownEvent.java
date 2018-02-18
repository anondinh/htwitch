package org.dinhware.event;

import org.dinhware.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 13:44
 */

public class UnknownEvent extends Event {

    public UnknownEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
    }
}
