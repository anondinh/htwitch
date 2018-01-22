package org.dinhware;

import org.dinhware.adapter.Listener;
import org.dinhware.adapter.ListenerObservable;
import org.dinhware.adapter.ListenerType;
import org.dinhware.adapter.core.AdapterExecutor;
import org.dinhware.objects.Channel;
import org.dinhware.objects.EventType;

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

    private Map<EventType, Listener> listeners = new HashMap<>();

    private BufferedWriter writer;
    private BufferedReader reader;

    private boolean verbose, printError;

    public HTwitchBot(String nick, String oAuth) {
        super(nick, oAuth);
        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
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
    public void notifyListener(EventType type, String[] arguments, String line, boolean printError) {
        AdapterExecutor.submit(listeners.get(type), arguments, line, printError);
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
    public void sendRAW(Object o) {
        try {
            writer.write(o + "\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    @Override
    public void setPrintError(boolean printError) {
        this.printError = printError;
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
                e.printStackTrace();
            }
        }
    }

    private void handle(String line) {
        if (verbose) {
            System.out.println(line);
        }

        if (line.startsWith("PING")) {
            sendRAW("PONG " + line.substring(5));
        } else {
            String[] arguments = line.split(" ", 5);
            EventType type = EventType.get(arguments[line.startsWith("@") ? 2 : 1]);
            if (listeners.containsKey(type)) {
                notifyListener(type, arguments, line, printError);
            }
        }
    }

}
