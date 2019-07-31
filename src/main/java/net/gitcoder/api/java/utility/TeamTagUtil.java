package net.gitcoder.api.java.utility;

import org.bukkit.ChatColor;

public class TeamTagUtil {

    public static String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static String parseName(String input) {
        if (input.length() > 15) return input.substring(0, 15);
        return input;
    }

    public static String parse(String input) {
        if (input.length() > 16) return input.substring(0, 16);
        return input;
    }
}