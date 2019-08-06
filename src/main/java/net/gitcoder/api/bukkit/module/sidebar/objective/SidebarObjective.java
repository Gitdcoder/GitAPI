package net.gitcoder.api.bukkit.module.sidebar.objective;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.gitcoder.api.bukkit.module.sidebar.Sidebar;
import net.gitcoder.api.java.packet.scoreboard.WrapperPlayServerScoreboardDisplayObjective;
import net.gitcoder.api.java.packet.scoreboard.WrapperPlayServerScoreboardObjective;
import org.bukkit.entity.Player;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
@Getter
@AllArgsConstructor
public class SidebarObjective {

    private static final int SIDEBAR = 1;

    @Getter
    private final String name;
    private String displayName;

    public void setDisplayName(String displayName, Sidebar sidebar) {
        this.displayName = displayName;

        WrapperPlayServerScoreboardObjective packet = getPacket();
        packet.setMode(WrapperPlayServerScoreboardObjective.Mode.UPDATE_VALUE);
        sidebar.broadcastPacket(packet);
    }
    public void create(Player player) {
        WrapperPlayServerScoreboardObjective packet = getPacket();
        packet.setMode(WrapperPlayServerScoreboardObjective.Mode.ADD_OBJECTIVE);

        packet.sendPacket(player);
    }

    public void remove(Player player) {
        WrapperPlayServerScoreboardObjective packet = getPacket();
        packet.setMode(WrapperPlayServerScoreboardObjective.Mode.REMOVE_OBJECTIVE);

        packet.sendPacket(player);
    }

    public void show(Player player) {
        WrapperPlayServerScoreboardDisplayObjective displayObjective = new WrapperPlayServerScoreboardDisplayObjective();
        displayObjective.setPosition(SIDEBAR);
        displayObjective.setScoreName(name);

        displayObjective.sendPacket(player);
    }

    private WrapperPlayServerScoreboardObjective getPacket() {
        WrapperPlayServerScoreboardObjective packet = new WrapperPlayServerScoreboardObjective();
        packet.setDisplayName(displayName);
        packet.setName(name);
        packet.setHealthDisplay(WrapperPlayServerScoreboardObjective.HealthDisplay.INTEGER);
        return packet;
    }
}

