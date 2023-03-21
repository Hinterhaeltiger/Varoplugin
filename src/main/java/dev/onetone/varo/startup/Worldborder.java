package dev.onetone.varo.startup;

import io.papermc.paper.math.Position;
import net.kyori.adventure.text.BlockNBTComponent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

public class Worldborder {
    public static void setWorldBorderSize(){
        World world = Bukkit.getWorld("world");
        WorldBorder border = world.getWorldBorder();

        double worldspawnx = world.getSpawnLocation().getBlockX();
        double worldspawny = world.getSpawnLocation().getBlockY();

        border.setSize(760.0);
        border.setCenter(worldspawnx, worldspawny);
    }
}
