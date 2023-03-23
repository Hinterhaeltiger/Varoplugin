package dev.onetone.varo.commands.getteam;

import dev.onetone.varo.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetTeamCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (sender instanceof Player)   {
            Config config = new Config();
            Player req = Bukkit.getPlayer(strings[0]);

            sender.sendMessage(config.getTeam(req.getPlayer()).toString());
            return true;
        } else {
            return false;
        }
    }
}
