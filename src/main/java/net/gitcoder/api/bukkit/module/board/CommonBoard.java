package net.gitcoder.api.bukkit.module.board;

import net.gitcoder.api.bukkit.module.board.updater.BoardUpdater;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class CommonBoard {

    private Board board = null;
    private String displayName;

    private final Map<Integer, String> cache = new HashMap<>();

    /**
     * Получение новый обьект.
     * @return - новый CommonBoard.
     */
    public static CommonBoard newBuilder() {
        return new CommonBoard();
    }

    /**
     * Установка дисплея в борде.
     * @param displayName - дисплей.
     * @return - this.
     */
    public CommonBoard setDisplayName(String displayName) {
        this.displayName = displayName;

        return this;
    }

    /**
     * Установка линии для борда.
     * @param index - индекс.
     * @param line - текст.
     * @return - this.
     */
    public CommonBoard addLine(int index, String line) {
        cache.put(index, line);

        return this;
    }

    /**
     * Добавление апдейтера в борд.
     * @param time - время.
     * @param boardUpdater - новый апдейтер.
     * @return - this.
     */
    public CommonBoard addUpdater(long time, BoardUpdater boardUpdater) {
        this.board = new Board();
        this.board.addUpdater(time, boardUpdater);

        return this;
    }

    /**
     * Конечная подготовка борда к работе.
     * @return - новый борд.
     */
    public Board create() {
        cache.forEach((index, line) -> this.board.addLine(index, line));
        this.board.setDisplayName(this.displayName);
        this.board.startCommonUpdater();

        return this.board;
    }
}
