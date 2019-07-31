package net.gitcoder.api.bukkit.gamer.listener;

import net.gitcoder.api.bukkit.Management;
import net.gitcoder.api.bukkit.gamer.GamerStorage;
import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import net.gitcoder.api.java.utility.LoggerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class GamerListener implements Listener {

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        final GamerStorage gamerStorage = Management.GAMER_STORAGE;

        gamerStorage.loadGamer(event.getName());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Gamer gamer = Management.getGamer(player);

        gamer.setPlayer(player);
        gamer.setGamerPermissible(player);

        LoggerUtil.info("§6GitAPI §8| §fGamer %s has been loaded", player.getName());
    }
}
