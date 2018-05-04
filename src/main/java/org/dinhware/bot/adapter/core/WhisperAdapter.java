package org.dinhware.bot.adapter.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dinhware.bot.adapter.Listener;
import org.dinhware.bot.adapter.ListenerType;
import org.dinhware.bot.adapter.command.WhisperCommand;
import org.dinhware.bot.adapter.command.WhisperObservable;
import org.dinhware.bot.event.WhisperEvent;
import org.dinhware.bot.objects.EventType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Niklas
 * Date: 19.01.2018
 * Alias: Dinh
 * Time: 22:22
 */

@ListenerType(type = EventType.WHISPER)
public abstract class WhisperAdapter implements Listener, WhisperObservable {

    private static Logger LOGGER = LogManager.getLogger(WhisperAdapter.class);

    private final String COMMAND_PREFIX;

    public WhisperAdapter(String COMMAND_PREFIX) {
        this.COMMAND_PREFIX = COMMAND_PREFIX;
    }

    private Map<String, WhisperCommand> listeners = new HashMap<>();

    @Override
    public void dispatch(Map<String, String> tags, String[] arguments, String line) {
        String message = arguments[4].substring(1);
        String[] args = message.split(" ");
        WhisperEvent event = new WhisperEvent(tags, arguments, line, args, message);
        if (args[0].startsWith(COMMAND_PREFIX) && listeners.containsKey(args[0].substring(1))) {
            notifyCommand(args[0].substring(1), event);
        } else {
            LOGGER.info("{}: {}", event.getUser().getName(), event.getDisplayed());
            onWhisper(event);
        }

    }

    @Override
    public void addCommand(String trigger, WhisperCommand command) {
        listeners.put(trigger, command);
    }

    @Override
    public void notifyCommand(String command, WhisperEvent event) {
        listeners.get(command).execute(event);
    }

    protected abstract void onWhisper(WhisperEvent event);
}
