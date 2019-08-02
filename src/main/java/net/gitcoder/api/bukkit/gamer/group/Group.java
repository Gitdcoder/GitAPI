package net.gitcoder.api.bukkit.gamer.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.gitcoder.api.bukkit.GitAPI;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
@AllArgsConstructor
@Getter
@Setter
public class Group {

    private static final File file = new File(GitAPI.getInstance().getDataFolder(), "groups.yml");

    private String group;
    private String prefix;
    private String suffix;

    private int level;
    private int id;
    private int price;

    private List<String> PERMISSIONS;

    /**
     * Вызов конструктора, который создаёт новую группу.
     * @param group - имя группы.
     * @param prefix - префикс.
     * @param suffix - суффикс.
     * @param level - левел группы.
     * @param id - айди.
     * @param price - цена.
     */
    public Group(String group, String prefix,
                 String suffix,
                 int level,
                 int id,
                 int price) {

        this.group = group;
        this.prefix = prefix;
        this.suffix = suffix;
        this.level = level;
        this.id = id;
        this.price = price;

        this.PERMISSIONS = getPermissions();
    }

    /**
     * Получение всех прав у группы.
     * @return - лист с правами.
     */
    private List<String> getPermissions() {
        if (PERMISSIONS == null || PERMISSIONS.isEmpty()) {
            PERMISSIONS = new ArrayList<>();

            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            ConfigurationSection configurationSection = yamlConfiguration.getConfigurationSection("Groups." + group);

            if (configurationSection == null) {
                return null;
            }

            return PERMISSIONS = configurationSection.getStringList("Permissions");
        }

        return PERMISSIONS;
    }

    /**
     * Проверка есть ли у группы данное право.
     * @param permission - право.
     * @return true or false.
     */
    public boolean hasPermission(String permission) {
        return PERMISSIONS.contains(permission);
    }

    public boolean isDonate() {
        return level > 0;
    }
}
