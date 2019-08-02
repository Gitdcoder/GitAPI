package net.gitcoder.api.bukkit.server;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 20:38)
 */
@RequiredArgsConstructor
@Getter
public class GitServer {

    private final String serverName, serverMotd, serverAddress;

    private final int playersCount, serverPort;
}
