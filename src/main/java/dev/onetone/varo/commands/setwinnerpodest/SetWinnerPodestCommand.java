package dev.onetone.varo.commands.setwinnerpodest;

import dev.onetone.varo.Varo;
import dev.onetone.varo.config.Config;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetWinnerPodestCommand implements CommandExecutor{
    private final Varo varo;
    public SetWinnerPodestCommand(Varo varo)   {
        this.varo = varo;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (sender instanceof Player)   {
            Location inputcoords = ((Player) sender).getLocation();
            Location winnerpodestcoords = new Location(inputcoords.getWorld(), inputcoords.getBlockX(), inputcoords.getBlockY(), inputcoords.getBlockZ(), 180, 0);
            Config config = new Config();


            sender.sendMessage(winnerpodestcoords.toString());
            config.setWinnerPodestCoords(winnerpodestcoords);

            return true;
        } else {
            sender.sendMessage("You have to be a player entity in order to execute this command.");
            return false;
        }
    }
}
