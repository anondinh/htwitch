package org.dinhware.bot.adapter.command;

import org.dinhware.bot.event.MessageEvent;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 14:29
 */

public interface ChatObservable {
    void addCommand(String trigger, CommandInterface command);

    void notifyCommand(String command, MessageEvent event);
}
