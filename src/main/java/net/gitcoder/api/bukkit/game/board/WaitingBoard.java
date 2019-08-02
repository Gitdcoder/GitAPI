package net.gitcoder.api.bukkit.game.board;

import net.gitcoder.api.bukkit.module.board.Board;
import net.gitcoder.api.bukkit.module.board.CommonBoard;
import org.bukkit.entity.Player;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public final class WaitingBoard {

    private static final Board COMMON_BOARD = CommonBoard
            .newBuilder()
            .setDisplayName("")
            .create();

    /**
     * Конструктор, устанавливающий игроку борд.
     * @param player - игрок.
     */
    public WaitingBoard(Player player) {
        COMMON_BOARD.showCommonBoard(player);
    }
}
