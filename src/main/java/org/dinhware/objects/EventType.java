package org.dinhware.objects;

import java.util.Arrays;

/**
 * Created by: Niklas
 * Date: 19.01.2018
 * Alias: Dinh
 * Time: 21:59
 */

public enum EventType {
    INVALID, JOIN, PART, PRIVMSG, MODE, NAMES, CLEARCHAT, GLOBALUSERSTATE, ROOMSTATE, USERNOTICE, USERSTATE, HOSTTARGET, NOTICE, WHISPER;

    public static EventType get(String type) {
        return Arrays.stream(EventType.values()).filter(o -> o.name().equals(type)).findAny().orElseGet(() -> type.equals("353") ? NAMES : INVALID);
    }
}
