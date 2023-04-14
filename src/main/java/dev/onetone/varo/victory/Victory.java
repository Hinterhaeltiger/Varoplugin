package dev.onetone.varo.victory;

import dev.onetone.varo.Varo;
import dev.onetone.varo.config.Config;
import io.papermc.paper.math.Position;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Victory {
    private Player winner;
    private Integer winnerkillcount;
    public Victory (Player winner, Integer winnerkillcount) {
        this.winner = winner;
        this.winnerkillcount = winnerkillcount;
    }


    public void activate(Player activator) {
        JavaPlugin varoplugin = Varo.getProvidingPlugin(Varo.class);
        try {
            FileConfiguration configfile = varoplugin.getConfig();
            Config config = new Config();

            if (configfile.get("victorymessage.title") != null && configfile.get("victorymessage.subtitle") != null && configfile.get("victorymessage.chatmessage") != null && configfile.get("winnerpodestx") != null && configfile.get("winnerpodesty") != null && configfile.get("winnerpodestz") != null) {
                VictoryMessageHandler messageHandler = new VictoryMessageHandler(varoplugin);
                messageHandler.announceWinner(activator, winnerkillcount);

                activator.teleport(config.getWinnerPodestCoords());

            } else {
                Bukkit.getServer().broadcast(Component.text("config file is incomplete!"));
            }
        } catch (NullPointerException e)    {
            e.printStackTrace();
            Bukkit.getServer().broadcast(Component.text(e.getMessage()));
        }
    }
}
