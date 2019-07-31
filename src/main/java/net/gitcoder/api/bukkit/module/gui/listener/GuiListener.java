package net.gitcoder.api.bukkit.module.gui.listener;

import net.gitcoder.api.bukkit.module.gui.Gui;
import net.gitcoder.api.bukkit.module.gui.item.GuiItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class GuiListener implements Listener {

    private Gui gui;

    public GuiListener(Gui gui) {
        this.gui = gui;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;
        if (!gui.getInventory().equals(e.getClickedInventory())) {
            return;
        }

        ItemStack itemStack = e.getCurrentItem();
        if (itemStack == null) return;

        e.setCancelled(true);

        int slot = e.getRawSlot();
        Player player = (Player) e.getWhoClicked();

        GuiItem guiItem = gui.getGuiItem(slot);

        if (guiItem != null) {
            guiItem.getClickAction().onClick(player);
        }
    }

}
