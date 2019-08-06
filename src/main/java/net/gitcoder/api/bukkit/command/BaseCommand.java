package net.gitcoder.api.bukkit.command;

import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.bukkit.gamer.human.DefaultGamer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public abstract class BaseCommand extends Command implements CommandExecutor {

    protected BaseCommand(String name) {
        super(name);
    }

    public abstract void executeCommand(Player player, DefaultGamer gamer, String[] strings);

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return false;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        final Player player = (Player) commandSender;
        final DefaultGamer gamer = GitAPI.MANAGEMENT.getGamer(player.getName());

        executeCommand(player, gamer, strings);
        return false;
    }
}
