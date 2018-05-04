package org.dinhware.bot.objects;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 15:05
 */

public class User {

    private String name;
    private Channel channel;

    private long id;

    public User(Channel channel, String name, long id) {
        this.channel = channel;
        this.name = name;
        this.id = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public final void ban() {
        channel.getBot().send(channel, ".ban " + name);
    }

    public final void unban() {
        channel.getBot().send(channel, ".unban " + name);
    }

    public final void timeout(int duration) {
        channel.getBot().send(channel, ".timeout " + name + " " + duration);
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User[" + name + ";" + id + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.name.equals(user.name) && this.id == user.id && this.channel == user.channel;
    }
}
