package net.gitcoder.api.bukkit.module.store.builder;

import net.gitcoder.api.bukkit.module.store.StoreItem;
import org.bukkit.inventory.ItemStack;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class StoreBuilder {

    public static StoreBuilder newBuilder(ItemStack displayItem,
                                          String name,
                                          String displayName) {

        return new StoreBuilder(displayItem, name, displayName);
    }

    private final StoreItem storeItem;

    public StoreBuilder(ItemStack displayItem,
                        String name,
                        String displayName) {

        storeItem = new StoreItem(displayItem, name, displayName);
    }

    public StoreBuilder setCoins(int coins) {
        storeItem.setCoins(coins);

        return this;
    }

    public StoreBuilder setAliveForGroup(String name) {
        storeItem.setAliveForGroup(name);

        return this;
    }

    public StoreBuilder addDiscountCoins(String groupName, int discount) {
        storeItem.addCoinsDiscount(groupName, discount);

        return this;
    }


    public StoreItem create() {
        return storeItem;
    }

}
