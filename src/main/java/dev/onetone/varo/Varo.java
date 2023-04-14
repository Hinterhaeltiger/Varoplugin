package dev.onetone.varo;

import de.eldoria.eldoutilities.config.ConfigKey;
import de.eldoria.eldoutilities.config.JacksonConfig;
import dev.onetone.varo.commands.getteam.GetTeamCommand;
import dev.onetone.varo.commands.jointeam.JoinTeamCommand;
import dev.onetone.varo.commands.jointeam.JoinTeamCommandTabCompleter;
import dev.onetone.varo.commands.reloadborder.ReloadBorderCommand;
import dev.onetone.varo.commands.setwinnerpodest.SetWinnerPodestCommand;
import dev.onetone.varo.commands.setwinnerpodest.SetWinnerPodestCommandTabCompletor;
import dev.onetone.varo.commands.testvictory.TestVictoryCommand;
import dev.onetone.varo.config.ConfigFile;
import dev.onetone.varo.startup.Worldborder;
import org.bukkit.Bukkit;
import dev.onetone.varo.events.death.DeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;
import java.util.logging.Level;

public class Varo extends JavaPlugin {

    public static Varo getPlugin() {
        return (Varo) Bukkit.getPluginManager().getPlugin("Varo");
    }

    @Override
    public void onEnable() {

        // Defining the key for the config.yml
        ConfigKey<ConfigFile> defKey = ConfigKey.defaultConfig(ConfigFile.class, ConfigFile::new);
        JacksonConfig<ConfigFile> config = new JacksonConfig<>(this, defKey);
        // Getting the config.yml data
        ConfigFile general = config.main();

        // Defining a second config key for database.yml
        ConfigKey<ConfigFile> teamsConfig = ConfigKey.of("Database Config", Path.of("teams.yml"), ConfigFile.class, ConfigFile::new);
        // Reloading the config with the key
        
        // Saves all configurations loaded via this instance
        config.save();
        // Saves the configuration associated with this key
        config.save(defKey);


        Bukkit.getServer().getLogger().log(Level.INFO, "Varo loaded (VER 0.1.0)");
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);





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
