package net.gitcoder.api.bukkit.gamer;

import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.gamer.group.Group;
import net.gitcoder.api.bukkit.gamer.group.permissible.GamerPermissible;
import net.gitcoder.api.bukkit.gamer.human.HumanGamer;
import net.gitcoder.api.bukkit.gamer.level.ILeveling;
import net.gitcoder.api.bukkit.handler.GroupSQLHandler;
import net.gitcoder.api.bukkit.handler.MoneySQLHandler;
import net.gitcoder.api.bukkit.module.store.StoreItem;
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
public class GamerImpl implements HumanGamer {

    private Group group;

    private Player player;

    private final String name;

    private final Map<String, Object> property = new HashMap<>();

    private boolean spectator = false;

    private final GroupSQLHandler GROUP_SQL_HANDLER = GitAPI.MANAGEMENT.groupSQLHandler;

    private final MoneySQLHandler MONEY_SQL_HANDLER;

    /**
     * Конструктор, для вызова нового юзера.
     *
     * @param name - имя игрока.
     */
    GamerImpl(String name) {
        this.name = name;

        this.group = GROUP_SQL_HANDLER.getPlayerGroup(name);

        MONEY_SQL_HANDLER = new MoneySQLHandler();

    }

    @Override
    public void setSpectator() {
        this.spectator = true;
    }

    @Override
    public void removeSpectator() {
        this.spectator = false;
    }

    @Override
    public boolean spectator() {
        return spectator;
    }

    @Override
    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public void changeGroup(Group group) {
        GROUP_SQL_HANDLER.changeGroup(this, group);
    }

    @Override
    public void setGamerPermissible(Player player) {
        new GamerPermissible(player, this);
    }

    @Override
    public void changeMoney(int money) {

    }

    @Override
    public int getMoney() {
        return 0;
    }

    @Override
    public int getMoon() {
        return 0;
    }

    @Override
    public int getExp() {
        return (int) property.get("EXP");
    }

    @Override
    public int getLevel() {
        return (int) ILeveling.getLevel(this.getExp());
    }

    @Override
    public Group getGroup() {
        return this.group;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void redirect(String server) {

    }

    @Override
    public void itemIsBought(StoreItem storeItem) {

    }

    @Override
    public void itemIsSelected(StoreItem storeItem) {

    }

    @Override
    public boolean itemCanBuy(StoreItem storeItem) {
        return false;
    }
}