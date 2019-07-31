package net.gitcoder.api.bukkit.game.announcer;

import org.bukkit.Bukkit;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class GameAnnouncer {

    /**
     * Оповещает всех игроков.
     * @param message - текст.
     * @param prefix - нужен ли префикс.
     */
    public void broadcast(String message, boolean prefix) {

        if (prefix) Bukkit.broadcastMessage("" + message);
        else Bukkit.broadcastMessage(message);
    }

    /**
     * Оповещает всех спектаторов.
     * @param message - сообщение.
     */
    public void broadcastToSpectator(String message) {

    }
}
