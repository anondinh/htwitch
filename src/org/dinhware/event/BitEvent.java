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
    public BitEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
    }
}
