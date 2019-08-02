package net.gitcoder.api.bukkit.utility;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import lombok.Setter;
import net.gitcoder.api.java.utility.ReflectionUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public final class ItemUtil {

    @Setter
    @Getter
    private ItemStack itemStack;

    /**
     * Получение нового билдера.
     * @param material - материал.
     * @return - новый итем утил.
     */
    public static ItemUtil newBuilder(Material material) {
        return new ItemUtil(material);
    }

    /**
     * Получение нового билдера.
     * @param itemStack - материал.
     * @return - новый итем утил.
     */
    public static ItemUtil newBuilder(ItemStack itemStack) {
        return new ItemUtil(itemStack);
    }

    /**
     * Конструктор, создаёт новый материал.
     * @param material - материал.
     */
    private ItemUtil(Material material) {
        itemStack = new ItemStack(material);
    }

    /**
     * Конструктор, создаёт новый материал.
     * @param itemStack - материал.
     */
    private ItemUtil(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Установка имени предмету.
     * @param name - имя.
     * @return - this.
     */
    public ItemUtil setName(String name) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        return this;
    }

    /**
     * Установка лоров предмету.
     * @param lore - лоры.
     * @return - this.
     */
    public ItemUtil setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    /**
     * Установка лоров предмету.
     * @param lore - лоры.
     * @return - this.
     */
    private ItemUtil setLore(List<String> lore) {
        ItemMeta im = itemStack.getItemMeta();
        im.setLore(lore);
        itemStack.setItemMeta(im);
        return this;
    }

    /**
     * Установка количества предмету.
     * @param amount - кол-во.
     * @return - this.
     */
    public ItemUtil setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    /**
     * Установка даты предмету.
     * @param durability - дура.
     * @return - this.
     */
    public ItemUtil setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    /**
     * Установка лоров предмету.
     * @param value - установить glow или нет.
     * @return - this.
     */
    public ItemUtil setGlow(boolean value) {
        if (value) {
            addFlag();
            this.itemStack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        } else {
            this.itemStack.removeEnchantment(Enchantment.DIG_SPEED);
        }
        return this;
    }

    /**
     * Установка лоров предмету.
     * @param lore - лоры.
     * @return - this.
     */
    public ItemUtil addLore(String... lore) {
        return addLore(Arrays.asList(lore));
    }

    /**
     * Установка лоров предмету.
     * @param lore - лоры.
     * @return - this.
     */
    private ItemUtil addLore(List<String> lore) {
        ItemMeta im = itemStack.getItemMeta();
        List<String> oldLore = im.getLore();
        if (oldLore != null) {
            oldLore.addAll(lore);
        } else {
            oldLore = lore;
        }
        im.setLore(oldLore);
        itemStack.setItemMeta(im);
        return this;
    }

    /**
     * Установка флагов предмету.
     * @param flag - флаги.
     * @return - this.
     */
    private void addFlag(ItemFlag... flag) {
        ItemMeta im = itemStack.getItemMeta();
        im.addItemFlags(flag);
        itemStack.setItemMeta(im);
    }

    /**
     * Установка wdtnf предмету.
     * @param color - цвет.
     * @return - this.
     */
    public ItemUtil addColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        leatherArmorMeta.setColor(color);
        itemStack.setItemMeta(leatherArmorMeta);
        return this;
    }

    /**
     * Установка зачарований предмету.
     * @param enchantment - зачарование.
     * @param level - уровень зачарования.
     * @return - this.
     */
    public ItemUtil addEnchantment(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    /**
     * Установка флагов предмету.
     * @return - this.
     */
    private void addFlag() {
        addFlag(ItemFlag.HIDE_ENCHANTS);
    }

    /**
     * Установка скина для головы предмету.
     * @param texture - текстура.
     * @return - this.
     */
    public ItemUtil setHeadSkin(String texture) {
        if (!itemStack.getType().equals(Material.SKULL_ITEM)) {
            return this;
        }
        if (texture == null) {
            return this;
        }
        SkullMeta skull = (SkullMeta) itemStack.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "Steve");
        ReflectionUtil.setField(skull, "profile", setGameProfileTexture(profile, texture));
        itemStack.setItemMeta(skull);
        return this;

    }

    private static GameProfile setGameProfileTexture(final GameProfile p, final String texture) {
        p.getProperties().put("textures", new Property("textures", texture));
        return p;
    }

    public ItemStack create() {
        return itemStack;
    }

    private static Enchantment getEnchantment(String enchant) {
        String s = enchant.toLowerCase();
        if (s.equals("protection")) {
            return Enchantment.PROTECTION_ENVIRONMENTAL;
        }
        if (s.equals("protection_projectile")) {
            return Enchantment.PROTECTION_PROJECTILE;
        }
        if (s.equals("protection_fire")) {
            return Enchantment.PROTECTION_FIRE;
        }
        if (s.equals("featherfall")) {
            return Enchantment.PROTECTION_FALL;
        }
        if (s.equals("protection_explosions")) {
            return Enchantment.PROTECTION_EXPLOSIONS;
        }
        if (s.equals("respiration")) {
            return Enchantment.OXYGEN;
        }
        if (s.equals("aquaaffinity")) {
            return Enchantment.WATER_WORKER;
        }
        if (s.equals("sharpness")) {
            return Enchantment.DAMAGE_ALL;
        }
        if (s.equals("smite")) {
            return Enchantment.DAMAGE_UNDEAD;
        }
        if (s.equals("baneofarthropods")) {
            return Enchantment.DAMAGE_ARTHROPODS;
        }
        if (s.equals("knockback")) {
            return Enchantment.KNOCKBACK;
        }
        if (s.equals("fireaspect")) {
            return Enchantment.FIRE_ASPECT;
        }
        if (s.equals("depthstrider")) {
            return Enchantment.DEPTH_STRIDER;
        }
        if (s.equals("looting")) {
            return Enchantment.LOOT_BONUS_MOBS;
        }
        if (s.equals("power")) {
            return Enchantment.ARROW_DAMAGE;
        }
        if (s.equals("punch")) {
            return Enchantment.ARROW_KNOCKBACK;
        }
        if (s.equals("flame")) {
            return Enchantment.ARROW_FIRE;
        }
        if (s.equals("infinity")) {
            return Enchantment.ARROW_INFINITE;
        }
        if (s.equals("efficiency")) {
            return Enchantment.DIG_SPEED;
        }
        if (s.equals("silktouch")) {
            return Enchantment.SILK_TOUCH;
        }
        if (s.equals("unbreaking")) {
            return Enchantment.DURABILITY;
        }
        if (s.equals("fortune")) {
            return Enchantment.LOOT_BONUS_BLOCKS;
        }
        if (s.equals("luckofthesea")) {
            return Enchantment.LUCK;
        }
        if (s.equals("luck")) {
            return Enchantment.LUCK;
        }
        if (s.equals("lure")) {
            return Enchantment.LURE;
        }
        if (s.equals("thorns")) {
            return Enchantment.THORNS;
        }
        return null;
    }

    public static ItemStack parseItem(String itemName, ConfigurationSection section) {
        ItemStack item = new ItemStack(Material.getMaterial(itemName));

        if(section.get("data") != null) {
            item.setDurability(Short.parseShort(String.valueOf(section.get("data"))));
        }

        if(section.isInt("amount")) {
            item.setAmount(section.getInt("amount"));
        }

        if(section.isConfigurationSection("enchants")) {
            section.getConfigurationSection("enchants").getKeys(false).forEach(s -> {
                int level = section.getInt("enchants." + s);
                item.addUnsafeEnchantment(getEnchantment(s), level);
            });
        }

        return item;
    }

}


