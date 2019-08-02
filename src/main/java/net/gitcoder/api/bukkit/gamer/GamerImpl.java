package net.gitcoder.api.bukkit.gamer;

import lombok.Getter;
import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.game.perk.GamePerk;
import net.gitcoder.api.bukkit.gamer.group.Group;
import net.gitcoder.api.bukkit.gamer.group.permissible.GamerPermissible;
import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import net.gitcoder.api.bukkit.gamer.humans.SpectatorHumanGamer;
import net.gitcoder.api.bukkit.gamer.perk.SQLPerkHandler;
import net.gitcoder.api.java.mysql.handler.SQLGroupHandler;
import net.gitcoder.api.java.mysql.handler.SQLMoneyHandler;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private final GitAPI gitAPI = GitAPI.getInstance();

    private GamerPermissible gamerPermissible;

    private Group group;
    private Player player;

    private final String name;

    private final Map<String, Object> property = new HashMap<>();

    private GamePerk gamePerk;

    private boolean spectator = false;

    private List<GamePerk> gamePerks = new ArrayList<>();

    /**
     * Конструктор, для вызова нового юзера.
     * @param name - имя игрока.
     */
    GamerImpl(String name)  {
        this.name = name;

        this.group = getSQLGroupHandler().getPlayerGroup(name);
    }

    @Override
    public void changeGroup(Group group) {
        this.group = group;
    }

    @Override
    public void removeSpectator() {
        spectator = false;
    }

    @Override
    public void redirect(String server) {

    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void selectPerk(GamePerk gamePerk) {
        this.gamePerk = gamePerk;

        final String perkName = gamePerk.getPerkName();

        getSQLPerkHandler().selectPerk(name.toLowerCase(), perkName);
    }

    @Override
    public void setPurchasePerks(List<GamePerk> gamePerks) {
        this.gamePerks = gamePerks;
    }

    @Override
    public void setPerk(GamePerk gamePerk) {
        this.gamePerk = gamePerk;
    }

    @Override
    public void setSpectator() {
        this.spectator = true;
    }

    @Override
    public void setGamerPermissible(Player player) {
        this.gamerPermissible = new GamerPermissible(player, this);
    }

    @Override
    public String getName() {
        return this.player.getName();
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Group getGroup() {
        return this.group;
    }

    @Override
    public boolean spectator() {
        return this.spectator;
    }

    @Override
    public boolean hasPerk(GamePerk gamePerk) {
        return gamePerks.contains(gamePerk);
    }

    @Override
    public boolean selectedPerk(GamePerk gamePerk) {
        return this.gamePerk.equals(gamePerk);
    }

    @Override
    public SQLMoneyHandler getSQLMoneyHandler() {
        return (SQLMoneyHandler) property.computeIfAbsent("SQL-MONEY", field -> new SQLMoneyHandler());
    }

    @Override
    public SQLGroupHandler getSQLGroupHandler() {
        return gitAPI.MANAGEMENT.SQL_GROUP_HANDLER;
    }

    @Override
    public SQLPerkHandler getSQLPerkHandler() {
        return gitAPI.MANAGEMENT.SQL_PERK_HANDLER;
    }
}
