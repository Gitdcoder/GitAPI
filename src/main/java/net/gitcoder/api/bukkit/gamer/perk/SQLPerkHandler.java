package net.gitcoder.api.bukkit.gamer.perk;

import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.Management;
import net.gitcoder.api.bukkit.game.perk.GamePerk;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class SQLPerkHandler {

    private final GitAPI gitAPI = GitAPI.getPlugin(GitAPI.class);
    private final Management MANAGEMENT = gitAPI.MANAGEMENT;

    /**
     * Покупка перка или перезапись перка.
     * @param name - имя игрока.
     * @param perkName - имя перка.
     */
    public void selectPerk(String name,
                        String perkName) {

        final String gameName = MANAGEMENT.GAME_SETTING.GAME_TYPE.getName();

        String EXECUTE_SET_PERK = "INSERT INTO `GamePerks`.`%s`(`Name`, `Perk`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `Perk` = ?";
        gitAPI.getDatabase().execute(String.format(EXECUTE_SET_PERK, gameName),
                name.toLowerCase(),
                perkName, perkName);
    }

    /**
     * Получение выбранного перка по имени игрока.
     * @param name - имя игрока.
     * @return - перк.
     */
    public GamePerk getPlayerPerkByName(String name) {
        final String EXECUTE_QUERY_GET_PERK = "SELECT * FROM `GamePerks`.`%s` WHERE `Name` = ?";

        final String gameName = MANAGEMENT.GAME_SETTING.GAME_TYPE.getName();
        final String EXECUTE_QUERY = String.format(EXECUTE_QUERY_GET_PERK, gameName);

        return gitAPI.getDatabase().executeQuery(EXECUTE_QUERY, resultSet -> {

            if (resultSet.next()) {

                final String perkName = resultSet.getString("Perk");

                return MANAGEMENT.PERK_MANAGER.getPerkByName(perkName);
            }

            return null;

        }, name.toLowerCase());
    }

    /**
     * Получение купленных перков по имени игрока.
     * @param name - имя игрока.
     * @return - коллекцию с перками.
     */
    public List<GamePerk> getPlayerPerksByName(String name) {
        final String EXECUTE_QUERY_GET_PERKS = "SELECT * FROM `PerkShop`.`%s` WHERE `Name` = ?";

        final String gameName = MANAGEMENT.GAME_SETTING.GAME_TYPE.getName();
        final String EXECUTE_QUERY = String.format(EXECUTE_QUERY_GET_PERKS, gameName);

        List<GamePerk> perks = new ArrayList<>();

        gitAPI.getDatabase().executeQuery(EXECUTE_QUERY, resultSet -> {

            while (resultSet.next()) {
                GamePerk gamePerk = MANAGEMENT.PERK_MANAGER.getPerkByName(resultSet.getString("Perk"));

                perks.add(gamePerk);
            }

            return perks;

        }, name.toLowerCase());

        return null;
    }
}
