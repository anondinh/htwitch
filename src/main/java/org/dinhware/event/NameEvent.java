package org.dinhware.event;

import org.dinhware.adapter.Event;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by: Niklas
 * Date: 24.01.2018
 * Alias: Dinh
 * Time: 13:45
 */

public class NameEvent extends Event {

    private List<String> names;

    public NameEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.names = Arrays.asList(line.split(":")[2].split(" "));
    }

    public List<String> getNames() {
        return names;
    }
}
