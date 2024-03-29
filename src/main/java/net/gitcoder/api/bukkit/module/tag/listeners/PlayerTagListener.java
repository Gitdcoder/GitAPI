package net.gitcoder.api.bukkit.module.tag.listeners;

import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.Management;
import net.gitcoder.api.bukkit.module.tag.ProtocolTags;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerTagListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(GitAPI.getInstance(), () -> {
            if(!event.getPlayer().isOnline()) return;
            GitAPI.MANAGEMENT.tagManager.sendTags(event.getPlayer());
        }, 10L);

        Bukkit.getScheduler().runTaskLaterAsynchronously(GitAPI.getInstance(), () -> {
            if(!event.getPlayer().isOnline()) return;
            ProtocolTags.setTag(event.getPlayer());
        }, 12L);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        GitAPI.MANAGEMENT.tagManager.clearTag(e.getPlayer());
    }
}
