package net.gitcoder.api.bukkit.game.perk;

import net.gitcoder.api.bukkit.GitAPI;
import org.bukkit.Bukkit;

import java.util.*;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class PerkManager {

    private final Map<String, GamePerk> gamePerks = new HashMap<>();
    private final GitAPI gitAPI = GitAPI.getPlugin(GitAPI.class);

    /**
     * Регистрация перков.
     * @param gamePerk - игровой перк.
     */
    public void registerPerk(Class<? extends GamePerk> gamePerk) {

        try {

            GamePerk perk = gamePerk.newInstance();
            Bukkit.getPluginManager().registerEvents(perk, gitAPI);

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создание перка.
     * @param gamePerk - игровой перк.
     */
    void createPerk(GamePerk gamePerk) {
        gamePerks.put(gamePerk.getPerkName().toLowerCase(), gamePerk);
    }

    /**
     * Получение всех перков по уровню.
     * @param level - уровень.
     * @return - коллекцию с группами.
     */
    public Collection<GamePerk> getPerksByLevel(int level) {
        List<GamePerk> list = new ArrayList<>();

        for (GamePerk gamePerk : gamePerks.values()) {
            if (gamePerk.getLevel() == level) {
                list.add(gamePerk);
            }
        }

        return list;

    }

    /**
     * Получение всех перков по цене.
     * @param cost - цена.
     * @return - коллекцию с группами.
     */
    public Collection<GamePerk> getPerkByCost(int cost) {
        List<GamePerk> list = new ArrayList<>();

        for (GamePerk gamePerk : gamePerks.values()) {
            if (gamePerk.getCost() == cost) {
                list.add(gamePerk);
            }
        }

        return list;

    }

    /**
     * Получение перка по имени.
     * @param name - имя перка.
     * @return - перк.
     */
    public GamePerk getPerkByName(String name) {
        return gamePerks.get(name.toLowerCase());
    }
}
