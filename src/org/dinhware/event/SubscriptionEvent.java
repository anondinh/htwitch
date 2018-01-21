package org.dinhware.event;

import org.dinhware.adapter.Event;
import org.dinhware.objects.User;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 13:36
 */

public class SubscriptionEvent extends Event {

    private String message;
    private User user, recipientUser;
    private SubscriptionType subscriptionType;

    private int months;
    private boolean gift;

    public enum SubscriptionType {
        UNKNOWN, PRIME, FIRST, SECOND, THIRD
    }

    public SubscriptionEvent(Map<String, String> tags, String[] arguments, String line, boolean gift) {
        super(tags, arguments, line);
        this.user = new User(getChannel(), tags.get("display-name") == null ? tags.get("login") : tags.get("display-name"), Long.parseLong(tags.get("user-id")));
        String display = tags.get("msg-param-recipient-display-name"), name = tags.get("msg-param-recipient-user-name"), id = tags.get("msg-param-recipient-id");
        this.recipientUser = new User(getChannel(), display == null ? name : display, id == null ? -1 : Long.parseLong(id));
        this.months = Integer.parseInt(tags.get("msg-param-months"));
        this.message = arguments[4].substring(1);
        this.gift = gift;

        switch (tags.get("msg-param-sub-plan")) {
            case "Prime":
                subscriptionType = SubscriptionType.PRIME;
                break;
            case "1000":
                subscriptionType = SubscriptionType.FIRST;
                break;
            case "2000":
                subscriptionType = SubscriptionType.SECOND;
                break;
            case "3000":
                subscriptionType = SubscriptionType.THIRD;
                break;
            default:
                subscriptionType = SubscriptionType.UNKNOWN;
                break;
        }
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public User getRecipientUser() {
        return recipientUser;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public int getMonths() {
        return months;
    }

    public boolean isGift() {
        return gift;
    }
}
