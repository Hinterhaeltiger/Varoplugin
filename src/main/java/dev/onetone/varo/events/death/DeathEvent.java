package dev.onetone.varo.events.death;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;

public class DeathEvent implements Listener {
    private int get(String[] type) {
        return new Random().nextInt(type.length);
    }



    private void sendVictim(String[] type, PlayerDeathEvent e) {
        Player victim = e.getEntity();

        int res = get(type);
        String dm = type[res].replaceAll("player", victim.getName());
        e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', dm));
    }

    private void sendVictimAndKiller(String[] type, PlayerDeathEvent e) {
        Player victim = e.getEntity();

        int res = get(type);
        String dm;


        if (victim.getKiller() != null) {
            dm = (type[res]
                    .replaceAll("player", victim.getName())
                    .replaceAll("kil", victim.getKiller().getName())
            );
        } else {
            dm = (type[res]
                    .replaceAll("player", victim.getName())
                    .replaceAll("kil", victim.getKiller().getName())
            );
        }



        e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', dm));
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        DeathMessages messages = new DeathMessages();
        Player victim = e.getEntity();

        String lastCause = Objects.requireNonNull(e.getEntity().getLastDamageCause()).getCause().name();

        if (lastCause.equals("ENTITY_ATTACK")) {
            Player killer = victim.getKiller();

            if (killer != null) {
                    sendVictimAndKiller(messages.Melee, e);
            } else { sendVictim(messages.Mob, e); }
        }

        else if (Objects.equals(victim.getKiller(), victim)) { sendVictim(messages.Suicide, e); }

        else if (lastCause.equals("LAVA")) { sendVictim(messages.Burned, e); }

        else if (lastCause.equals("FIRE_TICK")) { sendVictim(messages.Burned, e); }

        else if (lastCause.equals("SUFFOCATION")) { sendVictim(messages.Suffocation, e); }

        else if (lastCause.equals("STARVATION")) { sendVictim(messages.Starved, e); }

        else if (lastCause.equals("FALL")) { sendVictim(messages.FallDamage, e); }

        else if (lastCause.equals("ENTITY_EXPLOSION")) { sendVictim(messages.Explosion, e); }

        else if (lastCause.equals("DROWNING")) { sendVictim(messages.Drowned, e); }

        else if (lastCause.equals("IMPALED")) { sendVictim(messages.Drowned, e); }

        else if (lastCause.equals("WITHER")||lastCause.equals("MAGIC")) { sendVictim(messages.Potion, e); }

        else {
            sendVictim(messages.Wildcard, e);
            Bukkit.getLogger().log(Level.INFO, lastCause);
        }
    }

    public void onProjectileHitEvent(ProjectileHitEvent e) {
        DeathMessages messages = new DeathMessages();

        Player victim = (Player) e.getHitEntity();
        if (victim != null) {
            if (victim.isDead()) {
                String[] type = messages.Arrow;
                String killedWith = e.getEntity().getName();

                int res = get(type);
                String dm = (type[res]
                        .replaceAll("player", victim.getName())
                        .replaceAll("kil", Objects.requireNonNull(victim.getKiller()).getName())
                        .replaceAll("wep", killedWith)
                );
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', dm));
            }
        }
    }
}