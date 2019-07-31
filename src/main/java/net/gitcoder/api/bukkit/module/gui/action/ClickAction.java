package net.gitcoder.api.bukkit.module.gui.action;

import org.bukkit.entity.Player;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface ClickAction {

    /**
     * Действие, выполняющееся при клике игрока на предмет.
     * @param player - кликающий.
     */
    void onClick(Player player);
}
