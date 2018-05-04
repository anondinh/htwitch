package org.dinhware.bot.adapter.core;

import org.dinhware.bot.adapter.Listener;
import org.dinhware.bot.adapter.ListenerType;
import org.dinhware.bot.adapter.command.ChatCommand;
import org.dinhware.bot.adapter.command.ChatObservable;
import org.dinhware.bot.event.BitEvent;
import org.dinhware.bot.event.MessageEvent;
import org.dinhware.bot.objects.EventType;
import org.dinhware.bot.objects.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private long counter;

    public MessageAdapter(String COMMAND_PREFIX) {
        this.COMMAND_PREFIX = COMMAND_PREFIX;
    }

    private Map<String, ChatCommand> listeners = new HashMap<>();
    private List<Pair<Long, String>> reoccuring = new ArrayList<>();

    @Override
    public void dispatch(Map<String, String> tags, String[] arguments, String line) {
        if (tags.containsKey("bits")) {
            onBitDonation(new BitEvent(tags, arguments, line));
        } else {
            counter++;
            String message = arguments[4].substring(1);
            String[] args = message.split(" ");
            MessageEvent event = new MessageEvent(tags, arguments, line, args, message);
            reoccuring.stream().filter(pair -> counter % pair.getLeftElement() == 0).forEach(pair -> event.respond(pair.getRightElement()));
            if (args[0].startsWith(COMMAND_PREFIX) && listeners.containsKey(args[0].substring(1))) {
                notifyCommand(args[0].substring(1), event);
            } else {
                onMessage(event);
            }
        }
    }

    @Override
    public void addCommand(String trigger, ChatCommand command) {
        listeners.put(trigger, command);
    }

    @Override
    public void addReoccurResponse(Long amount, String string) {
        reoccuring.add(Pair.createPair(amount, string));
    }

    @Override
    public void notifyCommand(String command, MessageEvent event) {
        listeners.get(command).execute(event);
    }


    protected abstract void onMessage(MessageEvent event);

    protected abstract void onBitDonation(BitEvent event);
}
