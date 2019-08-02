package net.gitcoder.api.bungee;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import ru.stonlex.api.packet.packets.bungee.ProxyOnlinePacket;
import ru.stonlex.api.packet.packets.bungee.ProxyServersPacket;

import java.util.concurrent.TimeUnit;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 20:11)
 */
public class BungeeGitAPI extends Plugin {

    @Getter
    public static BungeeGitAPI instance; {
        instance = this;
    }


    public static final BungeeManagement MANAGEMENT = new BungeeManagement();

    @Override
    public void onEnable() {
        getProxy().getScheduler().schedule(this, () -> {
            MANAGEMENT.PACKET_PROTOCOL.sendPacket(new ProxyOnlinePacket());
            MANAGEMENT.PACKET_PROTOCOL.sendPacket(new ProxyServersPacket());

        }, 0, 5, TimeUnit.SECONDS);
    }

}
