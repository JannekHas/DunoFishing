package de.jannnnek.duno.command;

import de.jannnnek.duno.Duno;
import de.jannnnek.duno.region.Region;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {

    //TODO: überall entfernen
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        for (Region region : Region.values()) {
            if (region.getStringWorld().equals(strings[0])) {
                player.teleport(Duno.getRegionSpawnPoint(region));
                return true;
            }
        }
        return true;
    }
}
