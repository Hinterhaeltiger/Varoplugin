package dev.onetone.varo.commands.reloadborder;

import dev.onetone.varo.Varo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import dev.onetone.varo.startup.Worldborder;
import org.jetbrains.annotations.NotNull;

public class ReloadBorderCommand implements CommandExecutor {
    private final Varo varo;
    public ReloadBorderCommand(Varo varo)   {
        this.varo = varo;

    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Worldborder.setWorldBorderSize();
        commandSender.sendMessage("Successfully reloaded worldborder size from config");

        return true;
    }
}
