package net.gitcoder.api.bukkit.module.store;

import lombok.Getter;
import net.gitcoder.api.bukkit.module.store.coins.DiscountCoins;
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
public class StoreItem extends DiscountCoins {

    private ItemStack displayItem;
    private String name, displayName;

    /**
     * Конструктор, создавающий новый предмет в шопе.
     * @param displayItem - дисплей предмета.
     * @param name - имя предмета.
     * @param displayName - дисплей имя предмета.
     */
    public StoreItem(ItemStack displayItem,
                     String name,
                     String displayName) {

        this.displayItem = displayItem;

        this.name = name;

        this.displayName = displayName;

    }

}


