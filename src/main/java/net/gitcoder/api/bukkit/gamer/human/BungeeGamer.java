package net.gitcoder.api.bukkit.gamer.human;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface BungeeGamer extends SpectatorHumanGamer{

    /**
     * Перекидывание игрока на другой сервер.
     */
    void redirect(String server);
}
