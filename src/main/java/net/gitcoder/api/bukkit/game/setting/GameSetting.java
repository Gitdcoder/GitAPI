package net.gitcoder.api.bukkit.game.setting;

import net.gitcoder.api.bukkit.game.type.GameType;
import net.gitcoder.api.java.utility.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
@SuppressWarnings("all")
public class GameSetting {

    public GameType GAME_TYPE;

    public final ItemStack EXIT_ITEM;
    public final ItemStack SHOP_ITEM;
    public final ItemStack SPECTATOR_MENU_ITEM;
    public final ItemStack SPECTATOR_SETTING_ITEM;

    public int MAX_PLAYERS_COUNT;
    public int MAX_PLAYERS_IN_TEAM;

    /**
     * Конструктор, создавающий стандартные настройки.
     */
    public GameSetting() {
        this.EXIT_ITEM = ItemUtil.newBuilder(Material.SLIME_BALL).setName("§6Выход в лобби").create();
        this.SHOP_ITEM = ItemUtil.newBuilder(Material.CHEST).setName("§6Магазин с перками и китами").create();
        this.SPECTATOR_MENU_ITEM = ItemUtil.newBuilder(Material.COMPASS).setName("§6Локатор игроков").create();
        this.SPECTATOR_SETTING_ITEM = ItemUtil.newBuilder(Material.REDSTONE_COMPARATOR_ON).setName("§6Настройки наблюдателя").create();
    }


}
