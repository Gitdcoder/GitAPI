package net.gitcoder.api.bukkit.game.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
public enum GameType {

    SW("SkyWars"),
    BW("BedWars"),
    EW("EggWars"),
    SM("Smasher");

    private final String name;
}
