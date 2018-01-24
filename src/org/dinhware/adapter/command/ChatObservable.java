package org.dinhware.adapter.command;

import org.dinhware.event.MessageEvent;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 14:29
 */

public interface ChatObservable {
    void addCommand(String trigger, ChatCommand command);

    void addReoccurResponse(Long amount, String string);

    void notifyCommand(String command, MessageEvent event);
}
