package net.gitcoder.api.bukkit.game;

import lombok.NonNull;
import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.Management;
import net.gitcoder.api.bukkit.game.announcer.GameAnnouncer;
import net.gitcoder.api.bukkit.game.setting.GameSetting;
import net.gitcoder.api.bukkit.game.type.GameState;
import net.gitcoder.api.bukkit.game.type.GameType;
import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public abstract class Game extends GameAnnouncer implements Listener {

    protected final GameSetting GAME_SETTING = Management.GAME_SETTING;

    private final Map<Integer, Listener> cache = new HashMap<>();

    private final List<Gamer> players = new ArrayList<>();

    private SpectatorManager spectatorManager = new SpectatorManager();
    private GameState gameState = GameState.WAITING;
    private PluginManager pluginManager = Bukkit.getPluginManager();

    /**
     * Конструктор, устанавливает игру,
     * чтобы в дальнейшем с работать с классом Game.
     *
     * @param gameType - тип игры.
     * @param slots - количество слотов.
     */
    public Game(GameType gameType, int slots) {

    }

    /**
     * Срабатывает, когда таймер заканчивается.
     */
    public abstract void startGame();

    /**
     * Срабатывает, когда игрок умирает.
     * @param player - игрок.
     */
    public abstract void onDeath(@NonNull Player player);


    /**
     * Срабатывает, когда игра заканчивается.
     * @param player - победитель.
     */
    protected void endGame(@NonNull Player player) {

    }

    /**
     * Срабатывает, когда игра заканчивается.
     * @param players - победители.
     */
    protected void endGame(@NonNull Player... players) {

    }

    /**
     * Регистрация листенера по ид.
     * @param id - ид.
     * @param listener - листенер.
     */
    public void registerListener(Integer id, @NonNull Listener listener) {
        pluginManager.registerEvents(listener, GitAPI.getPlugin(GitAPI.class));
        cache.put(id, listener);
    }

    /**
     * Разрегистрация листенера по ид.
     * @param id - ид.
     */
    public void unRegisterListener(Integer id) {
        HandlerList.unregisterAll(cache.get(id));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (gameState.equals(GameState.GAME)) {
            spectatorManager.setSpectator(player);

            player.sendMessage("§cИгра на данной арене уже началась! Вы можете только наблюдать за процессом.");
        }
    }

    public final List<Gamer> getAlivePlayers() {
        return null;
    }
}
