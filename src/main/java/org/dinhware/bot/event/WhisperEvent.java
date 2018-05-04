package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;
import org.dinhware.bot.objects.Channel;
import org.dinhware.bot.objects.User;

import java.util.Map;
import java.util.Optional;

/**
 * Created by: Niklas
 * Date: 24.01.2018
 * Alias: Dinh
 * Time: 12:44
 */

public class WhisperEvent extends Event {
    private String displayed;
    private String[] message;
    private User user;

    public WhisperEvent(Map<String, String> tags, String[] arguments, String line, String[] message, String displayed) {
        super(tags, arguments, line);
        this.displayed = displayed;
        this.message = message;
        this.user = new User(getChannel(), tags.get("display-name"), Long.parseLong(tags.get("user-id")));
    }

    public String getDisplayed() {
        return displayed;
    }

    public String[] getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    @Override
    public final void respond(String response) {
        Channel channel = Optional.ofNullable(Channel.getChannel("#" + getArguments()[3])).orElseThrow(() -> new RuntimeException("Whispers not enabled"));
        channel.getBot().sendWhisper(user.getName(), response);
    }
}
