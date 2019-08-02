package ru.stonlex.api.packet.protocol.bukkit;

import com.google.common.io.ByteArrayDataInput;
import lombok.experimental.UtilityClass;
import net.gitcoder.api.bukkit.GitAPI;
import org.bukkit.Bukkit;
import ru.stonlex.api.packet.MessagingPacket;
import ru.stonlex.api.packet.protocol.bukkit.receiver.PacketReceiver;
import ru.stonlex.api.packet.protocol.bukkit.sender.PacketSender;

import java.util.function.Consumer;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 19:11)
 */
public class PacketProtocol {

    public final String PACKER_CHANNEL_NAME = "GitAPI";

    private final PacketSender PACKET_SENDER = new PacketSender();


    /**
     * Чтение пакета
     *
     * @param id - id пакета
     * @param inputConsumer - консумер, принимающий InputStream
     */
    public void readPacket(int id, Consumer<ByteArrayDataInput> inputConsumer) {
        PacketReceiver packetReceiver = new PacketReceiver(id, inputConsumer);

        Bukkit.getMessenger().registerIncomingPluginChannel(GitAPI.getInstance(), PACKER_CHANNEL_NAME, packetReceiver);
    }

    /**
     * Отправка пакета бомже
     */
    public void sendPacket(MessagingPacket messagingPacket) {
        PACKET_SENDER.sendPacket(messagingPacket);
    }

    /**
     * Отправка пакетов бомже
     */
    public void sendPackets(MessagingPacket... messagingPackets) {
        PACKET_SENDER.sendPackets(messagingPackets);
    }

}
