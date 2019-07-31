package net.gitcoder.api.bukkit.gamer.humans;

import net.gitcoder.api.bukkit.gamer.group.permissible.GamerPermissible;
import net.gitcoder.api.java.mysql.handler.SQLGroupHandler;
import net.gitcoder.api.java.mysql.handler.SQLMoneyHandler;
import org.bukkit.entity.Player;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface Gamer extends CoreHumanGamer {

    /**
     * Установка прав игроку.
     * @param player - игрок.
     */
    void setGamerPermissible(Player player);

    /**
     * Получение прав геймера.
     * @return - права.
     */
    GamerPermissible getGamerPermissible();

    /**
     * Получение обьекта, в котором хранится вся информация о балансе игрока.
     * @return - обьект.
     */
    SQLMoneyHandler getSQLMoneyHandler();

    /**
     * Получение обьекта, в котором хранится вся информация о группе игрока.
     * @return - обьект.
     */
    SQLGroupHandler getSQLGroupHandler();

}
