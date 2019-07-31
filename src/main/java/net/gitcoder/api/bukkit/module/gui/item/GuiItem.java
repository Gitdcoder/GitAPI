package net.gitcoder.api.bukkit.module.gui.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.gitcoder.api.bukkit.module.gui.action.ClickAction;
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
@AllArgsConstructor
public class GuiItem {

    private int slot;
    private ItemStack itemStack;
    private ClickAction clickAction;
}
