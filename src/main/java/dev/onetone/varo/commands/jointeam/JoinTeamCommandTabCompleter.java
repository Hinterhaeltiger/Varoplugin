package dev.onetone.varo.commands.jointeam;

import dev.onetone.varo.config.Config;
import dev.onetone.varo.teams.Team;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JoinTeamCommandTabCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Config config = new Config();
        List<String> teamnames = null;

        for (Team t: config.getTeams()) {
             teamnames.add(t.getName());
        }
        
        return teamnames;
    }
}
