package net.gitcoder.api.bukkit.module.gui;

import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.Getter;
import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.gamer.human.HumanGamer;
import net.gitcoder.api.bukkit.module.gui.action.ClickAction;
import net.gitcoder.api.bukkit.module.gui.api.GuiAPI;
import net.gitcoder.api.bukkit.module.gui.item.GuiItem;
import net.gitcoder.api.bukkit.module.gui.listener.GuiListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public abstract class Gui implements GuiAPI {

    private final TIntObjectHashMap<GuiItem> guiItemTIntObjectHashMap = new TIntObjectHashMap<>();

    @Getter
    private Inventory inventory;

    @Getter
    public Player player;

    public HumanGamer gamer;

    /**
     * Конструктор, который создает начальный иннвентарь.
     *
     * @param player - открывающий.
     * @param name - имя инвентаря.
     * @param rows - строки.
     */
    public Gui(Player player,
               String name,
               int rows) {

        this.inventory = Bukkit.createInventory(null,  rows * 9, name);
        this.player = player;

        GitAPI.MANAGEMENT.getGamer(player);

        GuiListener guiListener = new GuiListener(this);
        Bukkit.getPluginManager().registerEvents(guiListener, GitAPI.getInstance());

    }

    /**
     * Подготовка инвентаря.
     */
    protected abstract void drawInventory();

    /**
     * Добавление кнопок в инвентарь.
     * @param slot - слот.
     * @param itemStack - кнопка.
     * @param clickAction - действие.
     */
    public void addItem(int slot, ItemStack itemStack, ClickAction clickAction) {
        guiItemTIntObjectHashMap.put(slot, new GuiItem(slot, itemStack, clickAction));
    }

    @Override
    public void prepareInventory() {
        for (GuiItem guiItem : getItems()) {
            this.inventory.setItem(guiItem.getSlot(), guiItem.getItemStack());
        }
    }

    @Override
    public void clearInventory() {
        this.inventory.clear();
    }

    @Override
    public void clearButtons() {
        guiItemTIntObjectHashMap.clear();
    }

    @Override
    public void openGui() {
        drawInventory();
        prepareInventory();

        player.openInventory(this.inventory);
    }

    private Collection<GuiItem> getItems() {
        return guiItemTIntObjectHashMap.valueCollection();
    }

    public GuiItem getGuiItem(int slot) {
        return guiItemTIntObjectHashMap.get(slot);
    }

}
