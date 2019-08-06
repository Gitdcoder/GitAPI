package net.gitcoder.api.bukkit.module.store;

import lombok.Getter;
import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.module.store.clickable.ClickableStore;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
@Getter
public class GameStore {

    private final Set<StoreItem> storeItems = new HashSet<>();

    private final StoreType enumStore;
    private final ClickableStore clickableStore;

    /**
     * Создание нового магазина.
     * @param enumStore  - имя магазина.
     * @param clickableStore - действие при клике.
     */
    public GameStore(StoreType enumStore,
                     ClickableStore clickableStore) {

        this.enumStore = enumStore;
        this.clickableStore = clickableStore;
    }

    /**
     * Добавление предмета в магазин.
     * @param storeItem - предмет.
     */
    public void addStoreItem(StoreItem storeItem) {
        storeItems.add(storeItem);
    }
}
