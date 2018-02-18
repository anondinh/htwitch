package org.dinhware.adapter;


import org.dinhware.objects.EventType;

/**
 * Created by: Niklas
 * Date: 01.01.2018
 * Alias: Dinh
 * Time: 20:00
 */

public interface ListenerObservable {

    void addListener(Listener... listeners);

    void notifyListener(EventType type, String[] arguments, String line, boolean printError);

}

