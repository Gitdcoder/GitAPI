package net.gitcoder.api.bukkit.module.board.updater;

import net.gitcoder.api.bukkit.module.board.Board;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface BoardUpdater {

    /**
     * Добавить апдейтер борды.
     * @param board - текущий борд.
     */
    void update(Board board);
}
