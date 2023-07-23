package de.jannnnek.duno;

import de.jannnnek.duno.command.TestCommand;
import de.jannnnek.duno.listener.ConnectionListener;
import de.jannnnek.duno.listener.EventListener;
import de.jannnnek.duno.listener.FishingListener;
import de.jannnnek.duno.npc.trait.CollectionTrait;
import de.jannnnek.duno.npc.trait.CosmeticTrait;
import de.jannnnek.duno.npc.trait.HaendlerTrait;
import de.jannnnek.duno.npc.trait.LookTrait;
import de.jannnnek.duno.player.FishingPlayer;
import de.jannnnek.duno.region.Region;
import de.jannnnek.duno.tasks.SaveTask;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.MemoryNPCDataStore;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

public class Duno extends JavaPlugin {

    private static Duno instance;
    public static boolean serverStarting = false;
    public static NPCRegistry npcRegistry;

    @Override
    public void onEnable() {
        instance = this;
        serverStarting = true;
        registerCommandsAndEvents();
        loadWorlds();
        (new SaveTask()).runTaskTimer(getInstance(), 300*20, 600*20);
        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                setNPCs();
                for (OfflinePlayer all : Bukkit.getOfflinePlayers()) {
                    if(!FishingPlayer.players.containsKey(all.getUniqueId())) new FishingPlayer(all.getUniqueId());
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(!FishingPlayer.players.containsKey(p.getUniqueId())) new FishingPlayer(p.getUniqueId());
                }
                serverStarting = false;
            }
        }, 10);
    }

    @Override
    public void onDisable() {
        savePlayers();
        npcRegistry.deregisterAll();
    }

    private void registerCommandsAndEvents() {
        npcRegistry = CitizensAPI.createNamedNPCRegistry("duno", new MemoryNPCDataStore());
        getCommand("switch").setExecutor(new TestCommand());
        getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new FishingListener(), this);
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        // TODO: Citizens
        getServer().getPluginManager().registerEvents(new HaendlerTrait(), this);
        getServer().getPluginManager().registerEvents(new CollectionTrait(), this);
        getServer().getPluginManager().registerEvents(new CosmeticTrait(), this);
        // TODO: Citizens
        CitizensAPI.registerEvents(new HaendlerTrait());
        CitizensAPI.registerEvents(new CollectionTrait());
        CitizensAPI.registerEvents(new CosmeticTrait());
    }

    private void savePlayers() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            UUID uuid = p.getPlayer().getUniqueId();
            if(FishingPlayer.players.containsKey(uuid)){
                FishingPlayer.players.get(uuid).save();
            }
        }
    }

    private void loadWorlds() {
        //TODO: loadWorlds
        Bukkit.getServer().createWorld(new WorldCreator("tumpel"));
    }

    private void setNPCs() {
        Location haendlerLocation = new Location(Bukkit.getWorld("world"), 22.5 , 60,21.5, 120.0f, 0);
        NPC npcHaendler = npcRegistry.createNPC(EntityType.PLAYER, "§bHändler");
        String signatureHaendler = "jYexnU+I+VufAuWNaXCMfIrJS24lUeIvTSZepShdWa2y2OvxLMti+PYAeH3INotw/4e0DCK8As56+uD+fQpsHsp9lbcznfaKwtsr0PWhFZrPCUUwE3axU0c02e+H4RnJ2605DaQAY1WH3ryqOhJznvcGXAbrKa8mBk+RuroZ9fi6DmSciTqV9wH5vGuh5zwyUSxvsS93temyd0L9LerXjfw3IuoyU+G3DUKSnxnjq69B2ZoiT33KBWf96oVJkYWbTlpAH8s+7+H5w+zXQkvtpNi+I1b43foDdpCUeFHop85iets/T4e0jVf2jREHrIkU1NVjPiOuyt3/5umROgOeOpckhARj4v571el+QDZvNBFwFh8LObqYkIc4XAUEnewc9OIm2Frn9Ll/23uZ0R9wGYpkb1S47mMoGs+6fg3njCJSOSJC1TkjcEFx8urQf28bIOQJiqspylcro15oLkJZ2gJUjDr3TtZXOTxcb5vOUIO4mArrQ4N8kIwxLPY0zXyxt6+U1Nq6SIoWMPWXr64urBgF3QPG/t5NwH3RgZoNUJPzRQdlrXFoqZBYh+M1HXrqsX5NNOB1EyMFpKoQMx0ptCLaKZ/7iXPDqTNQEli3EgxhGSWULUi3CtQLi6+7YoLo6jlc6IzsY1CZ74+clYCZcI8B+kwHGZpEVvPzZ7aLwTA=";
        String dataHaendler = "ewogICJ0aW1lc3RhbXAiIDogMTY4NjkyMDkyNzA4MywKICAicHJvZmlsZUlkIiA6ICI3Mjc2ZThmYzVkNjE0ODNjYmMwN2IxYjIzNjI3MDA4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJXYWJvV2ViaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kMGQ2MGYzNDFiNzc5Y2ViYTZkYWZmOWI3NDYzN2RiY2VjNTQxODQyZGRmNmY2N2Q5YWY2NzRjYThkMDFiMTYyIgogICAgfQogIH0KfQ==";
        npcHaendler.addTrait(new LookTrait());
        npcHaendler.getOrAddTrait(SkinTrait.class).setSkinPersistent("haendler", signatureHaendler, dataHaendler);
        npcHaendler.getOrAddTrait(HaendlerTrait.class);
        npcHaendler.spawn(haendlerLocation);

        Location collectionLocation = new Location(Bukkit.getWorld("world"), 20.5 , 60,30.5, 160.0f, 0);
        NPC npcCollection = npcRegistry.createNPC(EntityType.PLAYER, "§bSammlungen");
        String signatureCollection = "jYexnU+I+VufAuWNaXCMfIrJS24lUeIvTSZepShdWa2y2OvxLMti+PYAeH3INotw/4e0DCK8As56+uD+fQpsHsp9lbcznfaKwtsr0PWhFZrPCUUwE3axU0c02e+H4RnJ2605DaQAY1WH3ryqOhJznvcGXAbrKa8mBk+RuroZ9fi6DmSciTqV9wH5vGuh5zwyUSxvsS93temyd0L9LerXjfw3IuoyU+G3DUKSnxnjq69B2ZoiT33KBWf96oVJkYWbTlpAH8s+7+H5w+zXQkvtpNi+I1b43foDdpCUeFHop85iets/T4e0jVf2jREHrIkU1NVjPiOuyt3/5umROgOeOpckhARj4v571el+QDZvNBFwFh8LObqYkIc4XAUEnewc9OIm2Frn9Ll/23uZ0R9wGYpkb1S47mMoGs+6fg3njCJSOSJC1TkjcEFx8urQf28bIOQJiqspylcro15oLkJZ2gJUjDr3TtZXOTxcb5vOUIO4mArrQ4N8kIwxLPY0zXyxt6+U1Nq6SIoWMPWXr64urBgF3QPG/t5NwH3RgZoNUJPzRQdlrXFoqZBYh+M1HXrqsX5NNOB1EyMFpKoQMx0ptCLaKZ/7iXPDqTNQEli3EgxhGSWULUi3CtQLi6+7YoLo6jlc6IzsY1CZ74+clYCZcI8B+kwHGZpEVvPzZ7aLwTA=";
        String dataCollection = "ewogICJ0aW1lc3RhbXAiIDogMTY4NjkyMDkyNzA4MywKICAicHJvZmlsZUlkIiA6ICI3Mjc2ZThmYzVkNjE0ODNjYmMwN2IxYjIzNjI3MDA4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJXYWJvV2ViaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kMGQ2MGYzNDFiNzc5Y2ViYTZkYWZmOWI3NDYzN2RiY2VjNTQxODQyZGRmNmY2N2Q5YWY2NzRjYThkMDFiMTYyIgogICAgfQogIH0KfQ==";
        npcCollection.addTrait(new LookTrait());
        npcCollection.getOrAddTrait(SkinTrait.class).setSkinPersistent("collection", signatureCollection, dataCollection);
        npcCollection.getOrAddTrait(CollectionTrait.class);
        npcCollection.spawn(collectionLocation);

        Location anglerLocation = new Location(Bukkit.getWorld("world"), 16.5 , 60,37.5, 177.0f, 0);
        NPC npcAngler = npcRegistry.createNPC(EntityType.PLAYER, "§bAngler");
        String signatureAngler = "jYexnU+I+VufAuWNaXCMfIrJS24lUeIvTSZepShdWa2y2OvxLMti+PYAeH3INotw/4e0DCK8As56+uD+fQpsHsp9lbcznfaKwtsr0PWhFZrPCUUwE3axU0c02e+H4RnJ2605DaQAY1WH3ryqOhJznvcGXAbrKa8mBk+RuroZ9fi6DmSciTqV9wH5vGuh5zwyUSxvsS93temyd0L9LerXjfw3IuoyU+G3DUKSnxnjq69B2ZoiT33KBWf96oVJkYWbTlpAH8s+7+H5w+zXQkvtpNi+I1b43foDdpCUeFHop85iets/T4e0jVf2jREHrIkU1NVjPiOuyt3/5umROgOeOpckhARj4v571el+QDZvNBFwFh8LObqYkIc4XAUEnewc9OIm2Frn9Ll/23uZ0R9wGYpkb1S47mMoGs+6fg3njCJSOSJC1TkjcEFx8urQf28bIOQJiqspylcro15oLkJZ2gJUjDr3TtZXOTxcb5vOUIO4mArrQ4N8kIwxLPY0zXyxt6+U1Nq6SIoWMPWXr64urBgF3QPG/t5NwH3RgZoNUJPzRQdlrXFoqZBYh+M1HXrqsX5NNOB1EyMFpKoQMx0ptCLaKZ/7iXPDqTNQEli3EgxhGSWULUi3CtQLi6+7YoLo6jlc6IzsY1CZ74+clYCZcI8B+kwHGZpEVvPzZ7aLwTA=";
        String dataAngler = "ewogICJ0aW1lc3RhbXAiIDogMTY4NjkyMDkyNzA4MywKICAicHJvZmlsZUlkIiA6ICI3Mjc2ZThmYzVkNjE0ODNjYmMwN2IxYjIzNjI3MDA4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJXYWJvV2ViaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kMGQ2MGYzNDFiNzc5Y2ViYTZkYWZmOWI3NDYzN2RiY2VjNTQxODQyZGRmNmY2N2Q5YWY2NzRjYThkMDFiMTYyIgogICAgfQogIH0KfQ==";
        npcAngler.addTrait(new LookTrait());
        npcAngler.getOrAddTrait(SkinTrait.class).setSkinPersistent("angler", signatureAngler, dataAngler);
        npcAngler.spawn(anglerLocation);

        Location cosmeticLocation = new Location(Bukkit.getWorld("world"), 6.5 , 60,34.5, -150.0f, 0);
        NPC npcCosmetic = npcRegistry.createNPC(EntityType.PLAYER, "§bCosmetics");
        String signatureCosmetic = "jYexnU+I+VufAuWNaXCMfIrJS24lUeIvTSZepShdWa2y2OvxLMti+PYAeH3INotw/4e0DCK8As56+uD+fQpsHsp9lbcznfaKwtsr0PWhFZrPCUUwE3axU0c02e+H4RnJ2605DaQAY1WH3ryqOhJznvcGXAbrKa8mBk+RuroZ9fi6DmSciTqV9wH5vGuh5zwyUSxvsS93temyd0L9LerXjfw3IuoyU+G3DUKSnxnjq69B2ZoiT33KBWf96oVJkYWbTlpAH8s+7+H5w+zXQkvtpNi+I1b43foDdpCUeFHop85iets/T4e0jVf2jREHrIkU1NVjPiOuyt3/5umROgOeOpckhARj4v571el+QDZvNBFwFh8LObqYkIc4XAUEnewc9OIm2Frn9Ll/23uZ0R9wGYpkb1S47mMoGs+6fg3njCJSOSJC1TkjcEFx8urQf28bIOQJiqspylcro15oLkJZ2gJUjDr3TtZXOTxcb5vOUIO4mArrQ4N8kIwxLPY0zXyxt6+U1Nq6SIoWMPWXr64urBgF3QPG/t5NwH3RgZoNUJPzRQdlrXFoqZBYh+M1HXrqsX5NNOB1EyMFpKoQMx0ptCLaKZ/7iXPDqTNQEli3EgxhGSWULUi3CtQLi6+7YoLo6jlc6IzsY1CZ74+clYCZcI8B+kwHGZpEVvPzZ7aLwTA=";
        String dataCosmetic = "ewogICJ0aW1lc3RhbXAiIDogMTY4NjkyMDkyNzA4MywKICAicHJvZmlsZUlkIiA6ICI3Mjc2ZThmYzVkNjE0ODNjYmMwN2IxYjIzNjI3MDA4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJXYWJvV2ViaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kMGQ2MGYzNDFiNzc5Y2ViYTZkYWZmOWI3NDYzN2RiY2VjNTQxODQyZGRmNmY2N2Q5YWY2NzRjYThkMDFiMTYyIgogICAgfQogIH0KfQ==";
        npcCosmetic.addTrait(new LookTrait());
        npcCosmetic.getOrAddTrait(SkinTrait.class).setSkinPersistent("angler", signatureCosmetic, dataCosmetic);
        npcCosmetic.getOrAddTrait(CosmeticTrait.class);
        npcCosmetic.spawn(cosmeticLocation);

        Location regionLocation = new Location(Bukkit.getWorld("world"), 6.5 , 60,25.5, -130.0f, 0);
        NPC npcRegion = npcRegistry.createNPC(EntityType.PLAYER, "§bRegionen");
        String signatureRegion = "jYexnU+I+VufAuWNaXCMfIrJS24lUeIvTSZepShdWa2y2OvxLMti+PYAeH3INotw/4e0DCK8As56+uD+fQpsHsp9lbcznfaKwtsr0PWhFZrPCUUwE3axU0c02e+H4RnJ2605DaQAY1WH3ryqOhJznvcGXAbrKa8mBk+RuroZ9fi6DmSciTqV9wH5vGuh5zwyUSxvsS93temyd0L9LerXjfw3IuoyU+G3DUKSnxnjq69B2ZoiT33KBWf96oVJkYWbTlpAH8s+7+H5w+zXQkvtpNi+I1b43foDdpCUeFHop85iets/T4e0jVf2jREHrIkU1NVjPiOuyt3/5umROgOeOpckhARj4v571el+QDZvNBFwFh8LObqYkIc4XAUEnewc9OIm2Frn9Ll/23uZ0R9wGYpkb1S47mMoGs+6fg3njCJSOSJC1TkjcEFx8urQf28bIOQJiqspylcro15oLkJZ2gJUjDr3TtZXOTxcb5vOUIO4mArrQ4N8kIwxLPY0zXyxt6+U1Nq6SIoWMPWXr64urBgF3QPG/t5NwH3RgZoNUJPzRQdlrXFoqZBYh+M1HXrqsX5NNOB1EyMFpKoQMx0ptCLaKZ/7iXPDqTNQEli3EgxhGSWULUi3CtQLi6+7YoLo6jlc6IzsY1CZ74+clYCZcI8B+kwHGZpEVvPzZ7aLwTA=";
        String dataRegion = "ewogICJ0aW1lc3RhbXAiIDogMTY4NjkyMDkyNzA4MywKICAicHJvZmlsZUlkIiA6ICI3Mjc2ZThmYzVkNjE0ODNjYmMwN2IxYjIzNjI3MDA4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJXYWJvV2ViaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kMGQ2MGYzNDFiNzc5Y2ViYTZkYWZmOWI3NDYzN2RiY2VjNTQxODQyZGRmNmY2N2Q5YWY2NzRjYThkMDFiMTYyIgogICAgfQogIH0KfQ==";
        npcRegion.addTrait(new LookTrait());
        npcRegion.getOrAddTrait(SkinTrait.class).setSkinPersistent("regionen", signatureRegion, dataRegion);
        npcRegion.spawn(regionLocation);
    }

    public static Duno getInstance() {
        return instance;
    }

    public static String simplifyNumber(double number) {
        number = roundDouble(number);
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
        return nf.format(number);
    }

    private static double roundDouble (double value) {
        int scale = (int) Math.pow(10, 1);
        return (double) Math.round(value * scale) / scale;
    }

    public static Location getRegionSpawnPoint(Region region) {
        return new Location(region.getWorld(), region.getX(), region.getY(), region.getZ(), region.getYaw(), region.getPitch());
    }
}
