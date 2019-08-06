package ru.stonlex.api.packet.protocol.bukkit.sender;

import lombok.Getter;
import net.gitcoder.api.bukkit.GitAPI;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import ru.stonlex.api.packet.MessagingPacket;

import java.util.Arrays;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 19:08)
 */
@Getter
public class PacketSender {

    private final Server SERVER_SENDER = Bukkit.getServer();


    /**
     * Отправить пакет на бомжу
     *
     * @param messagingPacket - Пакет
     */
    public void sendPacket(MessagingPacket messagingPacket) {
        SERVER_SENDER.sendPluginMessage(GitAPI.getInstance(), GitAPI.MANAGEMENT.packetProtocol.PACKER_CHANNEL_NAME, messagingPacket.getBytes());
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
