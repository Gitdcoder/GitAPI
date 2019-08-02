package net.gitcoder.api.bukkit.gamer.humans;

import net.gitcoder.api.bukkit.game.perk.GamePerk;
import net.gitcoder.api.bukkit.game.type.GameType;
import net.gitcoder.api.bukkit.gamer.perk.SQLPerkHandler;

import java.util.List;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface GameGamer {

    /**
     * Установка спектатора игроку.
     */
    void setSpectator();

    /**
     * Проверка спектатор игрок или нет.
     * @return - да или нет.
     */
    boolean spectator();

    void setPurchasePerks(List<GamePerk> gamePerks);

    void setPerk(GamePerk gamePerk);

    void selectPerk(GamePerk gamePerk);

    boolean selectedPerk(GamePerk gamePerk);

    boolean hasPerk(GamePerk gamePerk);

    SQLPerkHandler getSQLPerkHandler();
}
