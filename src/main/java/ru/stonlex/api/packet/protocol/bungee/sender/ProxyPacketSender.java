package ru.stonlex.api.packet.protocol.bungee.sender;

import lombok.Getter;
import net.gitcoder.api.bungee.BungeeGitAPI;
import net.md_5.bungee.api.ProxyServer;
import ru.stonlex.api.packet.MessagingPacket;

import java.util.Arrays;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 19:08)
 */
@Getter
public class ProxyPacketSender {


    /**
     * Отправить пакет на бомжу
     *
     * @param messagingPacket - Пакет
     */
    public void sendPacket(MessagingPacket messagingPacket) {
        ProxyServer.getInstance().getServers().values().forEach(
                serverInfo -> serverInfo.sendData(BungeeGitAPI.MANAGEMENT.PACKET_PROTOCOL.PACKER_CHANNEL_NAME, messagingPacket.getBytes()));
    }

    /**
     * Отправить несколько пакетов на бомжу
     *
     * @param messagingPackets - Пакеты
     */
    public void sendPackets(MessagingPacket... messagingPackets) {
        Arrays.asList(messagingPackets).forEach(
                this::sendPacket);
    }

}
