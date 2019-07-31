package net.gitcoder.api.bukkit.gamer.humans;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface GameGamer {

    /**
     * Установка спектатора игроку.
     */
    void setSpectator();

    /**
     * Проверка спектатор игрок или нет.
     * @return - да или нет.
     */
    boolean spectator();
}
