package de.jannnnek.duno.tasks;

import de.jannnnek.duno.player.FishingPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class SaveTask extends BukkitRunnable {
    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            UUID uuid = p.getPlayer().getUniqueId();
            if(FishingPlayer.players.containsKey(uuid)){
                FishingPlayer.players.get(uuid).save();
            }
        }
    }
}
