package org.dinhware.bot.objects;

/**
 * Created by: Niklas
 * Date: 24.01.2018
 * Alias: Dinh
 * Time: 14:57
 */

public class Pair<K, V> {

    private final K leftElement;
    private final V rightElement;

    public static <K, V> Pair<K, V> createPair(K leftElement, V rightElement) {
        return new Pair<>(leftElement, rightElement);
    }

    private Pair(K leftElement, V rightElement) {
        this.leftElement = leftElement;
        this.rightElement = rightElement;
    }

    public K getLeftElement() {
        return leftElement;
    }

    public V getRightElement() {
        return rightElement;
    }
}
