package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 24.01.2018
 * Alias: Dinh
 * Time: 13:11
 */

public class NoticeEvent extends Event {

    private String notice;

    public NoticeEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.notice = tags.get("msg-id");
    }

    public String getNotice() {
        return notice;
    }
}
