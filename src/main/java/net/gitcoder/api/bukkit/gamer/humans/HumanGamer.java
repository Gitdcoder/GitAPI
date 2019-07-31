package net.gitcoder.api.bukkit.gamer.humans;

import net.gitcoder.api.bukkit.gamer.group.Group;
import org.bukkit.entity.Player;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
interface HumanGamer extends GameGamer {

    /**
     * Установка игрока игроку, ВТФ?!
     * @param player - игрок.
     */
    void setPlayer(Player player);

    /**
     * Изменение группы игроку.
     * @param group - группа.
     */
    void changeGroup(Group group);

    /**
     * Получение имени игрока.
     * @return - имя.
     */
    String getName();

    /**
     * Получение игрока.
     * @return - игрок.
     */
    Player getPlayer();

    /**
     * Получение группы игрока.
     * @return - группа.
     */
    Group getGroup();
}
