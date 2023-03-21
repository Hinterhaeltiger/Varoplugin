package dev.onetone.varo.teams;

import org.bukkit.entity.Player;

public class Team {
    Integer id;
    Player[] members;
    String name;
    public Team (Integer id, Player[] members) {
        this.id = id;
        this.members = members;
    }
    public static void join(Player joiningplayer, Integer id)   {

    }
    public String getName() {
        return this.name;
    }
    public Player[] getMembers() {
        return members;
    }
}
