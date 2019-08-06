package net.gitcoder.api.bukkit.gamer.human;

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
public interface DefaultGamer extends GameGamer {

    /**
     * Установка группы игроку.
     * @param group - группа.
     */
    void setGroup(Group group);

    /**
     * Изменение группы игроку.
     * @param group - группа.
     */
    void changeGroup(Group group);

    /**
     * Установка прав игроку.
     * @param player - игрок.
     */
    void setGamerPermissible(Player player);

    /**
     * Изменение баланса игроку.
     * @param money - баланс.
     */
    void changeMoney(int money);

    /**
     * Получение монет игрока.
     * @return - количество.
     */
    int getMoney();

    /**
     * Получение мунов.
     * @return - количество.
     */
    int getMoon();

    /**
     * Получение EXP.
     * @return - exp.
     */
    int getExp();

    /**
     * Получение уровня.
     * @return - уровень.
     */

    int getLevel();

    /**
     * Получение группы игрока.
     * @return - группа.
     */
    Group getGroup();

}
