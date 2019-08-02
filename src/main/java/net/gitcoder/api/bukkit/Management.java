package net.gitcoder.api.bukkit;

import net.gitcoder.api.bukkit.game.perk.PerkManager;
import net.gitcoder.api.bukkit.game.setting.GameSetting;
import net.gitcoder.api.bukkit.gamer.GamerStorage;
import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import net.gitcoder.api.bukkit.gamer.perk.SQLPerkHandler;
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

    public final GamerStorage GAMER_STORAGE = new GamerStorage();

    public final SQLGroupHandler SQL_GROUP_HANDLER = new SQLGroupHandler(GitAPI.getPlugin(GitAPI.class));

    public final TagManager TAG_MANAGER = new TagManager();

    public final GameSetting GAME_SETTING = new GameSetting();

    public final SQLPerkHandler SQL_PERK_HANDLER = new SQLPerkHandler();

    public final PerkManager PERK_MANAGER = new PerkManager();

    /**
     * Получение геймера из хранилищаю
     * @param name - имя игрока.
     * @return - Gamer.
     */
    public Gamer getGamer(String name) {
        return GAMER_STORAGE.getGamer(name);
    }

    /**
     * Получение геймера из хранилищаю
     * @param player - игрок.
     * @return - Gamer.
     */
    public Gamer getGamer(Player player) {
        return GAMER_STORAGE.getGamer(player.getName());
    }
}