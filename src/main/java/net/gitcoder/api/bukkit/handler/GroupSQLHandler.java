package net.gitcoder.api.bukkit.handler;

import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.gamer.group.Group;
import net.gitcoder.api.bukkit.gamer.human.HumanGamer;
import net.gitcoder.api.java.mysql.MySQL;

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
public final class GroupSQLHandler {

    private final Map<String, Group> groups = new HashMap<>();

    private final Group DEFAULT = groups.get("Default");

    private MySQL database = GitAPI.getInstance().getDatabase();

    /**
     * Загрузка всех групп в мапу и дальнейшая работа с ними.
     */
    public GroupSQLHandler() {
        String EXECUTE_QUERY = "SELECT * FROM `Groups`.`Groups`";

        database.executeQuery(EXECUTE_QUERY, resultSet -> {
            while (resultSet.next()) {
                final String display = resultSet.getString("Group");
                final String prefix = resultSet.getString("Prefix");
                final String suffix = resultSet.getString("Suffix");

                final int level = resultSet.getInt("Level");
                final int id = resultSet.getInt("Id");
                final int price = resultSet.getInt("Price");

                final Group group = new Group(display, prefix, suffix, level, id, price);

                groups.put(display, group);
            }

            return null;
        });
    }

    /**
     * Изменение группы игроку.
     * @param gamer - геймер.
     * @param group - нужная группа.
     */
    public void changeGroup(HumanGamer gamer, Group group) {
        gamer.setGroup(group);

        String EXECUTE_UPDATE = "INSERT INTO `Groups`.`Players`(`Name`, `Group`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `Group` = ?";

        database.execute(EXECUTE_UPDATE, gamer.getName().toLowerCase(), group.getGroup(), group.getGroup());
    }

    /**
     * Получение группы по имени.
     * @param name - имя группы.
     * @return - группу.
     */
    public Group getGroupByName(String name) {
        return groups.getOrDefault(name, DEFAULT);
    }

    /**
     * Получение группы по уровню.
     * @param level - уровень.
     * @return - группу.
     */
    public Group getGroupByLevel(int level) {
        return groups.values().stream().filter(group -> group.getLevel() == level).findFirst().orElse(DEFAULT);
    }

    /**
     * Получение группы по ID;
     * @param id - id;
     * @return - группу.
     */
    public Group getGroupById(int id) {
        return groups.values().stream().filter(group -> group.getId() == id).findFirst().orElse(DEFAULT);
    }

    /**
     * Получение группы игрока.
      * @param name - ник игрока.
     * @return - группу.
     */
    public Group getPlayerGroup(String name) {
        String EXECUTE_QUERY_PLAYER_GROUP = "SELECT * FROM `Groups`.`Players` WHERE `Name` = ?";

        return database.executeQuery(EXECUTE_QUERY_PLAYER_GROUP, resultSet -> {

            if (resultSet.next()) {
                return groups.getOrDefault(resultSet.getString("Group"), DEFAULT);
            }

            return DEFAULT;

        }, name.toLowerCase());
    }
}
