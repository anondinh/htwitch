package org.dinhware.adapter;

import org.dinhware.objects.EventType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 11:25
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface ListenerType {
    EventType type() default EventType.INVALID;
}
