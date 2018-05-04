package org.dinhware.bot.event;

import org.dinhware.bot.adapter.Event;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by: Niklas
 * Date: 24.01.2018
 * Alias: Dinh
 * Time: 13:18
 */

public class GlobalUserstateEvent extends Event {

    private String colorCode, name, type;
    private List<Integer> emoteSet;
    private Color color;

    private boolean isTurbo;
    private long id;


    public GlobalUserstateEvent(Map<String, String> tags, String[] arguments, String line) {
        super(tags, arguments, line);
        this.colorCode = tags.get("color");
        this.name = tags.get("display-name");
        this.type = tags.get("user-type");
        this.emoteSet = Stream.of(tags.get("emote-sets").split(",")).map(Integer::parseInt).collect(Collectors.toList());
        this.color = hex2Rgb(colorCode);
        this.isTurbo = Integer.parseInt(tags.get("turbo")) == 1;
        this.id = Long.parseLong(tags.get("user-id"));
    }

    private Color hex2Rgb(String s) {
        return new Color(Integer.valueOf(s.substring(1, 3), 16), Integer.valueOf(s.substring(3, 5), 16), Integer.valueOf(s.substring(5, 7), 16));
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Integer> getEmoteSet() {
        return emoteSet;
    }

    public Color getColor() {
        return color;
    }

    public boolean isTurbo() {
        return isTurbo;
    }

    public long getId() {
        return id;
    }
}
