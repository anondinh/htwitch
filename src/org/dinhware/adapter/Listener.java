package org.dinhware.adapter;

import java.util.Map;

/**
 * Created by: Niklas
 * Date: 01.01.2018
 * Alias: Dinh
 * Time: 21:21
 */

public interface Listener {
    void dispatch(Map<String, String> tags, String[] arguments, String line);
}
