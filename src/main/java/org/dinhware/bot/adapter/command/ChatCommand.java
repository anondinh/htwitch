package org.dinhware.bot.adapter.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dinhware.bot.event.MessageEvent;

/**
 * Created by: Niklas
 * Date: 04.05.2018
 * Alias: Dinh
 * Time: 21:33
 */

public abstract class ChatCommand implements CommandInterface {

    private static Logger LOGGER = LogManager.getLogger(ChatCommand.class);

    private long time;

    @Override
    public void dispatch(MessageEvent event) {
        LOGGER.info("{}: {}", time = System.currentTimeMillis(), event.getUser());
        execute(event);
    }

    @Override
    public long getLastUsage() {
        return time;
    }

    protected abstract void execute(MessageEvent event);
}
