package de.jannnnek.duno.npc.trait;

import de.jannnnek.duno.Duno;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;

public class LookTrait extends Trait {
    public LookTrait() {
        super("looktrait");
    }

    @Override
    public void run() {
        if(this.getNPC().getEntity() != null) {
            Player closestPlayer = getNearestPlayer(this.getNPC().getEntity().getLocation());
            if (closestPlayer != null) this.getNPC().faceLocation(closestPlayer.getLocation());
        }
    }

    public static @Nullable Player getNearestPlayer(Location location) {
        World world = location.getWorld();
        ArrayList<Player> playersInWorld = new ArrayList<>(world.getEntitiesByClass(Player.class));
        playersInWorld.sort(Comparator.comparingDouble(o -> o.getLocation().distanceSquared(location)));
        playersInWorld.removeIf(player -> Duno.npcRegistry.isNPC(player));
        if(playersInWorld.isEmpty()) return null;
        return playersInWorld.get(0);
    }
}
