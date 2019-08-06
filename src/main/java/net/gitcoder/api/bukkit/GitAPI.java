package net.gitcoder.api.bukkit;

import com.comphenix.protocol.ProtocolLibrary;
import lombok.Getter;
import net.gitcoder.api.bukkit.checker.AFKChecker;
import net.gitcoder.api.bukkit.gamer.listener.GamerListener;
import net.gitcoder.api.bukkit.module.entity.listeners.FakeEntityClickListener;
import net.gitcoder.api.bukkit.module.tag.listeners.PlayerTagListener;
import net.gitcoder.api.bukkit.server.GitServer;
import net.gitcoder.api.java.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class GitAPI extends JavaPlugin {

    @Getter
    private final MySQL database = MySQL
            .newBuilder()
            .host("localhost")
            .user("root")
            .database("database")
            .create();

    @Getter
    public static GitAPI instance; {
        instance = this;
    }

    public static Management MANAGEMENT = new Management();


    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new GamerListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerTagListener(), this);
        Bukkit.getPluginManager().registerEvents(new AFKChecker(), this);

        registerProtocolListener();
        readPackets();
    }

    /**
     * @ItzStonlex
     *
     * Чтение пакетов, приходящих с бомжи
     */
    private void readPackets() {
        /*
         * @ItzStonlex
         *
         * Обновление глобального онлайна
         */
        MANAGEMENT.packetProtocol.readPacket(0, dataInput -> MANAGEMENT.GLOBAL_ONLINE = dataInput.readInt());


        /*
          @ItzStonlex
         *
         * Обновление данных серверов бомжи
         */
        MANAGEMENT.packetProtocol.readPacket(1, dataInput -> {
            MANAGEMENT.proxyServersMap.clear();

            int serversCount = dataInput.readInt();

            for (int i = 0 ; i < serversCount ; i++) {
                String serverName = dataInput.readUTF();
                String serverMotd = dataInput.readUTF();
                String serverAddress = dataInput.readUTF();

                int playersCount = dataInput.readInt();
                int serverPort = dataInput.readInt();

                GitServer gitServer = new GitServer(serverName, serverMotd, serverAddress,
                                                    playersCount, serverPort);

                MANAGEMENT.proxyServersMap.put(serverName, gitServer);
            }
        });
    }

    /**
     * Регистрация пакетного листенера для прослушки клика
     * по FakeEntity
     */
    private void registerProtocolListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new FakeEntityClickListener(this));
    }

}
