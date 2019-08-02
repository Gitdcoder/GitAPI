package ru.stonlex.api.packet.protocol.bungee.receiver;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import lombok.RequiredArgsConstructor;
import net.gitcoder.api.bungee.BungeeGitAPI;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.function.Consumer;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 19:08)
 */
@RequiredArgsConstructor
public class ProxyPacketReceiver implements Listener {

    private final int packetId;

    private final Consumer<ByteArrayDataInput> inputConsumer;


    @EventHandler
    public void onPluginMessageReceived(PluginMessageEvent event) {
        if (!event.getTag().equals(BungeeGitAPI.MANAGEMENT.PACKET_PROTOCOL.PACKER_CHANNEL_NAME)) {
            return;
        }

        ByteArrayDataInput dataInput = ByteStreams.newDataInput(event.getData());

        int acceptedPacketId = dataInput.readInt();

        if (packetId == acceptedPacketId) {
            inputConsumer.accept(dataInput);
        }
    }

}
