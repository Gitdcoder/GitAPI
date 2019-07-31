package net.gitcoder.api.bukkit.gamer.group.permissible;

import net.gitcoder.api.bukkit.gamer.humans.Gamer;
import net.gitcoder.api.bukkit.gamer.group.Group;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;

import java.lang.reflect.Field;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class GamerPermissible extends PermissibleBase {

    private final Gamer gamer;

    /**
     * Выдача прав игроку.
     * @param player - игрок.
     * @param gamer - геймер.
     */
    public GamerPermissible(Player player, Gamer gamer) {
        super(player);

        this.gamer = gamer;

        try {
            setPermission(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка есть ли право у игрокаю
     * @param permission - право.
     * @return true or false.
     */
    @Override
    public boolean hasPermission(String permission) {
        Group group = gamer.getGroup();

        return group.hasPermission(permission);
    }

    /**
     * Установка права игроку.
     * @param player - плеер.
     * @throws Exception - ошибка 000000000000.
     */
    private void setPermission(Player player) throws Exception {
        Field field = CraftHumanEntity.class.getDeclaredField("perm");
        field.setAccessible(true);

        field.set(player, this);
    }

}
