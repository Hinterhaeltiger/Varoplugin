package dev.onetone.varo.commands.setwinnerpodest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class SetWinnerPodestCommandTabCompletor implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String[] taboptions = {"1", "2"};


        return Arrays.asList(taboptions);
    }
}
