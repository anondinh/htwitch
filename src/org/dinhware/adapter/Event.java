package org.dinhware.adapter;

import org.dinhware.objects.Channel;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 13.01.2018
 * Alias: Dinh
 * Time: 17:20
 */

public abstract class Event {

    private Map<String, String> tags;
    private String[] arguments;
    private Channel channel;
    private String line;

    public Event(Map<String, String> tags, String[] arguments, String line) {
        this.arguments = arguments;
        this.setChannel(arguments[line.startsWith("@") ? 3 : 2]);
        this.line = line;
        this.tags = tags;
    }

    private void setChannel(String name) {
        channel = Channel.getChannel(name);
    }

    public final void respond(String response) {
        channel.getBot().send(channel, response);
    }

    public Channel getChannel() {
        return channel;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public String[] getArguments() {
        return arguments;
    }

    public String getLine() {
        return line;
    }
}
