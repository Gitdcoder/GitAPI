package ru.stonlex.api.packet.protocol.bungee;

import com.google.common.io.ByteArrayDataInput;
import lombok.RequiredArgsConstructor;
import net.gitcoder.api.bungee.BungeeGitAPI;
import net.md_5.bungee.api.ProxyServer;
import ru.stonlex.api.packet.MessagingPacket;
import ru.stonlex.api.packet.protocol.bungee.receiver.ProxyPacketReceiver;
import ru.stonlex.api.packet.protocol.bungee.sender.ProxyPacketSender;

import java.util.function.Consumer;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 19:11)
 */
@RequiredArgsConstructor
public class ProxyPacketProtocol {

    private final BungeeGitAPI gitAPI = BungeeGitAPI.getInstance();


    public final String PACKER_CHANNEL_NAME = "GitAPI";

    private final ProxyPacketSender PACKET_SENDER = new ProxyPacketSender();


    /**
     * Чтение пакета
     *
     * @param id - id пакета
     * @param inputConsumer - консумер, принимающий InputStream
     */
    public void readPacket(int id, Consumer<ByteArrayDataInput> inputConsumer) {
        ProxyPacketReceiver proxyPacketReceiver = new ProxyPacketReceiver(id, inputConsumer);

        ProxyServer.getInstance().getPluginManager().registerListener(gitAPI, proxyPacketReceiver);
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
