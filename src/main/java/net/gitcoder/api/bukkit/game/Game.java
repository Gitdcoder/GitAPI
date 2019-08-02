package net.gitcoder.api.bukkit.game;

import lombok.Getter;
import lombok.NonNull;
import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.Management;
import net.gitcoder.api.bukkit.game.announcer.GameAnnouncer;
import net.gitcoder.api.bukkit.game.perk.GamePerk;
import net.gitcoder.api.bukkit.game.setting.GameSettings;
import net.gitcoder.api.bukkit.game.type.GameState;
import net.gitcoder.api.bukkit.game.type.GameType;
import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public abstract class Game extends GameAnnouncer implements Listener {

    protected final Management MANAGEMENT = GitAPI.MANAGEMENT;

    protected final GameSettings GAME_SETTINGS = MANAGEMENT.GAME_SETTINGS;


    private final Map<Integer, Listener> listenerMap = new HashMap<>();


    @Getter
    private final List<Gamer> alivePlayers = new ArrayList<>();


    private final SpectatorManager spectatorManager = new SpectatorManager();
    private final GameState gameState = GameState.WAITING;
    private final PluginManager pluginManager = Bukkit.getPluginManager();

    /**
     * Конструктор, устанавливает игру,
     * чтобы в дальнейшем с работать с классом Game.
     *
     * @param gameType - тип игры.
     * @param players - количество людей.
     */
    public Game(GameType gameType, int players) {

        GAME_SETTINGS.GAME_TYPE = gameType;

        GAME_SETTINGS.MAX_PLAYERS_COUNT = players;
    }

    /**
     * Срабатывает, когда таймер заканчивается.
     */
    public abstract void startGame();

    /**
     * Срабатывает, когда игрок умирает.
     * @param player - игрок.
     */
    public abstract void onDeath(@NonNull Player player, @NonNull Player killer);


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
    public void registerListener(Integer id, @NonNull Class<? extends Listener> listener) {

        Listener event = null;

        try {
            event = listener.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        pluginManager.registerEvents(event, GitAPI.getInstance());

        listenerMap.put(id, event);
    }

    /**
     * Разрегистрация листенера по ид.
     * @param id - ид.
     */
    public void unRegisterListener(Integer id) {
        HandlerList.unregisterAll(listenerMap.get(id));
    }

    @EventHandler
    public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event) {
        final int online = Bukkit.getOnlinePlayers().size();
        final int maxOnline = MANAGEMENT.GAME_SETTINGS.MAX_PLAYERS_COUNT;

        Gamer gamer = MANAGEMENT.GAMER_STORAGE.getGamer(event.getName());

        if (gameState.equals(GameState.STARTING)) {
            List<Gamer> gamers = new ArrayList<>();

            if (online == maxOnline && gamer.getGroup().isDonate()) {
                for (Gamer random : MANAGEMENT.GAMER_STORAGE.getGamers()) {
                    if (random.getGroup().getLevel() < gamer.getGroup().getLevel()) {
                        gamers.add(random);
                    }
                }
            } else if (online == maxOnline) {
                gamer.redirect("TestLobby-1");
                gamer.getPlayer().sendMessage("§cАрена на данной игре начинается...");
            }

            final Gamer randomGamer = gamers.stream().
                    sorted(Comparator.comparing(sortedGamer -> sortedGamer.getGroup().getLevel())).
                    collect(Collectors.toList()).get(0);

            final String kickMessage = "Ваше место занял %s";

            randomGamer.redirect("TestLobby-1");
            randomGamer.getPlayer().sendMessage(String.format(kickMessage, gamer.getGroup().getPrefix() + event.getName()));

        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Gamer gamer = MANAGEMENT.GAMER_STORAGE.getGamer(player);

        if (gameState.equals(GameState.GAME)) {
            spectatorManager.setSpectator(player);

            player.sendMessage("§cИгра на данной арене уже началась! Вы можете только наблюдать за процессом.");
            return;
        }

        final String joinMessage = "Игрок %s §fприсоединился к игре (§c%s§8/§a%s)";
        final int online = Bukkit.getOnlinePlayers().size();
        final int maxOnline = MANAGEMENT.GAME_SETTINGS.MAX_PLAYERS_COUNT;

        broadcast(String.format(joinMessage, player.getDisplayName(), online, maxOnline), true);

        final GamePerk gamePerk = MANAGEMENT.SQL_PERK_HANDLER.getPlayerPerkByName(player.getName());
        final List<GamePerk> purchasePerk = MANAGEMENT.SQL_PERK_HANDLER.getPlayerPerksByName(player.getName());

        gamer.setPerk(gamePerk);
        gamer.setPurchasePerks(purchasePerk);

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        onDeath(player, killer);
    }

}
