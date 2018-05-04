package org.dinhware.bot;

import org.dinhware.bot.objects.Capability;
import org.dinhware.bot.objects.Channel;

import java.io.*;
import java.net.Socket;

/**
 * Created by: Niklas
 * Date: 01.01.2018
 * Alias: Dinh
 * Time: 19:23
 */

abstract class HTwitch extends Thread {

    private String nick;
    private String oAuth;

    HTwitch(String nick, String oAuth) {
        this.oAuth = getOAuth(oAuth);
        this.nick = nick;
    }

    private String getChannel(String channel) {
        return channel.startsWith("#") ? channel.toLowerCase() : "#" + channel.toLowerCase();
    }

    private String getOAuth(String oAuth) {
        return oAuth.startsWith("oauth:") ? oAuth.toLowerCase() : "oauth:" + oAuth.toLowerCase();
    }

    abstract void setWriter(BufferedWriter writer);

    abstract void setReader(BufferedReader reader);

    public abstract void enableWhisper();

    public abstract void sendRAW(Object o);

    public abstract void send(Channel channel, Object o);

    public abstract void sendWhisper(String user, Object o);

    public void requestCapabilities(Capability... capabilities) {
        for (Capability capability : capabilities) {
            sendRAW("CAP REQ :twitch.tv/" + capability.name().toLowerCase());
        }
    }

    public Channel join(String channel) {
        return Channel.getChannel(getChannel(channel), (HTwitchBot) this);
    }

    void connect() throws IOException {
        Socket socket = new Socket("irc.chat.twitch.tv", 6667);

        setWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        setReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));

        sendRAW("PASS " + oAuth);
        sendRAW("NICK " + nick);
        sendRAW("USER " + nick);

        start();
    }

    String getNick() {
        return nick;
    }
}
