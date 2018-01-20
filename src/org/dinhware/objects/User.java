package org.dinhware.objects;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 15:05
 */

public class User {

    private String name;
    private Channel channel;

    public User(Channel channel, String user) {
        this.channel = channel;
        this.name = user;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.name.equals(user.name) && this.channel == user.channel;
    }
}
