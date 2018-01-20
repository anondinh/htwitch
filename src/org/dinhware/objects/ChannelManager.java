package org.dinhware.objects;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 15:20
 */

public class ChannelManager<K, V> {

    private K k;
    private V v;

    public ChannelManager(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }
}
