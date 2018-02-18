package org.dinhware.objects;

/**
 * Created by: Niklas
 * Date: 15.01.2018
 * Alias: Dinh
 * Time: 18:13
 */

public class HConfigBuilder {

    private StringBuilder configuration = new StringBuilder();

    public HConfigBuilder addConfig(String config) {
        configuration.append(config).append("\r\n");
        return this;
    }

    @Override
    public String toString() {
        return configuration.toString();
    }
}
