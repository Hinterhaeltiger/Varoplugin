package dev.onetone.varo.commands.jointeam;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JoinTeamCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (sender instanceof Player) {
            return true;
        } else {
            sender.sendMessage("You have to be a player entity to execute this command.");
            return false;
        }
    }
}
