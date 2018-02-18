package org.dinhware.objects;

import org.dinhware.HTwitchBot;

import java.util.*;

/**
 * Created by: Niklas
 * Date: 19.01.2018
 * Alias: Dinh
 * Time: 21:31
 */

public class Channel {

    private String channel;
    private HTwitchBot bot;
    private Map<String, User> users;

    private static Map<String, Channel> channels = new HashMap<>();

    private Channel(String channel, HTwitchBot bot) {
        this.users = new HashMap<>();
        this.channel = channel;
        this.bot = bot;
    }

    public static Channel getChannel(String channel, HTwitchBot bot) {
        bot.sendRAW("JOIN " + channel);
        Channel room = new Channel(channel, bot);
        channels.put(channel, room);
        return room;
    }

    public static Channel getChannel(String argument) {
        return channels.get(argument);
    }


    public final void ban(String user) {
        bot.send(this, ".ban " + user);
    }

    public final void unban(String user) {
        bot.send(this, ".unban " + user);
    }

    public final void timeout(String user, int duration) {
        bot.send(this, ".timeout " + user + " " + duration);
    }

    public HTwitchBot getBot() {
        return bot;
    }

    public void addUser(User user) {
        users.put(user.toString(), user);
    }

    public void removeUser(User user) {
        users.remove(user.toString());
    }

    public Optional<User> getUser(String user) {
        return Optional.ofNullable(users.get(user));
    }

    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @Override
    public String toString() {
        return channel;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Channel)) {
            return false;
        }
        Channel channel = (Channel) o;
        return this.channel.equals(channel.channel);
    }

}
