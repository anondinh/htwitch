package org.dinhware.bot.adapter.command;

import org.dinhware.bot.event.WhisperEvent;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 14:29
 */

public interface WhisperObservable {
    void addCommand(String trigger, WhisperCommand command);

    void notifyCommand(String command, WhisperEvent event);
}
