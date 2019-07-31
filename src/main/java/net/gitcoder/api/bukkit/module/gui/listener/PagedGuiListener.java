package net.gitcoder.api.bukkit.module.gui.listener;

import net.gitcoder.api.bukkit.module.gui.PagedGui;
import net.gitcoder.api.bukkit.module.gui.item.GuiItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
public class PagedGuiListener implements Listener {

    private PagedGui pagedGui;

    public PagedGuiListener(PagedGui pagedGui) {
        this.pagedGui = pagedGui;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onInvClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!event.getClickedInventory().equals(pagedGui.getInventory())) return;
        if (event.isCancelled()) return;

        ItemStack item = event.getCurrentItem();
        if (item == null) return;

        event.setCancelled(true);

        int clickedSlot = event.getRawSlot();
        Player player = (Player) event.getWhoClicked();

        GuiItem guiItem = pagedGui.getButtonTable().get(pagedGui.getCurrentPage(), clickedSlot);

        if (guiItem != null) {
            guiItem.getClickAction().onClick(player);
        }
    }

}
