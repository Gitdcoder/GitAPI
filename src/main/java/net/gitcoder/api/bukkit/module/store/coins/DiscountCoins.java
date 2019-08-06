package net.gitcoder.api.bukkit.module.store.coins;

import lombok.Getter;
import lombok.Setter;

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
public class DiscountCoins {

    private final Map<String, Integer> coinsDiscount = new HashMap<>();
    private final List<String> aliveForGroup = new ArrayList<>();

    @Getter
    @Setter
    private int coins;

    /**
     * Добавление скидки для донатера.
     * @param groupName - имя группы.
     * @param discount - процент.
     */
    public void addCoinsDiscount(String groupName, int discount) {
        coinsDiscount.put(groupName, discount);
    }

    /**
     * Установить бесплатный предмет для донатера.
     * @param groupName - имя группы.
     */
    public void setAliveForGroup(String groupName) {
        aliveForGroup.add(groupName);
    }

    /**
     * Получить процент скидки донатера.
     * @param groupName - имя группы.
     * @return - процент.
     */
    public int getCoinsDiscount(String groupName) {
        return coinsDiscount.get(groupName);
    }

    /**
     * Получение уже готового числа со скидкой.
     * @param groupName - имя группы.
     * @return - число со скидкой.
     */
    public int getCostCoinsDiscount(String groupName) {
        return coins * coinsDiscount.get(groupName) / 100;
    }

    /**
     * Проверка бесплатный ли для группы предмет.
     * @param groupName - имя группы.
     * @return - true or false.
     */
    public boolean isAliveForGroup(String groupName) {
        return aliveForGroup.contains(groupName);
    }

}
