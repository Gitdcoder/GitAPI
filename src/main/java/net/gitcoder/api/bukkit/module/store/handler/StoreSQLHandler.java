package net.gitcoder.api.bukkit.module.store.handler;

import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.Management;
import net.gitcoder.api.java.mysql.MySQL;

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
public class StoreSQLHandler {

    private final MySQL database = GitAPI.getInstance().getDatabase();
    private final Management MANAGEMENT = GitAPI.MANAGEMENT;


    public void buyItem(String playerName,
                        String itemName) {

        final String gameName = MANAGEMENT.gameSettings.GAME_TYPE.getName();

        String EXECUTE_SET_PERK = "INSERT INTO `Store`.`%s`(`Name`, `Item`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `Perk` = ?";

        database.execute(String.format(EXECUTE_SET_PERK, gameName),
                playerName.toLowerCase(),
                itemName, itemName);
    }

    public String getPlayerItemByName(String name, String item) {
        final String EXECUTE_QUERY_GET_PERK = "SELECT * FROM `Store`.`%s` WHERE `Name` = ?";

        final String gameName = MANAGEMENT.gameSettings.GAME_TYPE.getName();
        final String EXECUTE_QUERY = String.format(EXECUTE_QUERY_GET_PERK, gameName);

        return database.executeQuery(EXECUTE_QUERY, resultSet -> {

            if (resultSet.next()) {

                return resultSet.getString(item);
            }

            return null;

        }, name.toLowerCase());
    }


    public List<String> getPlayerItemsByName(String name, String item) {
        final String EXECUTE_QUERY_GET_PERKS = "SELECT * FROM `Store`.`%s` WHERE `Name` = ?";

        final String gameName = MANAGEMENT.gameSettings.GAME_TYPE.getName();
        final String EXECUTE_QUERY = String.format(EXECUTE_QUERY_GET_PERKS, gameName);

        List<String> items = new ArrayList<>();

        database.executeQuery(EXECUTE_QUERY, resultSet -> {

            while (resultSet.next()) {
                items.add(resultSet.getString(item));
            }

            return items;

        }, name.toLowerCase());

        return null;
    }

}
