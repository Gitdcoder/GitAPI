package net.gitcoder.api.bukkit.game.setting;

import net.gitcoder.api.bukkit.game.type.GameType;
import net.gitcoder.api.bukkit.utility.ItemUtil;
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
public final class GameSettings {

    public final ItemStack EXIT_ITEM = ItemUtil.newBuilder(Material.SLIME_BALL).setName("§6Выход в лобби").create();
    public final ItemStack SHOP_ITEM = ItemUtil.newBuilder(Material.CHEST).setName("§6Магазин с перками и китами").create();
    public final ItemStack SPECTATOR_MENU_ITEM = ItemUtil.newBuilder(Material.COMPASS).setName("§6Локатор игроков").create();
    public final ItemStack SPECTATOR_SETTING_ITEM = ItemUtil.newBuilder(Material.REDSTONE_COMPARATOR_ON).setName("§6Настройки наблюдателя").create();

    public int MAX_PLAYERS_COUNT;
    public int MAX_PLAYERS_IN_TEAM;

    public GameType GAME_TYPE;
}
