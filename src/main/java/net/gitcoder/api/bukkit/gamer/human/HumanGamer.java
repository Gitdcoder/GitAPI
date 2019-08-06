package net.gitcoder.api.bukkit.gamer.human;

import org.bukkit.entity.Player;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface HumanGamer extends DefaultGamer{

    /**
     * Установка игрока игроку, ВТФ?!
     * @param player - игрок.
     */
    void setPlayer(Player player);

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

}
