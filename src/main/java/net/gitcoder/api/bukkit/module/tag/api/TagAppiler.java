package net.gitcoder.api.bukkit.module.tag.api;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;

public class TagAppiler {

    private static TagManager manager;

    static void init(TagManager manager) {
        TagAppiler.manager = manager;
    }

    public static void applyTag(Player player, String prefix) {
        applyTag(player, prefix);
    }

    public static void applyTag(Player player, String prefix, String suffix) {
        applyTag(player, player.getName(), prefix, suffix);
    }

    public static void applyTag(Player player, String team, String prefix, String suffix) {
        manager.setTag(player, team, prefix, suffix);
    }

    public static void clearTags() {
        manager.clearTags();
    }

    public static void clearTag(Player player) {
        manager.clearTag(player);
    }

    public static boolean hasTag(Player player) {
        return manager.hasTag(player);
    }

    public static TagData getTagData(Player player) {
        return manager.getTagData(player);
    }

    public static Collection<TagData> getDatas() {
        return manager.getDatas();
    }

    public static Map<String, TagData> getDatasMap() {
        return manager.getDatasMap();
    }

    public static TagManager getTagManager() {
        return manager;
    }
}
