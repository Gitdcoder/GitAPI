package net.gitcoder.api.bukkit;

import net.gitcoder.api.bukkit.game.setting.GameSettings;
import net.gitcoder.api.bukkit.gamer.GamerStorage;
import net.gitcoder.api.bukkit.gamer.human.HumanGamer;
import net.gitcoder.api.bukkit.handler.GroupSQLHandler;
import net.gitcoder.api.bukkit.module.store.handler.StoreSQLHandler;
import net.gitcoder.api.bukkit.module.store.registry.RegistryStore;
import net.gitcoder.api.bukkit.module.tag.api.TagManager;
import net.gitcoder.api.bukkit.server.GitServer;
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

    public final GamerStorage gamerStorage = new GamerStorage();

    public final GroupSQLHandler groupSQLHandler = new GroupSQLHandler();

    public final TagManager tagManager = new TagManager();

    public final GameSettings gameSettings = new GameSettings();

    public final StoreSQLHandler storeSQLHandler = new StoreSQLHandler();

    public final RegistryStore registryStore = new RegistryStore();

    public final PacketProtocol packetProtocol = new PacketProtocol();

    public final Map<String, GitServer> proxyServersMap = new HashMap<>();

    public int GLOBAL_ONLINE = 0;

    /**
     * Получение геймера из хранилищаю
     * @param name - имя игрока.
     * @return - Gamer.
     */
    public HumanGamer getGamer(String name) {
        return gamerStorage.getGamer(name);

    }

    /**
     * Получение геймера из хранилищаю
     * @param player - игрок.
     * @return - Gamer.
     */
    public HumanGamer getGamer(Player player) {
        return gamerStorage.getGamer(player.getName());
    }
}