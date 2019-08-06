package net.gitcoder.api.bukkit.module.store.gui;

import net.gitcoder.api.bukkit.module.gui.Gui;
import org.bukkit.entity.Player;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class StoreGui extends Gui {

    /**
     * Конструктор, который создает начальный иннвентарь.
     *
     * @param player - открывающий.
     * @param name   - имя инвентаря.
     * @param rows   - строки.
     */
    public StoreGui(Player player, String name, int rows) {
        super(player, name, rows);
    }

    @Override
    protected void drawInventory() {

    }
}
