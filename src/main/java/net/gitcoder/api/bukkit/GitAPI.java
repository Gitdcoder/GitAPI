package net.gitcoder.api.bukkit;

import com.comphenix.protocol.ProtocolLibrary;
import net.gitcoder.api.bukkit.gamer.listener.GamerListener;
import net.gitcoder.api.bukkit.module.entity.listeners.FakeEntityClickListener;
import net.gitcoder.api.bukkit.module.tag.listeners.PlayerTagListener;
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

    private final MySQL DATABASE = MySQL
            .newBuilder()
            .host("localhost")
            .user("root")
            .database("database")
            .create();

    public Management MANAGEMENT = new Management();

    /**
     * Override method, triggered when the server start.
     */
    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new GamerListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerTagListener(), this);

        //LoggerUtil.info("",true,"События были загружены во внутренний кеш");

        registerProtocolListener();

    }

    /**
     * Регистрация пакетного листенера для прослушки клика
     * по MoonFakeEntity
     */
    private void registerProtocolListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new FakeEntityClickListener(this));
    }


    /**
     * Override method, troggered when the server shut down.
     */
    @Override
    public void onDisable() {

    }

    public MySQL getDatabase() {
        return DATABASE;
    }
}
