package org.dinhware.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dinhware.bot.adapter.Listener;
import org.dinhware.bot.adapter.ListenerObservable;
import org.dinhware.bot.adapter.ListenerType;
import org.dinhware.bot.adapter.core.AdapterExecutor;
import org.dinhware.bot.objects.Channel;
import org.dinhware.bot.objects.EventType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Niklas
 * Date: 13.01.2018
 * Alias: Dinh
 * Time: 17:14
 */

public class HTwitchBot extends HTwitch implements ListenerObservable {

    private static Logger LOGGER = LogManager.getLogger(HTwitchBot.class);

    private Map<EventType, Listener> listeners = new HashMap<>();

    private BufferedWriter writer;
    private BufferedReader reader;

    public HTwitchBot(String nick, String oAuth) {
        super(nick, oAuth);
        try {
            connect();
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
    }

    @Override
    public void addListener(Listener... listeners) {
        for (Listener listener : listeners) {
            Class parent = listener.getClass().getSuperclass();
            if (parent.isAnnotationPresent(ListenerType.class)) {
                Annotation annotation = parent.getAnnotation(ListenerType.class);
                ListenerType listenerType = (ListenerType) annotation;
                this.listeners.put(listenerType.type(), listener);
            }
        }
    }

    @Override
    public void notifyListener(EventType type, String[] arguments, String line) {
        AdapterExecutor.submit(listeners.get(type), arguments, line);
    }

    @Override
    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void enableWhisper() {
        sendRAW("JOIN #" + getNick());
    }

    @Override
    public void sendRAW(Object o) {
        try {
            writer.write(o + "\r\n");
            writer.flush();
            LOGGER.debug(o);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
    }

    @Override
    public void send(Channel channel, Object o) {
        sendRAW("PRIVMSG " + channel + " :" + o);
    }

    @Override
    public void sendWhisper(String user, Object message) {
        sendRAW("PRIVMSG #jtv :/w " + user + " " + message);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                handle(reader.readLine());
            } catch (IOException e) {
                LOGGER.error(e.toString());
            }
        }
    }

    private void handle(String line) {
        LOGGER.debug(line);
        if (line.startsWith("PING")) {
            sendRAW("PONG " + line.substring(5));
        } else {
            String[] arguments = line.split(" ", 5);
            EventType type = EventType.get(arguments[line.startsWith("@") ? 2 : 1]);
            if (listeners.containsKey(type)) {
                notifyListener(type, arguments, line);
            }
        }
    }

}
