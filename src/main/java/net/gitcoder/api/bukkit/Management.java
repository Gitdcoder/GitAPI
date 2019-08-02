package net.gitcoder.api.bukkit;

import net.gitcoder.api.bukkit.game.perk.PerkManager;
import net.gitcoder.api.bukkit.game.setting.GameSettings;
import net.gitcoder.api.bukkit.gamer.GamerStorage;
import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import net.gitcoder.api.bukkit.gamer.perk.SQLPerkHandler;
import net.gitcoder.api.bukkit.module.tag.api.TagManager;
import net.gitcoder.api.bukkit.server.GitServer;
import net.gitcoder.api.java.mysql.handler.SQLGroupHandler;
import org.bukkit.entity.Player;
import ru.stonlex.api.packet.protocol.bukkit.PacketProtocol;

import java.util.HashMap;
import java.util.Map;

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

    public final SQLGroupHandler SQL_GROUP_HANDLER = new SQLGroupHandler(GitAPI.getInstance());

    public final TagManager TAG_MANAGER = new TagManager();

    public final GameSettings GAME_SETTINGS = new GameSettings();

    public final SQLPerkHandler SQL_PERK_HANDLER = new SQLPerkHandler();

    public final PerkManager PERK_MANAGER = new PerkManager();

    public final PacketProtocol PACKET_PROTOCOL = new PacketProtocol();

    public final Map<String, GitServer> PROXY_SERVERS_MAP = new HashMap<>();

    public int GLOBAL_ONLINE = 0;

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