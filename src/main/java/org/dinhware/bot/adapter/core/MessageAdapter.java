package org.dinhware.bot.adapter.core;

import org.dinhware.bot.adapter.Listener;
import org.dinhware.bot.adapter.ListenerType;
import org.dinhware.bot.adapter.command.ChatObservable;
import org.dinhware.bot.adapter.command.CommandInterface;
import org.dinhware.bot.event.BitEvent;
import org.dinhware.bot.event.MessageEvent;
import org.dinhware.bot.objects.EventType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 11:40
 */

@ListenerType(type = EventType.PRIVMSG)
public abstract class MessageAdapter implements Listener, ChatObservable {

    protected final String COMMAND_PREFIX;

    public MessageAdapter(String COMMAND_PREFIX) {
        this.COMMAND_PREFIX = COMMAND_PREFIX;
    }

    private Map<String, CommandInterface> listeners = new HashMap<>();

    @Override
    public void dispatch(Map<String, String> tags, String[] arguments, String line) {
        if (tags.containsKey("bits")) {
            onBitDonation(new BitEvent(tags, arguments, line));
        } else {
            String message = arguments[4].substring(1);
            String[] args = message.split(" ");
            MessageEvent event = new MessageEvent(tags, arguments, line, args, message);
            if (args[0].startsWith(COMMAND_PREFIX) && listeners.containsKey(args[0].substring(1))) {
                CommandInterface commandInterface = listeners.get(args[0].substring(1));
                if (commandInterface.getLastUsage() + commandInterface.getCooldown() <= System.currentTimeMillis()) {
                    notifyCommand(args[0].substring(1), event);
                }
            } else {
                onMessage(event);
            }
        }
    }

    @Override
    public void addCommand(String trigger, CommandInterface command) {
        listeners.put(trigger, command);
    }

    @Override
    public void notifyCommand(String command, MessageEvent event) {
        listeners.get(command).dispatch(event);
    }

    protected abstract void onMessage(MessageEvent event);

    protected abstract void onBitDonation(BitEvent event);
}
