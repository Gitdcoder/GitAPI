package ru.stonlex.api.packet.packets.bungee;

import com.google.common.io.ByteArrayDataOutput;
import net.md_5.bungee.api.ProxyServer;
import ru.stonlex.api.packet.MessagingPacket;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 20:13)
 */
public class ProxyOnlinePacket extends MessagingPacket {

    public ProxyOnlinePacket() {
        super(0);
    }

    @Override
    public void write(ByteArrayDataOutput dataOutput) {
        dataOutput.writeInt(ProxyServer.getInstance().getOnlineCount());
    }

}
