package net.gitcoder.api.bukkit.gamer;

import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import org.bukkit.entity.Player;

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
public class GamerStorage {

    private final Map<String, Gamer> gamers = new HashMap<>();

    /**
     * Загрузка геймера в кеш.
     * @param player - игрок.
     */
    public void loadGamer(Player player) {
        final String name = player.getName().toLowerCase();
        final Gamer gamer = new GamerImpl(player.getName());

        gamers.put(name, gamer);
    }

    public void loadGamer(String player) {
        final Gamer gamer = new GamerImpl(player);

        gamers.put(player.toLowerCase(), gamer);
    }

    /**
     * Получение игрока из кеша.
     * @param name - имя игрока.
     * @return - геймера.
     */
    public Gamer getGamer(String name) {
        return gamers.get(name.toLowerCase());
    }
}
