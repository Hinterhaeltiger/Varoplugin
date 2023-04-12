package dev.onetone.varo.config;

import dev.onetone.varo.Varo;
import dev.onetone.varo.teams.Team;
import io.papermc.paper.math.Position;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class Config {
    private double winnerpodestcoordsx;
    private double winnerpodestcoordsy;
    private double winnerpodestcoordsz;
    //private World winnerpodestworld;
    private String victorytitle;
    private String victorysubtitle;
    private JavaPlugin plugin = Varo.getProvidingPlugin(Varo.class);
    private FileConfiguration pluginconfig = plugin.getConfig();
    private Team[] teams;
    public void init()  {
        plugin.saveDefaultConfig();
    }
    public void setWinnerPodestCoords(Location position){
        this.winnerpodestcoordsx = position.getBlockX();
        this.winnerpodestcoordsy = position.getBlockY();
        this.winnerpodestcoordsz = position.getBlockZ();


        pluginconfig.set("winnerpodestx", winnerpodestcoordsx);
        pluginconfig.set("winnerpodesty", winnerpodestcoordsy);
        pluginconfig.set("winnerpodestz", winnerpodestcoordsz);
        //pluginconfig.set("winnerpodestworld", winnerpodestworld);

        plugin.saveConfig();
    }
    public Location getWinnerPodestCoords() {
        this.winnerpodestcoordsx = (double) pluginconfig.get("winnerpodestx");
        this.winnerpodestcoordsy = (double) pluginconfig.get("winnerpodesty");
        this.winnerpodestcoordsz = (double) pluginconfig.get("winnerpodestz");

        Location winnerpodestcoords = new Location(Bukkit.getWorld("world"), winnerpodestcoordsx, winnerpodestcoordsy, winnerpodestcoordsz);

        return winnerpodestcoords;
    }
    public void setVictoryMessage(String title, String subtitle, String chatmessage) {
        this.victorytitle = title;
        this.victorysubtitle = subtitle;
        plugin.getConfig().set("victorymessage.title", victorytitle);
        plugin.getConfig().set("victorymessage.subtitle", victorysubtitle);
        plugin.getConfig().set("victorymessage.chatmessage", chatmessage);


        plugin.saveConfig();
    }
    public String getVictoryMessageTitle() {
        return (String) pluginconfig.get("victorymessage.title");
    }
    public String getVictoryMessageSubTitle() {
        return (String) pluginconfig.get("victorymessage.subtitle");
    }
    public String getVictoryChatMessage() {
        return (String) pluginconfig.get("victorymessage.chatmessage");
    }
    public List<Team> getTeams() {
        return Arrays.asList(teams);
    }
    public Player[] getPlayersInTeam(Integer teamid) {
        Player[] players = null;
        for (Team t : getTeams()) {
            if (pluginconfig.get("teams." + teamid.toString()) != null) {
                players = (Player[]) pluginconfig.get("teams." + teamid + ".members");
                continue;
            } else {
                Bukkit.getServer().getLogger().log(Level.WARNING, "The team with the given ID does not exist.");
                players = null;
                continue;
            }
        }
        return players;
    }
    public Team getTeam (Player player) {
        Team playerteam = null;
        for (Team t: getTeams()) {
            for (Player p: t.getMembers()) {
                if (p == player)    {
                    playerteam = t;
                }
            }
        }
        return playerteam;
    }
}
