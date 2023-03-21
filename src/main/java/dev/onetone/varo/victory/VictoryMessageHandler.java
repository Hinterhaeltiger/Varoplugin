package dev.onetone.varo.victory;

import dev.onetone.varo.Varo;
import dev.onetone.varo.config.Config;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;


public class VictoryMessageHandler {
    private final JavaPlugin varo;
    public VictoryMessageHandler(JavaPlugin varo)   {
        this.varo = varo;
    }
    public void announceWinner (Player winner, Integer winnerkillcount) {
        List<Player> onlinePlayers = (List<Player>) Bukkit.getOnlinePlayers();



        Config config = new Config();

        String titlemessage = config.getVictoryMessageTitle().replaceAll("/winner/", winner.getName()).replaceAll("/wkills/", winnerkillcount.toString());
        String subtitlemessage = config.getVictoryMessageSubTitle().replaceAll("/winner/", winner.getName()).replaceAll("/wkills/", winnerkillcount.toString());
        String chatmessage = config.getVictoryChatMessage().replaceAll("/winner/", winner.getName()).replaceAll("/wkills/", winnerkillcount.toString());


        onlinePlayers.forEach(player -> setPlayertitle(player, titlemessage, subtitlemessage));
        Bukkit.getServer().broadcastMessage(chatmessage);
    }
    private void setPlayertitle(Player player, String titlemessage, String subtitlemessage)  {
        player.sendTitle(titlemessage, subtitlemessage, 10, 100, 10);
    }
}
