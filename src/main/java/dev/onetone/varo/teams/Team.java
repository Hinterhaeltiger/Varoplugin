package dev.onetone.varo.teams;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class Team {
    Integer id;
    List<OfflinePlayer> members;
    String name;
    public Team (Integer id, List<OfflinePlayer> members, String name) {
        this.id = id;
        this.members = members;
        this.name = name;
    }
    public static void join(Player joiningplayer, Integer id)   {

    }
    public String getName() {
        return this.name;
    }
    public List<OfflinePlayer> getMembers() {
        return members;
    }
}
