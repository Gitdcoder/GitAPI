package net.gitcoder.api.bukkit;

import net.gitcoder.api.bukkit.game.setting.GameSetting;
import net.gitcoder.api.bukkit.gamer.GamerStorage;
import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import net.gitcoder.api.bukkit.module.tag.api.TagManager;
import net.gitcoder.api.java.mysql.handler.SQLGroupHandler;
import org.bukkit.entity.Player;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public final class Management {

    public static final GamerStorage GAMER_STORAGE = new GamerStorage();
    public static final SQLGroupHandler SQL_GROUP_HANDLER = new SQLGroupHandler(GitAPI.getPlugin(GitAPI.class));
    public static final TagManager TAG_MANAGER = new TagManager();
    public static final GameSetting GAME_SETTING = new GameSetting();

    /**
     * Получение геймера из хранилищаю
     * @param name - имя игрока.
     * @return - Gamer.
     */
    public static Gamer getGamer(String name) {
        return GAMER_STORAGE.getGamer(name);
    }

    /**
     * Получение геймера из хранилищаю
     * @param player - игрок.
     * @return - Gamer.
     */
    public static Gamer getGamer(Player player) {
        return GAMER_STORAGE.getGamer(player.getName());
    }
}