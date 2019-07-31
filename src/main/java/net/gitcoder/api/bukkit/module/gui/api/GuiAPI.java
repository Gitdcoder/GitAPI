package net.gitcoder.api.bukkit.module.gui.api;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface GuiAPI {

    /**
     * Подготовка инвентаря к его работе.
     */
    void prepareInventory();

    /**
     * Очистка инвентаря.
     */
    void clearInventory();

    /**
     * Очистка внутреннего кеша.
     */
    void clearButtons();

    /**
     * Открытие инвентаря игроку.
     */
    void openGui();
}
