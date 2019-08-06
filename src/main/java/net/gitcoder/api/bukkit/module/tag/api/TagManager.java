package net.gitcoder.api.bukkit.module.tag.api;

import net.gitcoder.api.bukkit.utility.TagDataMapUtil;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;

public class TagManager {

    private TagDataMapUtil datas = new TagDataMapUtil();

    public TagManager() {
        TagAppiler.init(this);
    }

    public void setTag(Player player, String prefix) {
        setTag(player, prefix, "");
    }

    public void setTag(Player player, String prefix, String suffix) {
        setTag(player, player.getName(), prefix, suffix);
    }

    public void setTag(Player player, String team, String prefix, String suffix) {
        team = team.toLowerCase();

        synchronized(this) {
            if(hasTag(player)) clearTag(player);
        }

        TagData data = datas.get(team);

        if(data == null) {
            data = new TagData(team, prefix, suffix);
            datas.put(team, data);
            data.getPacket().send();

            if(data.getName().contains(player.getName())) {
                data.destroy();
                data.getPacket().send();
            }
        }
        synchronized(this) {
            data.addPlayer(player);
        }
    }

    public void sendTags(Player player) {
        datas.values().forEach(data -> data.getPacket().send(player));
    }

    public void clearTags() {
        datas.values().forEach(this::removeTeam);
    }

    public void removeTeam(TagData team) {
        removeTeam(team.getName());
    }

    public void removeTeam(String team) {
        if(datas.get(team) == null) return;
        TagData data = datas.get(team);
        data.destroy();
        datas.remove(team);
    }

    public void clearTag(Player player) {
        datas.values()
                .stream()
                .filter(data -> data.hasPlayer(player))
                .forEach(data -> data.removePlayer(player));
    }

    public boolean hasTag(Player player) {
        return getTagData(player) != null;
    }

    public TagData getTagData(Player player) {
        for(TagData data: datas.values()) {
            if(!data.hasPlayer(player)) continue;
            return data;
        }
        return null;
    }

    public Map<String, TagData> getDatasMap() {
        return datas;
    }

    public Collection<TagData> getDatas() {
        return datas.values();
    }
}
