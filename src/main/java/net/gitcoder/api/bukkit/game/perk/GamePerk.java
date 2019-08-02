package net.gitcoder.api.bukkit.game.perk;

import lombok.Getter;
import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.Management;
import net.gitcoder.api.bukkit.game.type.GameType;
import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */

@Getter
public abstract class GamePerk implements Listener {

    private final String perkName, displayPerkName;

    private final ItemStack displayItem;

    private final int cost, level;

    /**
     * Конструктор, создавающий новый перк.
     *
     * @param perkName          - (имя перка. (Для миски, кора, и т.д))
     * @param displayPerkName   - (Дисплей имя перка)
     * @param cost              - (Цена, для игроков, для донатеров)
     * @param level             - (Уровень группы, для которой перк бесплатный)
     */
    public GamePerk(String perkName,
                    String displayPerkName,
                    ItemStack displayItem,

                    int cost,
                    int level) {

        this.perkName = perkName;
        this.displayPerkName = displayPerkName;

        this.displayItem = displayItem;

        this.cost = cost;
        this.level = level;

        GitAPI.getPlugin(GitAPI.class).MANAGEMENT.PERK_MANAGER.createPerk(this);
    }

    /**
     * Активация перка.
     *
     * @param player - игрок.
     */
    protected void onUse(Player player) {

    }

    /**
     * Активая перка.
     *
     * @param gamer - геймер.
     */
    protected void onUse(Gamer gamer) {

    }

    /**
     * Установка перка игроку.
     *
     * @param gamer - геймер.
     */
    public void selectPerk(Gamer gamer) {
        gamer.setPerk(this);
    }

    /**
     * Проверка есть ли у игрока перк.
     *
     * @return - true or false.
     */
    public boolean hasPerk(Gamer gamer) {
        return gamer.hasPerk(this);
    }
}