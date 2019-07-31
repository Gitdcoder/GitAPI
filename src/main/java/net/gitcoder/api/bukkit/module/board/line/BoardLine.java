package net.gitcoder.api.bukkit.module.board.line;

import com.google.common.base.Splitter;
import net.gitcoder.api.bukkit.module.board.Board;
import net.gitcoder.api.java.utility.ColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Team;

import java.util.Iterator;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class BoardLine {

    private final Board board;
    private final int index;
    private String text;
    private Team team;

    /**
     * Конструктор, создаёт новую линию для борда.
     * @param board - борд.
     * @param index - индекс.
     * @param text - текст.
     */
    public BoardLine(Board board,
                     int index,
                     String text) {

        this.board = board;
        this.index = index;
        this.text = ColorUtil.replaceColors(text.isEmpty() ? ChatColor.values()[index].toString() : text);
        this.team = board.getScoreboard().registerNewTeam(index + "");
        prepareText();
    }

    /**
     * Установка текста для линии и его форматирование.
     * @param text - текст.
     */
    public void setText(String text) {
        this.text = ColorUtil.replaceColors(text.isEmpty() ? ChatColor.values()[index].toString() : text);
        prepareText();
    }

    /**
     * Подготовка текста.
     */
    private void prepareText() {
        if (isModifiable()) {
            return;
        }
        Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
        String prefix = iterator.next();
        String result = ChatColor.values()[index].toString();

        team.setPrefix(prefix);

        team.removeEntry(result);

        team.addEntry(result);

        if (text.length() > 16) {
            String prefixColor = ChatColor.getLastColors(prefix);
            String suffix = iterator.next();

            if (prefix.endsWith(String.valueOf(ChatColor.COLOR_CHAR))) {
                prefix = prefix.substring(0, prefix.length() - 1);
                team.setPrefix(prefix);
                prefixColor = ChatColor.getByChar(suffix.charAt(0)).toString();
                suffix = suffix.substring(1);
            }

            if (prefixColor == null)
                prefixColor = "";

            if (suffix.length() > 16) {
                suffix = suffix.substring(0, (13 - prefixColor.length()));
            }

            team.setSuffix((prefixColor.equals("") ? ChatColor.RESET : prefixColor) + suffix);

        } else team.setSuffix(ChatColor.RESET.toString());

        board.getObjective().getScore(result).setScore(index);
    }

    /**
     * ??
     * @return true or false
     */
    private boolean isModifiable() {
        return board.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null
                || board.getScoreboard().getTeam(index + "") == null;
    }

    /**
     * Скрыть линию в борде.
     */
    public void hide() {
        this.board.getScoreboard().resetScores(ChatColor.values()[index].toString());
        this.team.unregister();
    }

    /**
     * Показать линию в борде.
     */
    public void show() {
        this.team = board.getScoreboard().registerNewTeam(index + "");
        prepareText();
    }

}
