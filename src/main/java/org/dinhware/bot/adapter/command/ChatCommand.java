package org.dinhware.bot.adapter.command;

import org.dinhware.bot.event.MessageEvent;

/**
 * Created by: Niklas
 * Date: 04.05.2018
 * Alias: Dinh
 * Time: 21:33
 */

public abstract class ChatCommand implements CommandInterface {

    private long time;

    @Override
    public void dispatch(MessageEvent event) {
        time = System.currentTimeMillis();
        execute(event);
    }

    @Override
    public long getLastUsage() {
        return time;
    }

    protected abstract void execute(MessageEvent event);
}
