package org.dinhware.bot.adapter;


import org.dinhware.bot.objects.EventType;

/**
 * Created by: Niklas
 * Date: 01.01.2018
 * Alias: Dinh
 * Time: 20:00
 */

public interface ListenerObservable {

    void addListener(Listener... listeners);

    void notifyListener(EventType type, String[] arguments, String line);

}

