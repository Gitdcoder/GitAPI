package net.gitcoder.api.bukkit.module.tag;

import net.gitcoder.api.bukkit.Management;
import net.gitcoder.api.bukkit.gamer.group.Group;
import net.gitcoder.api.bukkit.module.tag.api.TagManager;
import net.gitcoder.api.bukkit.utility.TeamTagUtil;
import org.bukkit.entity.Player;

public class ProtocolTags {

    private static TagManager tagManager = new TagManager();

    public static void setTag(Player player) {
        Group group = Management.getGamer(player).getGroup();

        tagManager.setTag(player, group.getGroup(),
                    TeamTagUtil.colorize(group.getPrefix()), TeamTagUtil.colorize(group.getSuffix()));

        player.setDisplayName(TeamTagUtil.colorize(group.getPrefix()) + player.getDisplayName());
    }
}
