package net.gitcoder.api.bukkit.module.board;

import net.gitcoder.api.bukkit.module.board.line.BoardLine;
import net.gitcoder.api.bukkit.module.board.updater.BoardUpdater;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
@SuppressWarnings("ALL")
public class Board {

    private Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    private Objective objective = scoreboard.registerNewObjective("board", "dummy");

    private final Updater updater = new Updater();

    private final Map<Integer, BoardLine> LINES = new HashMap<>();
    private final Map<Long, BoardUpdater> UPDATERS = new HashMap<>();

    /**
     * Конструктор, который выставляет дисплей слот - DisplaySlot.SIDEBAR
     */
    Board() {
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    /**
     * Показать борд игроку.
     * @param player - игрок.
     */
    public void show(Player player) {
        player.setScoreboard(this.scoreboard);

        if (!UPDATERS.isEmpty()) updater.start();
    }

    /**
     * Показать один борд всем.
     * @param player
     */
    public void showCommonBoard(Player player) {
        player.setScoreboard(this.scoreboard);

    }

    /**
     * Запустить обновление коммон борда.
     */
    public void startCommonUpdater() {
        updater.start();
    }

    /**
     * Установить дисплей имя борду.
     * @param name - название.
     */
    public void setDisplayName(String name) {
        this.objective.setDisplayName(name);
    }

    /**
     * Добавить линию в борде
     * @param index - индекс.
     * @param text - текст.
     */
    public void addLine(int index, String text) {
        BoardLine boardLine = LINES.get(index);

        if (boardLine != null) {
            boardLine.setText(text);
        } else LINES.put(index, new BoardLine(this, index, text));
    }

    /**
     * Добавить линию в текст без индекс.
     * @param text - текст.
     */
    public void addLine(String text) {

    }

    /**
     * Удалить линию в борде.
     * @param index - индекс.
     */
    public void removeLine(int index) {
        LINES.remove(index).hide();
    }

    /**
     * Добавить апдейтер в борд.
     * @param time - время.
     * @param boardUpdater - апдейтер.
     */
    public void addUpdater(long time, BoardUpdater boardUpdater) {
        UPDATERS.put(time, boardUpdater);
    }

    /**
     * Убрать апдейтер в борде.
     * @param runnable - апдейтер.
     */
    public void removeUpdater(Runnable runnable) {
        UPDATERS.clear();
        updater.stop();
    }

    /**
     * Удалить борд у игрока.
     * @param player - игрок.
     */
    public void remove(Player player) {
        player.setScoreboard(null);
    }


    /**
     * Удалить борд.
     */
    public void rejectBoard() {
        LINES.values().forEach(BoardLine::hide);
        LINES.clear();
    }

    /**
     * Получить борд.
     * @return - текущий борд.
     */
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    /**
     * Получить обьектив.
     * @return - новый обьектив.
     */
    public Objective getObjective() {
        return objective;
    }

    public class Updater extends Thread {

        private final AtomicLong time = new AtomicLong();

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException ignored) {
                }

                if (time.incrementAndGet() == Long.MAX_VALUE) time.set(0);
                UPDATERS.entrySet()
                        .stream()
                        .filter((entry) -> time.get() % entry.getKey() == 0)
                        .forEach(entry -> {
                            if (entry.getValue() != null)
                                entry.getValue().update(Board.this);
                        });
            }
        }
    }
}
