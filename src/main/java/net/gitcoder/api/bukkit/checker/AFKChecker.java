package net.gitcoder.api.bukkit.checker;

import net.gitcoder.api.bukkit.GitAPI;
import net.md_5.bungee.event.EventHandler;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

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
public class AFKChecker implements Listener {

    private final Map<String, BukkitTask> afk = new HashMap<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
            Player player = event.getPlayer();
            String name = player.getName();

            if (event.getFrom().getBlockX() != event.getTo().getBlockX()
                    || event.getFrom().getBlockY() != event.getTo().getBlockY()
                    || event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {
                BukkitTask task = afk.remove(name);

                if (task != null)
                    task.cancel();
                return;
            }

            if (afk.containsKey(name))
                return;

            BukkitTask active = new BukkitRunnable() {
                int timeAFK = 0;
                int timeTitle = 0;

                @Override
                public void run() {
                        if (!player.isOnline()) {
                            cancel();
                            afk.remove(name);
                        }

                        if (timeAFK >= 2700) {
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_CHIME, 1, 1);

                            if (timeTitle >= 10) {
                                timeTitle = 0;
                                player.sendTitle("", "§cНе стойте в афк!",
                                        0, 3, 1);
                            }

                            timeTitle++;
                        }

                        timeAFK++;
                }
            }.runTaskTimer(GitAPI.getInstance(), 1L, 1L);
            afk.put(name, active);
    }
}
