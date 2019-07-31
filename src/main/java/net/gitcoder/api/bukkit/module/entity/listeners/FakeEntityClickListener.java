package net.gitcoder.api.bukkit.module.entity.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import net.gitcoder.api.bukkit.module.entity.FakeEntity;
import net.gitcoder.api.java.utility.CooldownUtil;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FakeEntityClickListener extends PacketAdapter {

    public FakeEntityClickListener(Plugin plugin) {
        super(plugin, PacketType.Play.Client.USE_ENTITY);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        Player player = event.getPlayer();

        if (CooldownUtil.hasCooldown(player.getName().concat("_fake_click"))) {
            return;
        }

        EnumWrappers.EntityUseAction entityUseAction = event.getPacket().getEntityUseActions().read(0);

        if (entityUseAction != EnumWrappers.EntityUseAction.INTERACT) {
            return;
        }

        FakeEntity fakeEntity = FakeEntity.getEntityById(event.getPacket().getIntegers().read(0));

        if (fakeEntity == null || fakeEntity.getClickAction() == null) {
            return;
        }

        fakeEntity.getClickAction().accept(player);

        CooldownUtil.putCooldown(player.getName().concat("_fake_click"), 1000);
    }
}
