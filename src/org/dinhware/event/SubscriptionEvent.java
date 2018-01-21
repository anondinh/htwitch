package org.dinhware.event;

import org.dinhware.adapter.Event;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 13:36
 */

public class SubscriptionEvent extends Event {

    private String display, login, message;
    private SubscriptionType subscriptionType;

    private int months;

    public enum SubscriptionType {
        UNKNOWN, PRIME, FIRST, SECOND, THIRD
    }

    public SubscriptionEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.display = tags.get("display-name");
        this.login = tags.get("login");
        this.message = arguments[4].substring(1);
        this.months = Integer.parseInt(tags.get("msg-param-months"));
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

    public String getDisplay() {
        return display;
    }

    public String getLogin() {
        return login;
    }

    public String getMessage() {
        return message;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public int getMonths() {
        return months;
    }
}
