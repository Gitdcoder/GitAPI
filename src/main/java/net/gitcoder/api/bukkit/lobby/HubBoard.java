package net.gitcoder.api.bukkit.lobby;

import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.gamer.human.DefaultGamer;
import net.gitcoder.api.bukkit.gamer.level.ILeveling;
import net.gitcoder.api.bukkit.module.sidebar.Sidebar;
import net.gitcoder.api.bukkit.module.sidebar.objective.SidebarObjective;
import net.gitcoder.api.java.utility.DecimalUtil;
import org.bukkit.entity.Player;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class HubBoard {

    public HubBoard(Player player) {
        Sidebar sidebar = new Sidebar();

        DefaultGamer defaultGamer = GitAPI.MANAGEMENT.getGamer(player);

        sidebar.setObjective(new SidebarObjective("hubObjective",
                "§e§lMoonWays Network"));

        sidebar.setLine(12,"");
        sidebar.setLine(11,"Ваш ранг: " + defaultGamer.getGroup().getPrefix());
        sidebar.setLine(10,"");
        sidebar.setLine(9,"Монет: §6" + DecimalUtil.getStringFormat(defaultGamer.getMoney()));
        sidebar.setLine(8,"Мунов: §a" + DecimalUtil.getStringFormat(defaultGamer.getMoon()));
        sidebar.setLine(7,"");
        sidebar.setLine(6,"Ваш уровень: §d" + defaultGamer.getLevel());
        sidebar.setLine(5, getScale(ILeveling.getPercentageToNextLevel(defaultGamer.getExp())));
        sidebar.setLine(4,"Текущий EXP: §b" + defaultGamer.getExp());
        sidebar.setLine(3,"");
        sidebar.setLine(2,"Онлайна проекта: §a" + GitAPI.MANAGEMENT.GLOBAL_ONLINE);
        sidebar.setLine(1,"");
        sidebar.setLine(0,"§ewww.moonways.me");

        sidebar.send(player);
    }

    private String getScale(double percent) {
        int part = (int)Math.round(10.0 * percent);

        StringBuilder scale = new StringBuilder((part == 0) ? "" : "§a");

        for (int i = 0; i < 10; ++i) {
            if (i == part) {
                scale.append("§7");
            }
            scale.append('■');
        }
        return scale.toString();
    }
}
