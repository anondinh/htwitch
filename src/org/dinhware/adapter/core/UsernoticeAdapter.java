package org.dinhware.adapter.core;

import org.dinhware.adapter.Listener;
import org.dinhware.adapter.ListenerType;
import org.dinhware.event.RaidEvent;
import org.dinhware.event.RitualEvent;
import org.dinhware.event.SubscriptionEvent;
import org.dinhware.objects.EventType;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 19.01.2018
 * Alias: Dinh
 * Time: 22:22
 */

@ListenerType(type = EventType.USERNOTICE)
public abstract class UsernoticeAdapter implements Listener {

    @Override
    public void dispatch(Map<String, String> tags, String[] arguments, String line) {
        switch (tags.get("msg-id")) {
            case "sub":
                onSubscription(new SubscriptionEvent(tags, arguments, line));
                break;
            case "resub":
                onReSubscription(new SubscriptionEvent(tags, arguments, line));
                break;
            case "raid":
                onRaid(new RaidEvent(tags, arguments, line));
                break;
            case "ritual":
                onRitual(new RitualEvent(tags, arguments, line));
                break;
        }
    }

    protected abstract void onSubscription(SubscriptionEvent event);

    protected abstract void onReSubscription(SubscriptionEvent event);

    protected abstract void onRaid(RaidEvent event);

    protected abstract void onRitual(RitualEvent event);
}
