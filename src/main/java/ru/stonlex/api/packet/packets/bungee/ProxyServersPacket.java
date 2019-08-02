package ru.stonlex.api.packet.packets.bungee;

import com.google.common.io.ByteArrayDataOutput;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import ru.stonlex.api.packet.MessagingPacket;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 20:29)
 */
public class ProxyServersPacket extends MessagingPacket {

    public ProxyServersPacket() {
        super(1);
    }

    @Override
    public void write(ByteArrayDataOutput dataOutput) {
        ProxyServer proxyServer = ProxyServer.getInstance();

        dataOutput.writeInt(proxyServer.getServers().size());

        for (ServerInfo serverInfo : proxyServer.getServers().values()) {
            dataOutput.writeUTF(serverInfo.getName());
            dataOutput.writeUTF(serverInfo.getMotd());
            dataOutput.writeUTF(serverInfo.getAddress().getHostName());

            dataOutput.writeInt(serverInfo.getPlayers().size());
            dataOutput.writeInt(serverInfo.getAddress().getPort());
        }
    }
}
