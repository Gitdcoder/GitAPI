package net.gitcoder.api.bukkit.module.sidebar;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import net.gitcoder.api.bukkit.module.sidebar.objective.SidebarObjective;
import net.gitcoder.api.java.packet.AbstractPacket;
import net.gitcoder.api.java.packet.scoreboard.WrapperPlayServerScoreboardTeam;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class Sidebar {

    private final Set<Player> players = new HashSet<>();
    private final Map<Integer, SidebarLine> lines = new HashMap<>();

    @Getter
    private SidebarObjective objective;

    /**
     * Установка objective, если предыдущая objective здесь существует,
     * то она удаляется.
     *
     * @param objective - objective
     */
    public void setObjective(@NonNull final SidebarObjective objective) {
        this.objective = objective;
        for (Player player : unregisterForAll()) { //отправляем новую objective
            send(player);
        }
        recreate(); //пересоздаем линии.
    }

    /**
     * Установка линии на текущей objective.
     *
     * @param index - индекс
     * @param text  - текст линии
     */
    public void setLine(int index, String text) {
        SidebarLine line = getLine(index);
        if (line == null) lines.put(index, new SidebarLine(index, text, this));
        else line.setText(text);
    }

    /**
     * Получение определенной линии.
     *
     * @param index - индекс линии
     * @return линию, которая соответствует данному индексу, иначе - null.
     */
    public SidebarLine getLine(int index) {
        return lines.get(index);
    }

    /**
     * Возвращает неизменяемую коллекцию игроков, которые обрабатывают этот sidebar
     *
     * @return коллекцию игроков
     */
    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    /**
     * Возвращает неизменяемую коллекцию линий.
     *
     * @return коллекцию линий
     */
    public Map<Integer, SidebarLine> getLines() {
        return Collections.unmodifiableMap(lines);
    }

    /**
     * Вспомагательный метод, который отправляет пакет всем игрокам из Sidebar::getPlayers
     *
     * @param packet - пакет, который нужно отправить
     */
    public void broadcastPacket(@NonNull AbstractPacket packet) {
        players.forEach(packet::sendPacket);
    }

    /**
     * Удаление objective для определенного игрока
     *
     * @param player - игрок, которому нужно удалить sidebar.
     */
    public void unregister(@NonNull Player player) {
        Preconditions.checkState(players.contains(player), "Player %s is not receiving this sidebar.", player.getName());

        lines.values().forEach(line -> {
            line.getTeamPacket(WrapperPlayServerScoreboardTeam.Mode.TEAM_REMOVED).sendPacket(player);
            line.getScorePacket(EnumWrappers.ScoreboardAction.REMOVE).sendPacket(player);
        });
        objective.remove(player);
        players.remove(player);
    }

    /**
     * Удаление objective для всех игроков в Sidebar::getPlayers.
     *
     * @return клон коллекции игроков
     */
    public Collection<Player> unregisterForAll() {
        Set<Player> copied = new HashSet<>(players);
        players.forEach(this::unregister);

        return copied;
    }

    /**
     * Переустановка всех линий.
     */
    private void recreate() {
        lines.values().forEach(SidebarLine::show);
    }


    /**
     * Отправка sidebar игрокам
     *
     * @param players - массив игроков
     */
    public void send(@NonNull Player... players) {
        for (Player player : players) {
            Preconditions.checkArgument(!this.players.contains(player), "Player %s already receiving this sidebar.", player.getName());
            objective.create(player);
            lines.values().forEach(line -> {
                line.getTeamPacket(WrapperPlayServerScoreboardTeam.Mode.TEAM_CREATED).sendPacket(player);
                line.getScorePacket(EnumWrappers.ScoreboardAction.CHANGE).sendPacket(player);
            });
            objective.show(player);
        }
        Collections.addAll(this.players, players);
    }
}
