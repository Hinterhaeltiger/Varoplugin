package dev.onetone.varo.commands.getteam;

import dev.onetone.varo.Varo;
import dev.onetone.varo.config.Config;
import dev.onetone.varo.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetTeamCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (sender instanceof Player)   {
            /*Config config = new Config();

            if(strings.length == 1) {
                Player req = Bukkit.getPlayer(strings[0]);

                sender.sendMessage(config.getTeam(req.getPlayer()).toString());
            } else {
                sender.sendMessage("Please provide a valid player as an argument.");
            }
            */





            Team exteam = new Team(1, (List<OfflinePlayer>) Bukkit.getOfflinePlayer("hinterhältiger"), "test");
            Varo.getPlugin().getConfig().set("teams", exteam.toString());


            Varo.getPlugin().saveConfig();

            return true;
        } else {
            return false;
        }
    }
}
