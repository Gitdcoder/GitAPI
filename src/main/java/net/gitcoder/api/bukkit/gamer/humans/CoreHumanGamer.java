package net.gitcoder.api.bukkit.gamer.humans;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface CoreHumanGamer extends HumanGamer {

    /**
     * Перекидывание игрока на другой сервер.
     */
    void redirect(String server);
}
