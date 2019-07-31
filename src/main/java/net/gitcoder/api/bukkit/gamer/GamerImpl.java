package net.gitcoder.api.bukkit.gamer;

import lombok.Getter;
import net.gitcoder.api.bukkit.Management;
import net.gitcoder.api.bukkit.gamer.group.Group;
import net.gitcoder.api.bukkit.gamer.group.permissible.GamerPermissible;
import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import net.gitcoder.api.bukkit.gamer.humans.SpectatorHumanGamer;
import net.gitcoder.api.java.mysql.handler.SQLGroupHandler;
import net.gitcoder.api.java.mysql.handler.SQLMoneyHandler;
import org.bukkit.entity.Player;

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
@Getter
public class GamerImpl implements Gamer, SpectatorHumanGamer {

    private GamerPermissible gamerPermissible;
    private Group group;
    private Player player;
    private final String name;

    private final Map<String, Object> cache = new HashMap<>();

    private boolean spectator = false;

    /**
     * Конструктор, для вызова нового юзера.
     * @param name - имя игрока.
     */
    GamerImpl(String name)  {
        this.name = name;

        this.group = getSQLGroupHandler().getPlayerGroup(name);
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void changeGroup(Group group) {
        this.group = group;

    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void setSpectator() {
        spectator = true;
    }

    @Override
    public boolean spectator() {
        return spectator;
    }

    @Override
    public void removeSpectator() {
        spectator = false;
    }

    @Override
    public void redirect(String server) {

    }

    @Override
    public void setGamerPermissible(Player player) {
        this.gamerPermissible = new GamerPermissible(player, this);
    }

    @Override
    public SQLMoneyHandler getSQLMoneyHandler() {
        return (SQLMoneyHandler) cache.computeIfAbsent("SQL-MONEY", field -> new SQLMoneyHandler());
    }

    @Override
    public SQLGroupHandler getSQLGroupHandler() {
        return Management.SQL_GROUP_HANDLER;
    }
}
