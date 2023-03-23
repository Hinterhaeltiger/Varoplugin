package dev.onetone.varo;

import dev.onetone.varo.commands.getteam.GetTeamCommand;
import dev.onetone.varo.commands.jointeam.JoinTeamCommand;
import dev.onetone.varo.commands.jointeam.JoinTeamCommandTabCompleter;
import dev.onetone.varo.commands.reloadborder.ReloadBorderCommand;
import dev.onetone.varo.commands.setwinnerpodest.SetWinnerPodestCommand;
import dev.onetone.varo.commands.setwinnerpodest.SetWinnerPodestCommandTabCompletor;
import dev.onetone.varo.commands.testvictory.TestVictoryCommand;
import dev.onetone.varo.config.Config;
import dev.onetone.varo.startup.Worldborder;
import org.bukkit.Bukkit;
import dev.onetone.varo.events.death.DeathEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Varo extends JavaPlugin {

    public Varo getPlugin() {
        return (Varo) this;
    }

    @Override
    public void onEnable() {

        Bukkit.getServer().getLogger().log(Level.INFO, "Varo loaded (VER 0.1.0)");
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);

        Config config = new Config();

        config.init();

        Worldborder.setWorldBorderSize();

        getCommand("setwinnerpodest").setTabCompleter(new SetWinnerPodestCommandTabCompletor());
        getCommand("jointeam").setTabCompleter(new JoinTeamCommandTabCompleter());


        getCommand("getteam").setExecutor(new GetTeamCommand());
        getCommand("jointeam").setExecutor(new JoinTeamCommand());
        getCommand("setwinnerpodest").setExecutor(new SetWinnerPodestCommand(this));
        getCommand("testvictory").setExecutor(new TestVictoryCommand());
        getCommand("reloadBorder").setExecutor(new ReloadBorderCommand(this));
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().log(Level.INFO, "Plugin unloaded");
    }
}
