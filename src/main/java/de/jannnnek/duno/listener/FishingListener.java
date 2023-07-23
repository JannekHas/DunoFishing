package de.jannnnek.duno.listener;

import de.jannnnek.duno.Duno;
import de.jannnnek.duno.loot.Fish;
import de.jannnnek.duno.loot.MoneyBundles;
import de.jannnnek.duno.loot.Scrap;
import de.jannnnek.duno.player.FishingPlayer;
import de.jannnnek.duno.region.Region;
import de.jannnnek.duno.rods.Rod;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FishingListener implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        boolean catchedFish = false;
        Player player = e.getPlayer();
        Region region = Region.getRegion(player.getWorld());
        FishingPlayer fishingPlayer = FishingPlayer.players.get(player.getUniqueId());
        boolean alreadyFull = fishingPlayer.collectionIsFull(region);
        Location playerLocation = player.getLocation();
        Rod rod = Rod.STARTER.getRod(player.getInventory().getItemInMainHand());
        PlayerFishEvent.State state = e.getState();
        e.setExpToDrop(-1);
        //TODO: Zeit setzen
        FishHook fishHook = e.getHook();
        fishHook.setWaitTime(5);
        if (state == PlayerFishEvent.State.CAUGHT_FISH || state == PlayerFishEvent.State.CAUGHT_ENTITY) {
            Entity caughtEntity = e.getCaught();
            if (!(caughtEntity instanceof Item)) {
                fishHook.remove();
                return;
            }
            Item caughtItem = (Item) caughtEntity;
            Random random = new Random();
            int randomNumber = random.nextInt(100);
            //TODO debug
            Bukkit.broadcastMessage("Drop: " + randomNumber + "[<85 Fish, <97Scrap, else MoneyBundle]");
            ItemStack drop;
            double size = 0.0;
            Fish fish = null;
            if (randomNumber < 85) {
                catchedFish = true;
                fish = Region.getRegion(playerLocation.getWorld()).getLoot(rod.getRarityAsString());
                drop = fish.getFish();
                size = fish.getRandomSize();
                player.sendMessage("§7Du hast einen " + fish.getName() + "§7 gefangen. Größe: §a" + size + "cm");
            } else if (randomNumber < 97) {
                ArrayList scrap = Scrap.CAN.getRandomScrap();
                Scrap scrap1 = (Scrap) scrap.get(1);
                drop = (ItemStack) scrap.get(0);
                player.sendMessage("§7Du hast " + scrap1.getAkkusativ() + scrap1.getName() + "§7 gefangen.");
            } else {
                ArrayList bundle = MoneyBundles.TINY.getRandomWeightedBundle();
                MoneyBundles moneyBundles = (MoneyBundles) bundle.get(1);
                drop = (ItemStack) bundle.get(0);
                player.sendMessage("§7Du hast ein Gelbündel gefunden. Wert: §a" + moneyBundles.getWorth() + " Münzen");
            }
            if (rod.getDoubledrop() > 0) {
                double randomDoubleDrop = Math.random() * 100;
                if (randomDoubleDrop <= rod.getDoubledrop()) {
                    drop.setAmount(drop.getAmount()*2);
                    player.sendMessage("§aDu hattest Glück und dein Fang hing doppelt am Haken");
                }
            }
            if (catchedFish) {
                Fish finalFish = fish;
                double finalSize = size;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        fishingPlayer.addCatchedFish(drop.getAmount());
                        player.getScoreboard().getTeam("catchedFish").setSuffix("§6" + fishingPlayer.getCatchedFish());
                        if (finalSize > fishingPlayer.getRecord(finalFish.getName())) {
                            fishingPlayer.setRecord(finalFish.getName(), finalSize);
                        }
                        if (fishingPlayer.collectionIsFull(region) && !alreadyFull) {
                            player.sendTitle("§bSammlung vervollständigt", region.getName(), 10, 40, 10);
                            player.playSound(playerLocation, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
                        }
                    }
                }.runTaskLater(Duno.getInstance(), 11);
            }
            caughtItem.setItemStack(drop);
            HashMap<Integer, ItemStack> leftover = player.getInventory().addItem(drop);
            if (leftover.isEmpty()) {
                player.getInventory().removeItem(drop);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.playSound(playerLocation, Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
                        player.getInventory().addItem(drop);
                    }
                }.runTaskLater(Duno.getInstance(), 11);
                Bukkit.getScheduler().runTaskLater(Duno.getInstance(), caughtItem::remove, 11);
                caughtItem.setCanPlayerPickup(false);
                caughtItem.setPickupDelay(Integer.MAX_VALUE);
                caughtItem.setCanMobPickup(false);
                caughtItem.setCanMobPickup(false);
                caughtItem.setTicksLived(Integer.MAX_VALUE);
            } else {
                player.sendTitle("ACHTUNG UNICODE §cInventar voll!", "§7Dein Fang wurde gedroppt.", 20, 40, 20);
                player.playSound(playerLocation, Sound.ENTITY_SNIFFER_DROP_SEED, 1.0f, 1.0f);
                return;
            }
        }

        if (state == PlayerFishEvent.State.BITE) {
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0f, 1.0f);
        }
    }

}
