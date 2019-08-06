package net.gitcoder.api.bukkit.gamer.human;

import net.gitcoder.api.bukkit.module.store.StoreItem;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface GameGamer extends BungeeGamer {

    void itemIsBought(StoreItem storeItem);

    void itemIsSelected(StoreItem storeItem);

    boolean itemCanBuy(StoreItem storeItem);


}
