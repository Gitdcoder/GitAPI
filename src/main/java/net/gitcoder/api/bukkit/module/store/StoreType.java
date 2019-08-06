package net.gitcoder.api.bukkit.module.store;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
@Getter
@RequiredArgsConstructor
public enum StoreType {

    ABILITY("Ability",
            "§eУмения",
            Material.BLAZE_POWDER),

    KITS("Kits",
            "§eНаборы",
            Material.BOW),

    BED_BREAKING_MUSIC("BedBreakingMusic",
            "§eЗвук ломающейся кровати",
            Material.BED),

    EGG_BREAKING_MUSIC("EggBreakingMusic",
            "§eЗвук ломающегося яйца",
            Material.DRAGON_EGG),

    CAGES("Cages",
            "§Клетки",
            Material.STAINED_GLASS);

    private final String accessModifier;
    private final String displayName;
    private final Material material;

}
