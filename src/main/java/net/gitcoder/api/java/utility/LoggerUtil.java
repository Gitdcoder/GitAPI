package net.gitcoder.api.java.utility;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
@UtilityClass
public class LoggerUtil {

    /**
     * Оповещение в консоль.
     * @param format - текст.
     * @param args - аргументы.
     */
    public void info(String format, Object... args) {
        Bukkit.getConsoleSender().sendMessage(String.format(format, args));
    }

    /**
     * Оповещение в консоль в префиксом.
     * @param prefix - префикс.
     * @param format - текст.
     * @param args - аргументы.
     */

    public void info(String prefix, boolean defaultPrefix, String format, Object... args) {
        if (defaultPrefix) {
            prefix = "§f[§cGitAPI§f] ";
        }

        Bukkit.getConsoleSender().sendMessage(prefix.concat(String.format(format, args)));
    }
}
