package net.gitcoder.api.bukkit.module.store.registry;

import net.gitcoder.api.bukkit.module.store.GameStore;
import net.gitcoder.api.bukkit.module.store.coins.DiscountCoins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class RegistryStore {

    private final List<GameStore> stores = new ArrayList<>();

    /**
     * Регистрация магазина.
     * @param clazz - класс с магазином.
     */
    public void registerStore(Class<? extends GameStore> clazz) {

        try {

            final GameStore gameStore = clazz.newInstance();
            stores.add(gameStore);

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Регистрация предмета в магазине.
     * @param clazz - класс с магазином.
     */
    public void registerStoreItem(Class<? extends DiscountCoins> clazz) {

        try {

            clazz.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение всех магазинов.
     * @return - коллекцию с магазинами.
     */
    public Collection<GameStore> getStores() {
        return stores;
    }
}
