package dev.onetone.varo.config;

import dev.onetone.varo.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class TeamConfig {
    private List<Team> teams;
    public List<Team> getteams() {
        return teams;
    }
    public void addTeam(Team team) {
        teams.add(team);
    }
}
