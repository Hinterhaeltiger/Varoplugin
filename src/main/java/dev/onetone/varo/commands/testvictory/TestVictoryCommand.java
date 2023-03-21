package dev.onetone.varo.commands.testvictory;

import dev.onetone.varo.Varo;
import dev.onetone.varo.victory.Victory;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestVictoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player)    {
            sender.sendMessage("Testing victory animation and messages.");

            Victory victory = new Victory((Player) sender, ((Player) sender).getStatistic(Statistic.PLAYER_KILLS));
            victory.activate((Player) sender);
            return true;
        }
        else {
            return false;
        }
    }
}
