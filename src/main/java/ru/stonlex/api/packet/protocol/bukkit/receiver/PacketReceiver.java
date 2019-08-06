package ru.stonlex.api.packet.protocol.bukkit.receiver;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import lombok.RequiredArgsConstructor;
import net.gitcoder.api.bukkit.GitAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.function.Consumer;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 19:08)
 */
@RequiredArgsConstructor
public class PacketReceiver implements PluginMessageListener {

    private final int packetId;

    private final Consumer<ByteArrayDataInput> inputConsumer;


    @Override
    public void onPluginMessageReceived(String channel, Player receiver, byte[] bytes) {
        if (!channel.equals(GitAPI.MANAGEMENT.packetProtocol.PACKER_CHANNEL_NAME)) {
            return;
        }

        ByteArrayDataInput dataInput = ByteStreams.newDataInput(bytes);

        int acceptedPacketId = dataInput.readInt();

        if (packetId == acceptedPacketId) {
            inputConsumer.accept(dataInput);
        }
    }

}
